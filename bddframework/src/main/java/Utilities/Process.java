package Utilities;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Process {

	private static final Logger logger = LogManager.getLogger(Process.class);
	static File healeniumLogFile = new File("D:\\healenium\\shell-installation\\web\\logs\\healenium-backend.log");

	public static void main(String[] args) throws InterruptedException {

		logger.info("Test Logs");

		System.out.println("JOe");

		long startTime = System.currentTimeMillis();
		Thread.sleep(Duration.ofSeconds(10));
		long timeSpent = System.currentTimeMillis();

		System.out.println(timeSpent-startTime);

		File file = new File(healeniumLogFile.getAbsolutePath());
		if (!file.exists() || !file.isFile()) return;

		System.out.println(getFileSizeBytes(file));
		System.out.println(getFileSizeKiloBytes(file));
		System.out.println(getFileSizeMegaBytes(file));
	}

	private static String getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024) + " mb";
	}

	private static String getFileSizeKiloBytes(File file) {
		return (double) file.length() / 1024 + "  kb";
	}

	private static String getFileSizeBytes(File file) {
		return file.length() + " bytes";
	}
}