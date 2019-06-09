package com.robolucha.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class JSONFormat {
	static Logger logger = Logger.getLogger(JSONFormat.class);
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public static String now() {
		return SDF.format(new Date());
	}

	public static long date2timestamp(String jsonDate) {
		try {
			Date date = SDF.parse(jsonDate);
			return date.getTime();

		} catch (ParseException e) {
			String message = String.format("Invalid date [%s]", jsonDate);
			logger.warn(message, e);
			return 0;
		}
	}

	public static String clean(String json) {
		json = json.replace("\n", "");
		json = json.replace("\r", "");
		json = json.replace("\t", "");
		json = json.replace("    ", " ");
		return json;
	}
}
