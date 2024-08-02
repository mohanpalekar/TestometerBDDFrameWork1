package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

	public String getProperty(String propertyName, String fileName) {

		String propertyValue;

		try {
			File file = new File(fileName);

			FileReader fileReader = new FileReader(file);

			Properties properties = new Properties();

			properties.load(fileReader);

			propertyValue = properties.getProperty(propertyName);

			Logs.getLog().getLogger().info("Success reading property : "+propertyName);

		}catch(IOException ex){

			Logs.getLog().getLogger().error("Failed to get property by name : "+propertyName);

			propertyValue = null;
		}

		return propertyValue;
	}

}
