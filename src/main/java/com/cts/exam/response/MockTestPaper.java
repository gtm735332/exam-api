package com.cts.exam.response;

import java.util.List;
import java.util.Map;

import com.cts.exam.model.Questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MockTestPaper {
	
	private String statusCode;
	private String statusMessage;
	private String candidateId;
	private String subject;
	private String numberOfQuestions;
	private List<Questions> questions;
	
	private Map<String, String> errorsMap;
}
