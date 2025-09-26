package com.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.pojos.UserCredentials;
import com.utility.ConfigManager_OLD;
import com.utility.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginAPITest {
	@Test
	public void LoginAPITest() throws IOException
	{
		
		//System.out.println(System.getProperty("env"));
		
	
		
		
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			 //.body("{\"username\":\"iamfd\", \"password\":\"password\"}")
			.body(userCredentials)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.log().uri()
			.log().body()
			.log().method()
			.log().headers()
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.time(lessThan(10000L))
			.body("message", equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginResponseSchema.json"));
			
		
	}
	
	

	
		
		

	

}
