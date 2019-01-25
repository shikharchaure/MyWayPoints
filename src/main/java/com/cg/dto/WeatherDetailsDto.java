/*This class is used to store all the weather details which has to be displayed
to the user*/ 
package com.cg.dto;

public class WeatherDetailsDto {
	
	private String description;
	private double temperature;
	private double humidity;
	private double latitude;
	private double longitude;
	private double maxTemperature;
	private double minTemperature;
	
	
	
	public double getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public double getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "WeatherDetailsDto [description=" + description + ", temperature=" + temperature + ", humidity="
				+ humidity + ", latitude=" + latitude + ", longitude=" + longitude + ", maxTemperature="
				+ maxTemperature + ", minTemperature=" + minTemperature + "]";
	}

}
