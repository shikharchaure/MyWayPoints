/*This interface has a list of defined methods to make API calls to weather
 * and map */

package com.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.cg.dto.WeatherDetailsDto;

public interface WavePointServ {
WeatherDetailsDto getWeather(Double lat,Double lon);
JSONObject getDirections(String start, String end);
}
