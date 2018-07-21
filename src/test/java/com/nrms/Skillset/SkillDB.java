package com.nrms.Skillset;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nrms.DBConnection.DBContext;

public class SkillDB {

	Skill skill;
	ResultSet rs;
	String query;
	public Skill getSkill(String skillName){

		DBContext db=new DBContext();
		db.connect();
		try{
			db.statement=db.conn.createStatement();
			skill=new Skill();
			query="SELECT skills.skill_name,skills.description,categories.name,status_codes.sc_title from "
					+ "categories,skills JOIN status_codes ON status_codes.sc_id=skills.sc_id "
					+ "WHERE categories.id=skills.category_id AND skills.skill_name='"+skillName+"'";
			rs=db.statement.executeQuery(query);
			if(rs.next()){
				skill.title=rs.getString("skill_name");
				skill.status=rs.getString("sc_title");
				skill.description=rs.getString("description");
				skill.category=rs.getString("name");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}		
		db.close();
		return skill;
	}
	
	
}
