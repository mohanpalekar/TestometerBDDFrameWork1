package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
	
	private static Logs log = new Logs();
	
	private Logs() {
		
	}

	public static Logs getLog() {
		return log;
	}

	public Logger getLogger(String className) {
		return LogManager.getLogger(className);
	}

}
