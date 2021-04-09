package tests;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;
import files.Payload;
import files.resources;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;


public class Twitter {
    
    String ConsumerKey="7C1las31LFZ1sqh8VOHkhAI8M";
    String ConsumerSecret="i8cjjdZIbJwcWhDy0oATBfeOztJ7CNAK82YQcUVMfdg0NKOE7p";
    String Token="1366702057093099523-vcxMlPU6tkUOgH6VU80AyhVZXFtqKY";
    String TokenSecret="o7RsPSdjhEUC9jbXU1HgY40uiWewgJE5NdD3obQc5XpRy";

 

    @Test
    public void getTweet() {
        
        // Getting latest tweet
        
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
        Response res=given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
        queryParam("count","1").
        when().get("/home_timeline.json").then().extract().response();
        
        String responseString=res.asString();
        System.out.println(responseString);
        
        JsonPath js=new JsonPath(responseString);
        String id=js.get("id").toString();
        System.out.println(id);
        String text=js.get("text").toString();
        System.out.println(text);
         
    }
    
    /*
    @Test
    public void createTweet() {
        
        // creating or posting tweet
        
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
        Response res=given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
        queryParam("status","This is my tweet for automation").
        when().post("/update.json").then().extract().response();
        
        String responseString=res.asString();
        System.out.println(responseString);
        
        JsonPath js=new JsonPath(responseString);
        String id=js.get("id").toString();
        System.out.println(id);
        String text=js.get("text").toString();
        System.out.println(text);
         
    }
    
    */
    @Test    
    public void deleteTweet() {
        
        // deleting tweet
        
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
        Response res=given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
        queryParam("status","This is my tweet for automation duplicate").
        when().post("/update.json").then().extract().response();
        
        String responseString=res.asString();
        System.out.println(responseString);
        JsonPath js=new JsonPath(responseString);
        String id=js.get("id").toString();
        System.out.println(id);
        String text=js.get("text").toString();
        System.out.println(text);
        
        given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
        when().post("/destroy/"+id+".json").then().assertThat().statusCode(200);
         
    }
}
 