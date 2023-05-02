package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payloads.User;
import api.utilities.dataproviders;
import io.restassured.response.Response;

public class DDTests {

	
	@Test(priority=1,dataProvider="Data",dataProviderClass=dataproviders.class)
	public void testPostUser(String userID,String userName,String Fname,
			String lname,String useremail,String pwd,String ph)
	{
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(Fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response= userEndpoints.CreateUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=dataproviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response= userEndpoints.DeleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
