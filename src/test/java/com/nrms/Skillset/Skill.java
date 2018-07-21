package com.nrms.Skillset;

import java.util.Random;

import com.nrms.DataReader.PropertiesReader;

public class Skill {

	String title, category, status, description;
	public static Skill get(){
		Skill s=new Skill();
		Random randomGenerator = new Random();
		String randomString = Integer.toString(randomGenerator.nextInt(1000));
		s.title=PropertiesReader.getValue("skillTitle")+randomString;
		s.category=PropertiesReader.getValue("skillCategory");
		s.status=PropertiesReader.getValue("skillStatus");
		s.description=PropertiesReader.getValue("skillDes");
		return s;
	}
	public static Skill getForEdit(){
		Skill s=new Skill();
		Random randomGenerator = new Random();
		String randomString = Integer.toString(randomGenerator.nextInt(1000));
		s.title=PropertiesReader.getValue("skill_title")+randomString;
		s.category=PropertiesReader.getValue("skill_category");
		s.status=PropertiesReader.getValue("skill_status");
		s.description=PropertiesReader.getValue("skill_des");
		return s;
	}
	
	
}
