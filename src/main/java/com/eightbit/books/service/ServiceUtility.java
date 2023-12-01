package com.eightbit.books.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceUtility {

	public static Date parseDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		try {
			parseDate = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseDate;
	}
}
