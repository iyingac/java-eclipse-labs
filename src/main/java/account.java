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
	      Map<String, Object> attributes = new HashMap<>();
	      try {
	        connection = DatabaseUrl.extract().getConnection();

	        Statement stmt = connection.createStatement();
	        //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
	        //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	        ResultSet rs = stmt.executeQuery("SELECT * FROM salesforce.Account where sfid='"+AccId+"'");

	        ArrayList<String> output = new ArrayList<String>();
	        while (rs.next()) {
	          //output.add( "Read from DB: " + rs.getString("Name") + ", Id: " + rs.getString("Id") +"");
	          Name= rs.getString("Name");
	          Id=rs.getString("ID");
	          Description=rs.getString("ID");
	        }

	        attributes.put("results", output);
	        //return new ModelAndView(attributes, "db.ftl");
	      } catch (Exception e) {
	        attributes.put("message", "There was an error: " + e);
	        //return new ModelAndView(attributes, "error.ftl");
	      } finally {
	        if (connection != null) try{connection.close();} catch(SQLException e){}
	      }
	}
	
	public String GetDerails(){
		return  "Id = "+Id + " , Name = " +Name +" , Descrition =" + Description;
	}
}