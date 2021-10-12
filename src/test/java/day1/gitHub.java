package day1;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class gitHub {
	
	@BeforeTest
	public void beforeTest() {
		baseURI ="https://api.github.com/user/repos";
		authentication=RestAssured.oauth2("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");
		
	}
	
	@Test(enabled=false)
	public void gettingAllRepo() {
		given()
		.auth()
		.oauth2("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")
	    .when()
	    .get("https://api.github.com/user/repos")
	    .then()
	    .log()
	    .body()
	    .statusCode(200);
	
	
	}

	@Test(enabled=true)
	public void createRepo() {
		JSONObject data=new JSONObject();
		data.put("name", "RestAssurede12");
		data.put("description", "I am created By RestAssured Tool");
		data.put("homepage", "https://github.com/KathiravanRamalingam");


		given()
		.auth()
		.oauth2("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")
		.header("Content-Type","application/json")
		.body(data.toJSONString())
	    .when()
	    .post(baseURI)
	    .then()
	    .log()
	    .body()
	    .statusCode(201)
	    .time(Matchers.lessThan(8000L),TimeUnit.MILLISECONDS);
	
	
	}


}
