package com.cts.exam.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cts.exam.beans.ExamSheet;
import com.cts.exam.response.MockTestPaper;
import com.cts.exam.response.MockTestResultResponse;
import com.cts.exam.response.MockTestSubmitResponse;
import com.cts.exam.service.MockTestService;
import com.cts.exam.util.ExamValidator;
import com.cts.exam.util.ValidatorConstant;

import lombok.Getter;
import lombok.Setter;

@RestController
@Getter@Setter
@RequestMapping("/mocktest")
public class MockTestsResource {

private static final Logger logger = LoggerFactory.getLogger(MockTestsResource.class);
	
	@Autowired
	MockTestService mockTestService;
	
	@GetMapping(path = "/paper")
	@Procedure("application/json")
	public ResponseEntity<MockTestPaper> getTestPaper(@RequestParam("subject") String subject, @RequestParam("candiateId") String  candiateId){
		logger.info("MockTestResource: getTestPaper, subject : {}, candiateId :{}", subject, candiateId);
		MockTestPaper paper = new MockTestPaper();
		paper.setCandidateId(candiateId);
		paper.setSubject(subject);
		return mockTestService.getTestPaper(subject, paper);
	}
	
	@PostMapping(path = "/submit", consumes = "application/json", produces = "application/json")
	public ResponseEntity<MockTestSubmitResponse> submitMockTest(@RequestBody  ExamSheet examSheet){
		logger.info("MockTestResource: submitTest, subject : {}, candiateId :{}", examSheet.getSubject(), examSheet.getCandiateId());
		MockTestSubmitResponse testSubmitResponse = new MockTestSubmitResponse();
		return mockTestService.submitMockTest(examSheet, testSubmitResponse);
		 
	} 
	
	@GetMapping(path = "/result")
	@Procedure("application/json")
	public ResponseEntity<MockTestResultResponse> getTestResult(@RequestParam("subject") String subject, @RequestParam("candiateId") String  candiateId){
		logger.info("MockTestResource: getTestResult, subject : {}, candiateId :{}", subject, subject);
		MockTestResultResponse resultResponse = new MockTestResultResponse();
		ExamValidator.validateGetTestResult(candiateId, resultResponse.getErrorsMap());
		if(resultResponse.getErrorsMap().isEmpty()) {
			return mockTestService.getMockTestResult(candiateId,subject, resultResponse);
		}else {
			resultResponse.setStatusCode(ValidatorConstant.BAD_REQUEST_CODE);
			resultResponse.setStatusMessage(ValidatorConstant.BAD_REQUEST_MSG);
			return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
}
