package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response createUser(User payload) {

		Response response = given()
			.accept(ContentType.JSON) //what type of content request will accept 
			.contentType(ContentType.JSON) //what type of content we are sending
			.body(payload)
		
		
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	public static Response getUser(String username ) {

		Response response = given()
			.accept(ContentType.JSON) //Here we are not sending contentType because we are not sending data
			.pathParam("username", username) // In Routes class check get request require username
		
		
		.when()
			.get(Routes.get_url);
		
		return response;
	}

	public static Response putUser(String username, User payload ) {

		Response response = given()
				.accept(ContentType.JSON) //what type of content request will accept 
				.contentType(ContentType.JSON) //what type of content we are sending
				.pathParam("username", username) // In Routes class check get request require username
				.body(payload)
			
			
			.when()
				.put(Routes.put_url);
			
			return response;
	}
	
	public static Response delUser(String username ) {

		Response response = given()
			.accept(ContentType.JSON) //what type of content request will accept 
			.pathParam("username", username) // // In Routes class check get request require username  
		
		
		.when()
			.delete(Routes.del_url);
		
		return response;
	}
	
}
