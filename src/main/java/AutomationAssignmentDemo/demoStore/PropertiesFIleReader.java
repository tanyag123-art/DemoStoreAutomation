package AutomationAssignmentDemo.demoStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFIleReader {
	 
	 private Properties properties;
	 private final String propertyFilePath= "src//test//resources//cucumber.properties";
	 
	 
	 public PropertiesFIleReader(){
	 BufferedReader reader;
	 try {
	 reader = new BufferedReader(new FileReader(propertyFilePath));
	 properties = new Properties();
	 try {
	 properties.load(reader);
	 reader.close();
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 } catch (FileNotFoundException e) {
	 e.printStackTrace();
	 throw new RuntimeException("cucumber.properties not found at " + propertyFilePath);
	 } 
	 }
	 
	 public String getDriverPath(){
	 String driverPath = properties.getProperty("webdriver.chrome");
	 if(driverPath!= null) return driverPath;
	 else throw new RuntimeException("driverPath not specified in the cucumber.properties file."); 
	 }
	 	 
	 public String getApplicationUrl() {
	 String url = properties.getProperty("portal.url");
	 if(url != null) return url;
	 else throw new RuntimeException("url not specified in the cucumber.properties file.");
	 }
	 
	}

