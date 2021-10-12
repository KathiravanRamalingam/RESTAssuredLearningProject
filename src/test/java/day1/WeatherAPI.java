package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
	
	@Test(enabled=true,description="Getting weather information of specific city")
	  public void getWeather() {
		  RestAssured.given()		// Some Pre-condition like Authentication
		  .when()					// Performs some steps
		  .get("https://api.openweathermap.org/data/2.5/weather?q=hyderabad&appid=f63ec4e7bb626f60a16359baccd0d1fd")
		  .then()			// Some Post_Condition like Verification
		  .log()			//Print data in Console
		  .body()			//Printing body
		  .statusCode(200);
		  
	  }
	  @Test(enabled=true,description="Getting weather information of specific city")
	  public void getWeather2() {
		Response res=  RestAssured.given()		// Some Pre-condition like Authentication
		  .when()					// Performs some steps
		  .get("https://api.openweathermap.org/data/2.5/weather?q=hyderabad&appid=f4959247acf5d4b973271bc14839636b");
		//  .then()			// Some Post_Condition like Verification
		//  .log()			//Print data in Console
		//  .body()
		//  .statusCode(200);
		  
		System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
		
		Assert.assertEquals(res.getStatusCode(), 200);  // Checking with help of TestNG Assertion
	  }
	  @Test(enabled=true,description="Getting weather information of specific city")
	  public void getWeather3() {
		  RestAssured.given()
		  .queryParam("q", "hyderabad")// Some Pre-condition like Authentication
		  .queryParam("appid", "f4959247acf5d4b973271bc14839636b")
		  .when()					// Performs some steps
		  .get("https://api.openweathermap.org/data/2.5/weather")
		  .then()			// Some Post_Condition like Verification
		  .log()			//Print data in Console
		  .body()			//Printing body
		  .statusCode(200);
		  
	  }
	  @Test(enabled=true,description="Getting weather information of specific city")
	  public void getWeather4() {
		 Map<String,String> param=new HashMap<String, String>();
		  param.put("q", "hyderabad");	// Some Pre-condition like Authentication
		  param.put("appid", "f4959247acf5d4b973271bc14839636b");
		  RestAssured.given()
		  .queryParams(param)
		  .when()					// Performs some steps
		  .get("https://api.openweathermap.org/data/2.5/weather")
		  .then()			// Some Post_Condition like Verification
		  .log()			//Print data in Console
		  .body()			//Printing body
		  .statusCode(200);
		  
	}	


}
