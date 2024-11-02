package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Reso_Register;
import resources.BaseUtils;

public class Register_success extends BaseUtils{
	
	private Response respInfo;
	@Given("API page is responsive and {string} and {string} and {string} in body")
	public void api_page_is_responsive_and_email_and_password_in_body(String user, String email, String pass) throws FileNotFoundException {
		Reso_Register reg=new Reso_Register();
		reg.setUsername(user);
		reg.setEmail(email);
		reg.setPassword(pass);
		
		reqInfo=given()
				.spec(requestSpecification())
				.body(reg);
	}
	@When("we use POST method to send request")
	public void we_use_post_method_to_send_request() throws FileNotFoundException {
				respInfo= reqInfo
				.when()
					.post(readProperty("register"))
				.then()
				.spec(responseSpecification())
				.extract().response();
	}
	@Then("we get id with token in response")
	public void we_get_id_with_token_in_response() {
		String respon=respInfo.asString();
		JsonPath jp=new JsonPath(respon);
		System.out.println(jp.getString("id"));
		System.out.println(jp.getString("token"));

		
	}
}
