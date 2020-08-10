package com.cts.exam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="QUESTIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor


	@NamedQuery(name = "Questions.getAllQuestionsBySubject", query = "SELECT q FROM Questions q WHERE q.subject = :subject")

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(content = Include.NON_DEFAULT)

public class Questions implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="S_NO")
	private int sno;
	
	@Column(name="QUESTION_NO")
	private String qestionNo;
	
	@Column(name = "QUESTION_DESC")
	private String questionDesc;
	
	@Column(name = "A")
	private String a;
	
	@Column(name = "B")
	private String b;
	
	@Column(name = "C")
	private String c;
	
	@Column(name = "D")
	private String d;
	
	@Column(name = "ANSWERE")
	private String answere;
	
	private String subject;
	
}
