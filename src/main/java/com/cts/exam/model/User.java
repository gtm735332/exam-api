package com.cts.exam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USER")
@Data
@AllArgsConstructor
@NoArgsConstructor


	@NamedQuery(name = "User.getUserByUserName", query = "SELECT u FROM User u WHERE u.userName= :userName")


public class User implements Serializable{

	private static final long serialVersionUID = -789405100625369017L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	private String password;

	@Column(name="EMAIL_ID")
	private String emailId;
	
}
