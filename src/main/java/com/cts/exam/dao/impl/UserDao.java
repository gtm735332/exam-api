package com.cts.exam.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cts.exam.model.User;




@Repository
public class UserDao {

	Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@PersistenceContext
	EntityManager em;
	
	public User getUserByUserName(String userName) {
		User user =null;
		Query query = em.createNamedQuery("User.getUserByUserName")
						.setParameter("userName", userName);
		try {
		 user = (User) query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;	
	}
}
