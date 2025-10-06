package com.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.utility.AuthToken;
import com.utility.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPI {

	@Test
	public void masterAPITest() throws IOException
	{
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.headers("Authorization", AuthToken.getToken(Roles.FD))
			.contentType("") //for every post method default content type is application/url-formencoded
			.log().all()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(200)
			.time(Matchers.lessThan(1000L))
			.body("message", Matchers.equalTo("Success"))
			.body("data", Matchers.notNullValue())
			.body("data", Matchers.hasKey("mst_oem"))
			.body("data", Matchers.hasKey("mst_model"))
			.body("$", Matchers.hasKey("message"))
			.body("data.mst_oem.size()", Matchers.greaterThan(0))
			.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
			.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/MasterAPISchema.json"));
		}
	
	@Test
	public void invalidTokenMasterAPI() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.headers("Authorization", "")
		.contentType("") //for every post method default content type is application/url-formencoded
		.log().all()
	.when()
		.post("master")
	.then()
		.log().all()
		.statusCode(401);
	}

}
