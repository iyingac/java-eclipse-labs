import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class account {
	
	public String Name ;
	public String Id ;
	public String Description ;
	
	
	public account(String AccId)
	{
	      Connection connection = null;
	      try {
	        connection = DatabaseUrl.extract().getConnection();

	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.Account where sfid="+AccId+"");

	        while (rs.next()) {
	          Name= rs.getString("Name");
	          Id=rs.getString("ID");
	          Description=rs.getString("ID");
	        }
	      } 
	      catch (Exception e) {	      } 
	}
	
	public String GetDerails(){
		return  "Id = "+Id + " , Name = " +Name +" , Descrition =" + Description;
	}
}