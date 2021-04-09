package tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class First {

	@Test
	public void APITest() {
		
		
		RestAssured.baseURI= "https://reqres.in/";
		Response res=given().
		    param("page","2").log().all().
		     when().
		     get("api/users").
		     then().assertThat().log().body().
		     statusCode(200).and().contentType(ContentType.JSON).and().
		   //  body("data[0].first_name",equalTo("Michael")).and().
		   //  body("data[1].avatar",equalTo("https://reqres.in/img/faces/8-image.jpg")).and().
		   //  body("page",equalTo(2)).and().header("Server", "cloudflare");
		     
		     extract().response();
		    
		    String responseString=res.asString();
		    JsonPath js=new JsonPath(responseString);
		    String fname=js.get("data[0].first_name");
		    System.out.println(fname);
		    int count=js.get("data.size()");
		    System.out.println(count);
		  
		    for(int i=0;i<count;i++) {
		    	String First_Name=js.get("data["+i+"].first_name");
		    	System.out.println(First_Name); 
		    }

	}

}
