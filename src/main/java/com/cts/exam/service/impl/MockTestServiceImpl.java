package com.cts.exam.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.exam.beans.AnswerSheet;
import com.cts.exam.beans.DetailedResult;
import com.cts.exam.beans.ExamSheet;
import com.cts.exam.dao.MockTestDao;
import com.cts.exam.model.Questions;
import com.cts.exam.model.TestResult;
import com.cts.exam.response.MockTestPaper;
import com.cts.exam.response.MockTestResultResponse;
import com.cts.exam.response.MockTestSubmitResponse;
import com.cts.exam.service.MockTestService;
import com.cts.exam.util.ValidatorConstant;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter@Setter
public class MockTestServiceImpl implements MockTestService{

	private static final Logger logger = LoggerFactory.getLogger(MockTestServiceImpl.class);
	
	@Autowired
	MockTestDao mockTestDao;
	
	
	@Override
	public ResponseEntity<MockTestPaper> getTestPaper(String subject, MockTestPaper paper) {
		logger.info("MockTestServiceImpl : getTestPaper");
		List<Questions> questionList = mockTestDao.getTestPaper(subject);
		if(CollectionUtils.isNotEmpty(questionList))
		{
			paper.setQuestions(questionList);
			paper.setNumberOfQuestions(String.valueOf(questionList.size()));
			paper.setStatusCode(ValidatorConstant.SUCCESS_CODE);
			paper.setStatusMessage(ValidatorConstant.SUCCESS_MSG);
		}
		return new ResponseEntity<>(paper, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MockTestSubmitResponse> submitMockTest(ExamSheet examSheet, MockTestSubmitResponse testSubmitResponse) {
		logger.info("MockTestServiceImpl : submitMockTest");
		
		List<Questions> questionList = mockTestDao.getTestPaper(examSheet.getSubject());
		double score = calculateScore(examSheet.getAnswerList(), questionList);
		TestResult testResult = new TestResult();
		constructTestResult(testResult, score, examSheet);
		mockTestDao.persistTestResult(testResult);
		testSubmitResponse.setCandidateId(examSheet.getCandiateId());
		testSubmitResponse.setStatusCode(ValidatorConstant.SUCCESS_CODE);
		testSubmitResponse.setStatusMessage(ValidatorConstant.SUCCESS_MSG);
		testSubmitResponse.setMessage(ValidatorConstant.TEST_SUBMISSION_SUCCESS);
		return new ResponseEntity<>(testSubmitResponse, HttpStatus.OK);
	}

	private void constructTestResult(TestResult testResult, double score, ExamSheet examSheet) {
		testResult.setCandidateId(examSheet.getCandiateId());
		testResult.setSubject(examSheet.getSubject());
		testResult.setScore(score);
		testResult.setTestDate(new Date());
	}

	private float calculateScore(List<AnswerSheet> answerList, List<Questions> questionList) {
	
		List<AnswerSheet> correctList =  answerList.stream().filter(answerSheet -> questionList.stream()
	              .anyMatch(question -> answerSheet.getQuestionNo().equals(question.getQestionNo()) && answerSheet.getMarkedAnswer().equals(question.getAnswere()))) 
	              .collect(Collectors.toList());
		
		return (((float)correctList.size()/questionList.size())*100);
	}

	@Override
	public ResponseEntity<MockTestResultResponse> getMockTestResult(String candiateId, String subject,MockTestResultResponse resultResponse) {
		logger.info("MockTestServiceImpl : getMockTestResult  candiateId :{},  subject  :{}", candiateId,  subject);
		List<Object[]> resultDetails = mockTestDao.getMockTestResult(candiateId, subject);
		if(CollectionUtils.isEmpty(resultDetails))
		{
			resultResponse.setStatusCode(ValidatorConstant.NOT_FOUND_CODE);
			resultResponse.setStatusMessage(ValidatorConstant.NOT_FOUND_MSG);
			return new ResponseEntity<>(resultResponse, HttpStatus.NOT_FOUND);
		}else{
			constructResultResponse(candiateId, resultDetails,resultResponse);
			resultResponse.setStatusCode(ValidatorConstant.SUCCESS_CODE);
			resultResponse.setStatusMessage(ValidatorConstant.SUCCESS_MSG);
			return new ResponseEntity<>(resultResponse, HttpStatus.OK);
		}
		
	}

	private void constructResultResponse(String candiateId, List<Object[]> resultDetails,MockTestResultResponse resultResponse) {
		
		List<DetailedResult> drList = new ArrayList<>();
		resultResponse.setCandidateId(candiateId);
	
		resultDetails.stream().forEach(obj->{
			DetailedResult resDetails = new DetailedResult();
			resDetails.setScore(obj[0].toString());
			resDetails.setExamDate(new SimpleDateFormat(ValidatorConstant.DATE_FORMAT).format((Date)obj[1]));
			resDetails.setSubject(obj[2].toString());
			drList.add(resDetails);
		});
		resultResponse.setResults(drList);
	}
	
}
