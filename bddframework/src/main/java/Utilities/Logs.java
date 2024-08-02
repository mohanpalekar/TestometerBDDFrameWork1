package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
	
	private static Logs log = new Logs();
	
	private Logger logger = LogManager.getLogger();
	
	private Logs() {
		
	}

	public static Logs getLog() {
		return log;
	}

	public Logger getLogger() {
		return logger;
	}

}
