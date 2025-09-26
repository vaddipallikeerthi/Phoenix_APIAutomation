package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties properties = new Properties();
	private static String path = "config/config.properties";
	private static String env;

	private ConfigManager() {

	}

	static {
		env = System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		switch (env) {
		case "dev": {
			path = "config/config.dev.properties";
			break;
		}
		case "qa": {
			path = "config/config.qa.properties";
			break;
		}
		default:
			path = "config/config.properties";
			break;
		}

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (inputStream == null) {
			System.out.println("Cannot find the file at path " + path);
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) throws IOException {
		// TODO Auto-generated method stub

		return properties.getProperty(key);

	}

}
