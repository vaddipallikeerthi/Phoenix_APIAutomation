package com.utility;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import static com.api.constants.Roles.*;

import com.api.constants.Roles;
import com.pojos.UserCredentials;

import io.restassured.http.ContentType;


public class AuthToken {
	
	private AuthToken()
	{
		
	}
	public static String getToken(Roles role) throws IOException {
		UserCredentials userCredentials = null;
		
		if(role==FD)
		{
			userCredentials=new UserCredentials("iamfd","password");
		}
		else if(role==SUP)
		{
			userCredentials=new UserCredentials("iamsup","password");
		}
		else if(role==ENG)
		{
			userCredentials=new UserCredentials("iameng","password");
		}
		else if(role==QC)
		{
			userCredentials=new UserCredentials("iamqc","password");
		}
		
		
		String token=given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.body(userCredentials)
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.body("message",Matchers.equalTo("Success"))
			.log().ifValidationFails()
			.extract()
			.body()
			.jsonPath()
			.getString("data.token");
		
		
		return token;
			
			
		
	}

}
