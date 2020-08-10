package com.cts.exam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class ExamValidator {
private ExamValidator() {}
	public static Date convertStrToDateObj(String dateStr) throws ParseException {
			
			SimpleDateFormat sdf = new SimpleDateFormat(ValidatorConstant.DATE_FORMAT);
			sdf.setLenient(false);
			Date dateObj = sdf.parse(dateStr);
			return sdf.parse(sdf.format(dateObj));
		}
	
	public static void validateGetTestResult(String candiateId, Map<String, String> errorsMap) {
		
		if(StringUtils.isEmpty(candiateId)) {
			errorsMap.put(ValidatorConstant.CANDIDATE_ID, ValidatorConstant.MANDATORY_FIELD_MSG);
		}else if(!StringUtils.isNumeric(candiateId)) {
			errorsMap.put(ValidatorConstant.CANDIDATE_ID, ValidatorConstant.NUMERIC_FIELD_MSG);
		}
	
	}
}
