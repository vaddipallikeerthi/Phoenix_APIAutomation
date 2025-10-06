package com.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.utility.AuthToken.*;

import com.api.constants.Roles;
import com.utility.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() throws IOException
	{
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.accept(ContentType.JSON)
			.headers("Authorization", getToken(Roles.FD))
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(200)
			.body("message", Matchers.equalTo("Success"))
			.time(Matchers.lessThan(1000L))
			.body("data", Matchers.notNullValue())
			.body("data.size()",Matchers.equalTo(3))
			.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
			.body("data.label", Matchers.not(Matchers.everyItem(Matchers.emptyOrNullString())))
			.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))	
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CountAPISchema.json"));
		
		
		
	}
	
	@Test
	public void countAPImissingAuthToken() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.accept(ContentType.JSON)
		
	.when()
		.get("/dashboard/count")
	.then()
		.log().all()
		.statusCode(401);
	}

}
