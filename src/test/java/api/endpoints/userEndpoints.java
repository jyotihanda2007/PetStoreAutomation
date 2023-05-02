
package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payloads.User;



public class userEndpoints {

	
	public static Response CreateUser(User payload)
	{
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
	.when()
		.post(Routes.post_url);
		return response;
	}
	public static Response ReadUser(String UserName)
	{
	Response response=given()
		.pathParam("username", UserName)
	.when()
		.get(Routes.get_url);
		return response;
	}
	
	public static Response UpdateUser(String UserName,User payload)
	{
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", UserName)
		.body(payload)
	.when()
		     .put(Routes.update_url);
		return response;
	}
	
	public static Response DeleteUser(String UserName)
	{
	Response response=given()
		           .pathParam("username", UserName)
	.when()
		           .delete(Routes.delete_url);
		return response;
	}
}
