package tests;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Third {
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		 
		FileInputStream fis=new FileInputStream("C:\\javascripts\\APIAutomation\\src\\files\\data.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}
	
	@Test
	
	public void APITest1()  {
		
		//step 1
		  
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		Response res=given().
		
		header("Content-Type","application/json").
		body("{\"username\": \"skdaya\",\"password\": \"Root123#\" }").
		when().
		post("/rest/auth/1/session").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		//step 2
		
	/*	String responseString= res.asString();
		System.out.println(responseString);
		JsonPath js=new JsonPath(responseString);
		String finame=js.get("data[0].first_name");
		System.out.println(finame); */
	}

}
