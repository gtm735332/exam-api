package com.cts.exam.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSheet {

	
	private String candiateId;
	private String subject;
	private List<AnswerSheet> answerList;
}
