package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

	public static String getProperty(String filePath, String propertyName) {

		String property = null;

		if(!(filePath.isBlank() || propertyName.isBlank())) {

			try {
				File file = new File(filePath);

				FileReader fileReader = new FileReader(file);

				Properties properties =  new Properties();

				properties.load(fileReader);

				property = properties.getProperty(propertyName);
			}catch(IOException ex) {
				Logs.getLog().getLogger("AppProperties").error("ERROR --> "+ex.getMessage());
			}
			if(property != null) {
				Logs.getLog().getLogger("AppProperties").info("INFO --> success reading property : {"+propertyName+" : "+property+"}");
			}else {
				Logs.getLog().getLogger("AppProperties").error("ERROR --> failure reading property : {"+propertyName+" : "+property+"}");
			}
		}else {
			Logs.getLog().getLogger("AppProperties").error("ERROR --> either filePath and/or propertyName is blank/empty");
		}
		return property;
	}


}
