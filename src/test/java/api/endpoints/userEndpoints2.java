
package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payloads.User;

public class userEndpoints2 {

	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response CreateUser(User payload) {
		String post_url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);
		return response;
	}

	public static Response ReadUser(String UserName) {
		String get_url = getURL().getString("get_url");
		Response response = given().pathParam("username", UserName).when().get(get_url);
		return response;
	}

	public static Response UpdateUser(String UserName, User payload) {
		String update_url = getURL().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", UserName).body(payload).when().put(update_url);
		return response;
	}

	public static Response DeleteUser(String UserName) {

		String delete_url = getURL().getString("delete_url");
		Response response = given().pathParam("username", UserName).when().delete(delete_url);
		return response;
	}
}
