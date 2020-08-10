package com.cts.exam.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedResult {
	
	private String subject;
	private String score;
	private String examDate;
	
}
