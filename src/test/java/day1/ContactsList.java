package day1;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

 

import static io.restassured.RestAssured.*;

public class ContactsList {
	String id;
	  @Test(enabled=false,description="Getting all Contact List")
	  public void getContactListInfo() {
		  given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts\n")
		  
		  .then()
		  .log()
		  .body()
		  .statusCode(200);
	  }
	  
	  
	  @Test(enabled=false,description="Getting specific Contact ")
	  public void getSpecificContact() {
		  given()
		  .when()
		  .get("http://3.13.86.142:3000/contacts/5e066f8a2369c5050ec00f06\n")
		  .then()
		  .log()
		  .body()
		  .statusCode(200);
	  }
	  @Test(enabled=false,description="Getting specific Contact ")
	  public void getSpecificContact2() {
		  Response res = given()
				  .when()
				  		.get("http://3.13.86.142:3000/contacts/5e066f8a2369c5050ec00f06\n");
		  	System.out.println(res.getTime());
		  	
		   res.then()
		  .log()
		  .body()
		  .statusCode(200);
	  }

		  @Test(enabled=true,description="Getting specific Contact ")
		  public void addingContact() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "hyderabad");
		  loc.put("country", "India");
		  emp.put("JobTitle", "GET");
		  emp.put("company", "LTI");
		  details.put("firstName", "Vamshi");
		  details.put("lastName","Krishna Reddy");
		  details.put("email", "Secret007@gmail.com");
		  details.put("location", loc);
		  details.put("employer", emp);
		  
		ExtractableResponse<Response> ex=	 given()
			 .header("Content-Type","application/json")
			 .body(details.toJSONString())
			 .when()
			 .post("http://3.13.86.142:3000/contacts")
			 .then()
			 .log()
			 .body()
			 .statusCode(200)
			 .extract();
			// .path("_id");
		
			 id=ex.path("_id");
			 System.out.println(ex.path("_id"));
			 System.out.println(ex.path("firstName"));
			 System.out.println(ex.path("lastName"));
			 System.out.println(ex.path("location.city"));
			 
		  }
	  
		  @Test(enabled=true,dependsOnMethods="addingContact",description="Getting specific Contact ")
		  public void UpdatingContact() {
		  JSONObject details=new JSONObject();
		  JSONObject loc=new JSONObject();
		  JSONObject emp=new JSONObject();
		  
		  loc.put("city", "Kumbakonam");
		  loc.put("country", "India");
		  emp.put("JobTitle", "Developer");
		  emp.put("company", "Accenture");
		  details.put("firstName", "Elakya");
		  details.put("lastName","Kathiravan");
		  details.put("email", "elakiyakathiravan@gmail.com");
		  details.put("location", loc);
		  details.put("employer", emp);
		  
		  		given()
			 .header("Content-Type","application/json")
			 .body(details.toJSONString())
			 .when()
			 .put("http://3.13.86.142:3000/contacts/"+id)
			 .then()
			 .log()
			 .body()
			 .statusCode(204);
	}
		  @Test(enabled=true,dependsOnMethods="UpdatingContact",description="Getting specific Contact ")
		  public void getSpecificContact3() {
			  given()
			  .when()
			  .get("http://3.13.86.142:3000/contacts/"+id)
			  .then()
			  .log()
			  .body()
			  .statusCode(200);
		  }
		  
		  @Test(enabled=true,dependsOnMethods="getSpecificContact3",description="Deleting specific Contact ")
		  public void deleteRecord() {
		  		
		  		
	  		given()

		  	.header("Content-Type", "application/json")	
		  	.when()
		  	
		  	.delete("http://3.13.86.142:3000/contacts/"+ id)
	  		.then()
			  .log()
			  .body()
			  .statusCode(204);
		  		
		  	}
		  
		  @Test(enabled=true,dependsOnMethods="deleteRecord",description="Deleting specific Contact ")
		  public void getSpecificContact4() {	
	  		given()
		  	.when()
		  	
		  	.delete("http://3.13.86.142:3000/contacts/"+ id)
	  		.then()
			  .log()
			  .body()
			  .statusCode(404);
		  		
		  	}
		  @Test(enabled=false, description="adding contact with missing details")
		  public void addingContactMissing() {
			  JSONObject details = new JSONObject();
			  JSONObject location = new JSONObject();
			  JSONObject emp = new JSONObject();
			  
			  location.put("city", "Mumbai");
			  location.put("country", "India");
			  
			  emp.put("jobTitle", "QA");
			  emp.put("company", "LTI");
			  
			  details.put("firstName", null);
			  details.put("lastName", "Smith");
			  details.put("email", "john@email.com");
			  details.put("location", location);
			  details.put("employer", emp);
			  
			  
			  String error = given()
			  .header("Content-Type", "application/json")
			  .body(details.toJSONString())
			  .when()
			  .post("http://3.13.86.142:3000/contacts")
			  .then()
			  .log()
			  .body()
			  .statusCode(400)
			  .extract()
			  .path("err");
			  
			  Assert.assertTrue(error.contains("firstName: First Name is required"));
		  }
		  
		  @Test(enabled=true, description="adding contact with too many character")
		  public void addingContactBigSize() {
			  JSONObject details = new JSONObject();
			  JSONObject location = new JSONObject();
			  JSONObject emp = new JSONObject();
			  
			  location.put("city", "MumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbai");
			  location.put("country", "India");
			  
			  emp.put("jobTitle", "QA");
			  emp.put("company", "LTI");
			  
			  details.put("firstName", "joe");
			  details.put("lastName", "Smith");
			  details.put("email", "john@email.com");
			  details.put("location", location);
			  details.put("employer", emp);
			  
			  
			  String error = given()
			  .header("Content-Type", "application/json")
			  .body(details.toJSONString())
			  .when()
			  .post("http://3.13.86.142:3000/contacts")
			  .then()
			  .log()
			  .body()
			  .statusCode(400)
			  .extract()
			  .path("err");

			  Assert.assertTrue(error.contains("is longer than the maximum allowed length (30)"));
	}
		  
		  @Test(enabled=true, description="adding firstname with numbers")
		  public void addingInvalidFirstName() {
			  JSONObject details = new JSONObject();
			  JSONObject location = new JSONObject();
			  JSONObject emp = new JSONObject();
			  
			  location.put("city", "Mumbai");
			  location.put("country", "India");
			  
			  emp.put("jobTitle", "QA");
			  emp.put("company", "LTI");
			  
			  details.put("firstName", "joe12334");
			  details.put("lastName", "Smith112313");
			  details.put("email", "john@email.com");
			  details.put("location", location);
			  details.put("employer", emp);
			  
			  
			  String error = given()
			  .header("Content-Type", "application/json")
			  .body(details.toJSONString())
			  .when()
			  .post("http://3.13.86.142:3000/contacts")
			  .then()
			  .log()
			  .body()
			  .statusCode(400)
			  .extract()
			  .path("err");

			  Assert.assertTrue(error.contains("Validator failed for path `lastName` with value"));
			  Assert.assertTrue(error.contains("Validator failed for path `firstName` with value"));

	}
		  
		  @Test(enabled=true, description="@  is Missing in EmailId")
		  public void addingInvalidEmailId() {
			  JSONObject details = new JSONObject();
			  JSONObject location = new JSONObject();
			  JSONObject emp = new JSONObject();
			  
			  location.put("city", "Mumbai");
			  location.put("country", "India");
			  
			  emp.put("jobTitle", "QA");
			  emp.put("company", "LTI");
			  
			  details.put("firstName", "joe");
			  details.put("lastName", "Smith");
			  details.put("email", "john.com");
			  details.put("location", location);
			  details.put("employer", emp);
			  
			  
			  String error = given()
			  .header("Content-Type", "application/json")
			  .body(details.toJSONString())
			  .when()
			  .post("http://3.13.86.142:3000/contacts")
			  .then()
			  .log()
			  .body()
			  .statusCode(400)
			  .extract()
			  .path("err");
			  System.out.println(error);

			 // Assert.assertTrue(error.contains("Validator failed for path `lastName` with value"));

	}
}