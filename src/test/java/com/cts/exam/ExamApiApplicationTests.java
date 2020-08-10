package com.cts.exam;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.cts.exam.beans.AnswerSheet;
import com.cts.exam.beans.ExamSheet;
import com.cts.exam.dao.impl.MockTestDaoImpl;
import com.cts.exam.dao.impl.UserDao;
import com.cts.exam.model.Questions;
import com.cts.exam.model.TestResult;
import com.cts.exam.resource.MockTestsResource;
import com.cts.exam.response.MockTestPaper;
import com.cts.exam.response.MockTestResultResponse;
import com.cts.exam.response.MockTestSubmitResponse;
import com.cts.exam.service.impl.MockTestServiceImpl;


class ExamApiApplicationTests {

	MockTestsResource resource;
	UserDao userDao;
	MockTestDaoImpl testDao;
	MockTestServiceImpl testService;
	
	
	@BeforeEach
	public void setup() {
		
		resource = new MockTestsResource();
		userDao = EasyMock.niceMock(UserDao.class);
		testDao = EasyMock.niceMock(MockTestDaoImpl.class);
		testService = new MockTestServiceImpl();
		testService.setMockTestDao(testDao);
		resource.setMockTestService(testService);
		
	}
	
	@Test
	public void testGetTestPaper() {
		ResponseEntity<MockTestPaper> response = null;
		List<Questions> questionsList = null;
		
		String subject = "java";
		String candidateId = "102";
		try {//getTestPaper
			questionsList = getQuestions();
			EasyMock.expect(testDao.getTestPaper(EasyMock.isA(String.class))).andReturn(questionsList);
			EasyMock.replay(testDao);
			response = resource.getTestPaper(subject, candidateId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(response);
	}

	@Test
	public void testSubmitMockTest() {
		
		 ResponseEntity<MockTestSubmitResponse> response = null;
		 List<Questions> questionsList = null;
		 ExamSheet esheet = getExamSheet();
		 TestResult testResult = new TestResult();
		 constructTestResult(testResult, 66.66, esheet);
		 try {
			 	questionsList = getQuestions();
			 	EasyMock.expect(testDao.getTestPaper(EasyMock.isA(String.class))).andReturn(questionsList);
			 	EasyMock.replay(testDao);
			 	response = resource.submitMockTest(esheet);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 assertNotNull(response);
	}
	
	@Test
	public void testBadRequestGetTestResult() {
		ResponseEntity<MockTestResultResponse> response = null;
		try {
			response = resource.getTestResult("", "");
		}catch(Exception e) {
			e.printStackTrace();
		}
		 assertNotNull(response);
	}
	
	@Test
	public void testGetTestResult() {
		ResponseEntity<MockTestResultResponse> response = null;
		String candidateId = "102";
		String subject = "java";
		List<Object[]> resultDetails = getResultDeatils();
		try {
			EasyMock.expect(testDao.getMockTestResult(candidateId, subject)).andReturn(resultDetails);
			EasyMock.replay(testDao);
			response = resource.getTestResult("java", "102");
		}catch(Exception e) {
			e.printStackTrace();
		}
		 assertNotNull(response);
	}
	
	
	private List<Object[]> getResultDeatils() {
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj = {100.00, new Date(), "java"};
		list.add(obj);
		return list;
	}

	private void constructTestResult(TestResult testResult, double score, ExamSheet examSheet) {
		testResult.setCandidateId(examSheet.getCandiateId());
		testResult.setSubject(examSheet.getSubject());
		testResult.setScore(score);
		testResult.setTestDate(new Date());
	}

	private ExamSheet getExamSheet() {
		ExamSheet sheet = new ExamSheet();
		sheet.setSubject("java");
		sheet.setCandiateId("102");
		sheet.setAnswerList(new ArrayList<AnswerSheet>() {
			private static final long serialVersionUID = 1L;
		{
			add(new AnswerSheet("1", "b"));
		}});
		return sheet;
	}

	private List<Questions>  getQuestions() {
		
		return new ArrayList<Questions>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Questions(1, "1", "What is java", "a", "b", "c", "d", "b", "java"));
		}};
		
	}
	
}
