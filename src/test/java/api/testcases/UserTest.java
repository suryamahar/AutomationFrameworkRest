package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	private void genrerateTestData() {
		
		//data created using Faker dependency
		
		faker = new Faker();	
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password());

	}
	
	@Test(priority = 1)
	public void testCreateUser() {
		Response response= UserEndpoints.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		Response response= UserEndpoints.getUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		userPayload.setUsername(faker.name().firstName());
		Response response= UserEndpoints.putUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}

	@Test(priority = 4)
	public void testDeletUser() {
		Response response= UserEndpoints.delUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
