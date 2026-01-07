package com.qa.opencart.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
	
	public static final Logger log = LogManager.getLogger(LogUtils.class);
	
	public static void info(String msg) {
		log.info(msg);
	}
	public static void warn(String msg) {
		log.warn(msg);
	}
	public static void error(String msg) {
		log.error(msg);
	}
	public static void fatal(String msg) {
		log.fatal(msg);
	}
		
		
	

}
