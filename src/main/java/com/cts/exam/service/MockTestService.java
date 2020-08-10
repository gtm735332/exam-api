package com.cts.exam.service;

import org.springframework.http.ResponseEntity;

import com.cts.exam.beans.ExamSheet;
import com.cts.exam.response.MockTestPaper;
import com.cts.exam.response.MockTestResultResponse;
import com.cts.exam.response.MockTestSubmitResponse;

public interface MockTestService {

	public ResponseEntity<MockTestPaper> getTestPaper(String subject, MockTestPaper paper);

	public ResponseEntity<MockTestSubmitResponse> submitMockTest(ExamSheet examSheet, MockTestSubmitResponse testSubmitResponse);

	public ResponseEntity<MockTestResultResponse> getMockTestResult(String candiateId, String subject,MockTestResultResponse resultResponse);
}
