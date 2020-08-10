package com.cts.exam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TEST_RESULT")


	@NamedQuery(name = "TestResult.getResultByCidAndSubject", query = "SELECT score, testDate FROM TestResult r WHERE r.candidateId= :cid AND r.subject = :subject")


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TestResult implements Serializable{
	
	private static final long serialVersionUID = -929269490779081861L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SNO")
	private int sno;
	
	@Column(name = "CANDIDATE_ID")
	private String candidateId;
	
	private String subject;
	
	private double score;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "TEST_DATE")
	private Date testDate; 
}
