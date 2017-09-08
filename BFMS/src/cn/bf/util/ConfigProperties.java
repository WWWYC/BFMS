package cn.bf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
	private static Properties properties;
	private ConfigProperties() {
		
        }
	
	static {
		properties = new Properties();
		InputStream in = ConfigProperties.class.getClassLoader().getResourceAsStream("properties/config.properties");
		try {
                        properties.load(in);
                } catch (IOException e) {
                        e.printStackTrace();
                }
	}
	
	public static String getValue(String key) {
		return properties.getProperty("areaCode");
	}
	
	public Properties getProperties(){
		if(properties == null){
			properties = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("properties/config.properties");
			try {
	                        properties.load(in);
                        } catch (IOException e) {
	                        e.printStackTrace();
                        }
			return properties;
		} else {
			return properties;
		}
	}
	
	
}
