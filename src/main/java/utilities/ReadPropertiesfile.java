package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesfile {

	public static FileInputStream fis = null;
	public static Properties prop = null;

	public static Properties readPropertiesFile() throws Exception {
		try {
			fis = new FileInputStream(".\\resources\\TimeZone.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;

	}

	public static String getbrowserName() {

		return prop.getProperty("browserName");// gets property with key browserName from properties file and returns
												// it.
	}

	public static String getUrl() {

		return prop.getProperty("url");// gets property with key url from properties file and returns it.
	}

	public String getValue(String key) {

		String value = prop.getProperty(key);
		return value;
	}

}
