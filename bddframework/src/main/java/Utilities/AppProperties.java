package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

	public String getProperty(String filePath, String propertyName) {
		String property = null;
		try {
			File file = new File(filePath);

			FileReader fileReader = new FileReader(file);

			Properties properties =  new Properties();

			properties.load(fileReader);

			property = properties.getProperty(propertyName);
		}catch(IOException ex) {
			Logs.getLog().getLogger().error("ERROR --> "+ex.getMessage());
		}
		if(property != null) {
			Logs.getLog().getLogger().info("INFO --> success reading property : "+property);
		}else {
			Logs.getLog().getLogger().info("ERROR --> failure reading property : "+property);
		}
		return property;

	}


}
