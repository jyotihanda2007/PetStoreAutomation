package api.test;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payloads.User;
import io.restassured.response.Response;


import com.github.javafaker.Faker;



public class Usertests {
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload= new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
		logger.debug("Debugging..............");
}
	
	@Test(priority=1)
	public void testPostUser()
	
	{
		logger.info("*************Creating User**************");
		Response response= userEndpoints.CreateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testgetUserByName()
	{
		logger.info("*************Reading User Info**************");
	Response response= userEndpoints.ReadUser(this.userPayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("************User Info is Displayed**************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	
	{
		logger.info("*************Updating User**************");
		//update date using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= userEndpoints.UpdateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking Data after Update
	
		Response responseAfterUpdate= userEndpoints.ReadUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("************* User is updated**************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("*************Deleting User**************");
		Response response=userEndpoints.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************User is Deleted**************");
	}
	
	}




