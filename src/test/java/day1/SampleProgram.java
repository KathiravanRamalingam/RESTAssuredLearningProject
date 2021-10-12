package day1;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SampleProgram {
	@BeforeTest
	public void beforeTest() {
		baseURI ="https://api.github.com/user/repos";
		authentication=RestAssured.oauth2("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");
		
	}
	
	@Test
	public void F()
	{
		System.out.println("Hello");
	}
	
	
	
	@Test
	public void F2()
	{
		System.out.println("Helllo Kathir ");
	}

}
