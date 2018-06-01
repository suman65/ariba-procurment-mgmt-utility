package com.ariba.procurment.mgmt.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public final static String dd_MM_yyyy_HHmmss =  "dd-MM-yyyy HH:mm:ss";
	
	private static Date getFormattedDate(String givenDate) {
	    Timestamp dateTimeValue = null;
	    try {
	      if (null != givenDate) {
	        SimpleDateFormat timeStampFormat = new SimpleDateFormat(dd_MM_yyyy_HHmmss);
	        Date date = timeStampFormat.parse(timeStampFormat.format(givenDate));
	        dateTimeValue = new Timestamp(date.getTime());
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return dateTimeValue;
	  }

}
