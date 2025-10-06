package com.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Roles.*;
import com.utility.AuthToken;
import com.utility.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {
	@Test
	public void userDetailsAPIRequest() throws IOException
	{
		
		Header authHeader=new Header("Authorization",AuthToken.getToken(FD));
			given()
				.baseUri(ConfigManager.getProperty("BASE_URI"))
				.header(authHeader)
				.accept(ContentType.JSON)
				.log().uri()
				.log().method()
				.log().body()
				.log().headers()
			.when()
				.get("userdetails")
			.then()
				.log().all()
				.statusCode(200)
				.time(Matchers.lessThan(10000L))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/UserDetailsResponseSchema_FD.json"));
			
			
	}

}
