package com.cts.exam.dao;

import java.util.List;

import com.cts.exam.model.Questions;
import com.cts.exam.model.TestResult;

public interface MockTestDao {
	
	public List<Questions> getTestPaper(String subject);

	public void persistTestResult(TestResult testResult);

	public List<Object[]> getMockTestResult(String candiateId, String subject);
}
