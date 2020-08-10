package com.cts.exam.response;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockTestSubmitResponse {
	
	private String statusCode;
	private String statusMessage;
	private String candidateId;
	private String message;
	private Map<String,String> errorMap;
	
	
	public Map<String, String> getErrorsMap() {
		if(errorMap == null) {
			errorMap = new HashMap<>();
		}
		return errorMap;
	}
	public void setErrorsMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}
}
