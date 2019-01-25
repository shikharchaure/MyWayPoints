/*This class handles all the requests submitted by users*/


package com.sc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cg.dto.WeatherDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.service.WavePointServ;



@Controller
public class HelloController {
	@Autowired
	WavePointServ wavePointServ;

	
	//This is the first method which gets executed when app gets started.
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	String hit(Model model) throws JsonProcessingException {
		return "welcome2";
	}

	@RequestMapping(value="/welcome", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model,@RequestParam String start, @RequestParam String end) throws ParseException, IOException{
		start=start.replace(" ","");
		end=end.replace(" ","");
		List<WeatherDetailsDto> wayPointsWeather=new ArrayList<WeatherDetailsDto>();
		JSONObject latLongObj=new JSONObject();
		JSONObject cityObj=new JSONObject();
		JSONObject addressObj=new JSONObject();
		List<Double> markers=null;
		
		List<List<Double>> putMarkers=new ArrayList<List<Double>>();
		Double latitude,longitude;
		String place;
		
		List<Double> latlong=null;
		HashMap<String, List<Double>> places=new HashMap<String, List<Double>>();
		//Check if the user has entered valid start and end locations
		if(validParams(start, end)==false){
			//Return Control to errorMessage.jsp
			return "errorMessage";
		}
		else {
			//Make an API call to display directions.
			JSONObject respObj=wavePointServ.getDirections(start, end);
			if(respObj!=null) {
				//Extract lat long to display waypoints as markers which will be used to display weather
				JSONArray routeArray=(JSONArray) respObj.get("routes");
				JSONObject routeObj=(JSONObject) routeArray.get(0);
				JSONArray legsArray=(JSONArray) routeObj.get("legs");
				JSONObject legs=(JSONObject) legsArray.get(0);
				JSONArray steps=(JSONArray) legs.get("steps");
				List<JSONObject> stepsObjs=new ArrayList<JSONObject>();
				for (int i = 0; i < steps.size(); i++) {
					stepsObjs.add((JSONObject)steps.get(i));
				}
				
				int random=0;
				String randomPlace="";
				for(JSONObject stepObj:stepsObjs) {
					latlong=new ArrayList<Double>();
					JSONObject distObj= (JSONObject) stepObj.get("distance");
					Long dist=(Long) distObj.get("value");
					latLongObj=(JSONObject) stepObj.get("start_location");
					latitude=(Double) latLongObj.get("lat");
					longitude=(Double) latLongObj.get("lng");
					latlong.add(latitude);
					latlong.add(longitude);
					if(dist>400){
						randomPlace=Integer.toString(random);
						places.put(randomPlace, latlong);
						random=random+1;
					}
			}
			Set<WeatherDetailsDto> waypointWeather=new HashSet<WeatherDetailsDto>();
			ArrayList<String> dispCity=new ArrayList<String>();
			Set<String> keys=places.keySet();
			String de="";
			WeatherDetailsDto detailsDto=null;
			for(String key:keys){
				markers=new ArrayList<Double>();
				markers=places.get(key);
				detailsDto=new WeatherDetailsDto();
				detailsDto=wavePointServ.getWeather(markers.get(0),markers.get(1));
				de="Desciption-  "+detailsDto.getDescription().toString()
						+"\nMax Temperature-  "+detailsDto.getMaxTemperature()
						+"\nMin Temperature-  "+detailsDto.getMinTemperature()
						+"\nHumidity-  "+detailsDto.getHumidity();
				//Store all weather details in dispCity
				dispCity.add(de);
				//Store all marker lat longs in putMarkers list
				putMarkers.add(markers);
				de="";
			}
			ObjectMapper objectMapper = new ObjectMapper();
			//Send lat long and weather Details to jsp for displaying it on marker
			model.addAttribute("latlon",objectMapper.writeValueAsString(putMarkers));
			model.addAttribute("keys",objectMapper.writeValueAsString(dispCity));
			JSONObject reqObj=new JSONObject();
			//Send request Object to jsp
			reqObj.put("travelMode", "DRIVING");
			reqObj.put("origin", " ");
			reqObj.put("destination", " ");
			model.addAttribute("Request",reqObj);
			//Send response Object to jsp
			model.addAttribute("Response",respObj);
			model.addAttribute("start",start);
			model.addAttribute("end",end);
			places=new HashMap<String, List<Double>>(); 
		}
			else{
				return "errorMessage";
			}
		// Returns control welcome.jsp 
		return "welcome";
		}
	}

	
	boolean validParams(String start,String dest){
		boolean validStart=false;
		if (Pattern.matches("[a-zA-Z]+",start) && (Pattern.matches("[a-zA-Z]+",dest))){ 	
		if(start!=null){
			if(dest!=null){
				if(start.equals(dest)){
					validStart=false;
				}
				else
					validStart= true;
			}
		}
		else{
			validStart= false;
		}
		}else{
			validStart= false;
		}
		return validStart;
	}
}
