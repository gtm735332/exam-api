package com.cts.exam.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cts.exam.dao.MockTestDao;
import com.cts.exam.model.Questions;
import com.cts.exam.model.TestResult;

@Repository
public class MockTestDaoImpl implements MockTestDao{

	private static final Logger logger = LoggerFactory.getLogger(MockTestDaoImpl.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getTestPaper(String subject) {
		logger.info("MockTestDaoImpl :getTestPaper for subject :{} ", subject);
		Query query = em.createNamedQuery("Questions.getAllQuestionsBySubject")
						.setParameter("subject", subject);
		
		List<Questions> questionList =  query.getResultList();
		return questionList;
	}

	@Override
	@Transactional
	public void persistTestResult(TestResult testResult) {
		logger.info("MockTestDaoImpl :persistTestResult");
		em.persist(testResult);
		logger.info("\n Persisted TestResult ");
	}

	@Override
	public List<Object[]> getMockTestResult(String candiateId, String subject) {
		logger.info("MockTestDaoImpl :getMockTestResult for candiateId :{} ", candiateId);
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT r.score, r.testDate, r.subject FROM TestResult r WHERE r.candidateId= :cid ");
		
		if(StringUtils.isNotEmpty(subject))
			queryBuilder.append(" AND r.subject = :subject");
		
		Query query = em.createQuery(queryBuilder.toString())
						.setParameter("cid", candiateId);
		
		if(StringUtils.isNotEmpty(subject))
			query.setParameter("subject", subject);
			
		@SuppressWarnings("unchecked")
		List<Object[]> resultList =query.getResultList();	
		return resultList;
	}

}
