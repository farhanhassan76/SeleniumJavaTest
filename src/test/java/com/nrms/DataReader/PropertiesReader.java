package com.nrms.DataReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertiesReader {
	public static String getValue(String key){
		String value=null;
		File file1= new File("datafile.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		value=prop.getProperty(key);
		return value;
	}
	
	public String [] getKpc() throws Exception{
		String [] user_list=null;
		String csvFile = "keyPerformance.csv";
		BufferedReader br = null;
		String  line = "";
		String cvsSplitBy = ",";
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			user_list = line.split(cvsSplitBy);
		
		}
		br.close();
		return user_list;
		
	}

}
