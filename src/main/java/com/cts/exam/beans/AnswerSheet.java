package com.cts.exam.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSheet {

	private String questionNo;
	private String markedAnswer;
	
}
