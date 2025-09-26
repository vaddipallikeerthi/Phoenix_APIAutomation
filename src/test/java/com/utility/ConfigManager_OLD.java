package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_OLD {
	
	private static Properties properties=new Properties();
	
	private ConfigManager_OLD()
	{
		
	}
	static
	{
		File configFile=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader=null;
		try {
			fileReader = new FileReader(configFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
