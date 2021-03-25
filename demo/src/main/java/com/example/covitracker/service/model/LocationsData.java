package com.example.covitracker.service.model;

public class LocationsData {
	
	private String state;
	
	private String country;
	
	private int updatedCaseCount;
	
	private int differenceFromPreviousDay;
	


	public void setDifferenceFromPreviousDay(int differenceFromPreviousDay) {
		this.differenceFromPreviousDay = differenceFromPreviousDay;
	}
	
	public int getDifferenceFromPreviousDay() {
		return differenceFromPreviousDay;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getUpdatedCaseCount() {
		return updatedCaseCount;
	}

	public void setUpdatedCaseCount(int updatedCaseCount) {
		this.updatedCaseCount = updatedCaseCount;
	}

	@Override
	public String toString() {
		return "LocationsData [state=" + state + ", country=" + country + ", updatedCaseCount=" + updatedCaseCount
				+ "]";
	}
	
	

}
