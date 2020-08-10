package com.cts.exam.response;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cts.exam.beans.DetailedResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MockTestResultResponse {

	private String statusCode;
	private String statusMessage;
	private String candidateId;
	private List<DetailedResult> results;
	private Map<String, String> errorsMap;
	
	public Map<String, String> getErrorsMap() {
		if(errorsMap == null) {
			errorsMap = new HashMap<>();
		}
		return errorsMap;
	}
	public void setErrorsMap(Map<String, String> errorMap) {
		this.errorsMap = errorMap;
	}
}
