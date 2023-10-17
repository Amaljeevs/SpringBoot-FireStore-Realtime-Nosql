package com.example.firebase.dto;

import org.json.simple.JSONObject;

import io.swagger.annotations.ApiModel;

@ApiModel("ServiceResponseDTO")
public class ServiceResponse {

	private String message;

	private String code;

	private String details;

	private JSONObject data;

	public ServiceResponse(String message, String code, String details, JSONObject data) {
		super();
		this.message = message;
		this.code = code;
		this.details = details;
		this.data = data;
	}

	public ServiceResponse(String message, String code, String details) {
		super();
		this.message = message;
		this.code = code;
		this.details = details;
	}

	public ServiceResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	
	

}
