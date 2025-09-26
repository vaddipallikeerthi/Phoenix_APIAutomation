package com.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

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
		
		Header authHeader=new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3NTgzMDE0Mzl9.TJel7JHYaVNf6LtQRHi7CAhdgGwhdHqzHN9g1yiriYs");
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
				.statusCode(200)
				.time(Matchers.lessThan(10000L))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/UserDetailsResponseSchema_FD.json"));
			
			
	}

}
