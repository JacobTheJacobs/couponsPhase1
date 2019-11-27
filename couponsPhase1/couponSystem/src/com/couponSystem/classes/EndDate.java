package com.couponSystem.classes;

import java.util.Calendar;
import java.util.Date;

public class EndDate {
	
	Date date;
	
	/**
	 * @param date
	 */
	public EndDate(Date date) {
		super();
		this.date = date;
	}
	public Date newDate(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		date = c.getTime();
		return date;
		
	}
}
