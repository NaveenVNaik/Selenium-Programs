package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadRepository {
	public Properties getPropertyObj(String repoName)throws IOException{
		Properties prop = new Properties();
		FileInputStream objfile = 
				new FileInputStream(System.getProperty("user.dir")+"\\src\\repository\\"+repoName);
		prop.load(objfile);
		return prop;
	}
	
	public String getPropertyObj(String repoName, String key)throws IOException{
		Properties prop = new Properties();
		FileInputStream objfile = 
				new FileInputStream(System.getProperty("user.dir")+"\\src\\repository\\"+repoName);
		prop.load(objfile);
		return prop.getProperty(key);
	}
}
