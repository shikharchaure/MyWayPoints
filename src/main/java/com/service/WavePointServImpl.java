/*This interface has a list of implemented methods to make API calls to weather
 * and map */

package com.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.cg.dto.WeatherDetailsDto;

@Service
public class WavePointServImpl implements WavePointServ {

	public WeatherDetailsDto getWeather(Double lat,Double lon) {
		// TODO Auto-generated method stub
		HttpsURLConnection urlConnection = null;
		URL url = null;
		URL restServiceURL;
		String output;
		String response="";
		WeatherDetailsDto weatherDetailsDto=null;
		try {
			//HIT OPEN WEATHER API BY LAT LON
			restServiceURL = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&APPID=APIKEY");		
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");
			if(httpConnection.getResponseCode()<HttpURLConnection.HTTP_BAD_REQUEST){
				
				BufferedReader responseBuffer =new BufferedReader(new InputStreamReader(
						(httpConnection.getInputStream())));

				while ((output = responseBuffer.readLine()) != null) {
					//System.out.println(output);
					response=response+output;
				}
				
				//Convert response object to JSONObject to extract details which we want to display
				JSONParser parser=new JSONParser();
				JSONObject weatherDetailsObj=(JSONObject)parser.parse(response);
				org.json.simple.JSONArray jArray=(org.json.simple.JSONArray) weatherDetailsObj.get("weather");
				JSONObject parameterObj=(JSONObject) jArray.get(0);
				String weather=parameterObj.get("main").toString();
				JSONObject detailobj=(JSONObject) weatherDetailsObj.get("main");
				weatherDetailsDto=new WeatherDetailsDto();
				weatherDetailsDto.setLatitude(lat);
				weatherDetailsDto.setLongitude(lon);
				weatherDetailsDto.setDescription(parameterObj.get("main").toString());
				DecimalFormat df = new DecimalFormat("#.00");
				Double tempMin=Double.parseDouble(detailobj.get("temp_min").toString())-273;
				tempMin= Double.parseDouble(df.format(tempMin));
				Double tempMax=Double.parseDouble(detailobj.get("temp_max").toString())-273;
				tempMax= Double.parseDouble(df.format(tempMax));
				weatherDetailsDto.setMaxTemperature(tempMax);
				weatherDetailsDto.setMinTemperature(tempMin);
				weatherDetailsDto.setHumidity(Double.parseDouble(detailobj.get("humidity").toString()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weatherDetailsDto;
	}

	public JSONObject getDirections(String start, String end) {
		HttpsURLConnection urlConnection = null;
		URL url = null;
		URL restServiceURL;
		String output;
		String response="";
		JSONObject directionRespObj=null;
		end=end.replaceAll(" ","");
		try {
			//hit Google API by start and End
			restServiceURL = new URL("https://maps.googleapis.com/maps/api/directions/json?origin="+start+"&destination="+end+"&key=APIKEY");
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");
			if(httpConnection.getResponseCode()<HttpURLConnection.HTTP_BAD_REQUEST){
				
				BufferedReader responseBuffer =new BufferedReader(new InputStreamReader(
						(httpConnection.getInputStream())));

				while ((output = responseBuffer.readLine()) != null) {
					//System.out.println(output);
					response=response+output;
				}
				directionRespObj=new JSONObject();
				JSONParser parser=new JSONParser();
				directionRespObj=(JSONObject)parser.parse(response);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return directionRespObj;
	}

}
