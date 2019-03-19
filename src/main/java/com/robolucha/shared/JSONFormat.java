package com.robolucha.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONFormat {
	
	public static String now() {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

}
