package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import pojo.Body_Create;
import pojo.Create_resopnse_body;
import resources.BaseUtils;

public class Create_steps extends BaseUtils {
	private Response res;
	private JsonPath jp;
@Given("api is responsive we send {string} and {string} in body")
public void api_is_responsive_we_send_and_in_body(String name, String job) throws FileNotFoundException {
	Body_Create bc=new Body_Create();
	bc.setName(name);
	bc.setJob(job);
	
	reqInfo=given()
			.spec(requestSpecification())
			.body(bc)
			.log().all();
}
@When("POST method is used to send request")
public void post_method_is_used_to_send_request() throws FileNotFoundException {
	res=reqInfo
			.when()
				.post(readProperty("create"))
			.then()
			.log().all()
				.spec(responseSpecification())
				.extract().response();
	
	
}
@Then("we get response code {int} with body")
public void we_get_response_code_with_body(Integer int1) {
	String respon=res.asString();
		jp=new JsonPath(respon);
	System.out.println(jp.getString("id"));
	Create_resopnse_body crb=new Create_resopnse_body();
}
@Then("we update values using put")
public void we_update_values_using_put() throws FileNotFoundException {
	Body_Create bc=new Body_Create();
	bc.setName("Raj");
	bc.setJob("CDO");
	given()
		.spec(requestSpecification())
		.body(bc)
		.log().all()
	.when()
		.put(readProperty("create")+"/"+jp.getString("id"))
	.then()
	.log().all()
		.assertThat().statusCode(200);
}
//@SuppressWarnings("deprecation")
@Then("we update values using patch request")
public void we_update_values_using_patch_request() throws FileNotFoundException {
	Body_Create bc=new Body_Create();
	bc.setName("ShreeRaj");
	
	String patchResponse=
	given()
		.spec(requestSpecification())
		.body(bc)
		.log().all()
	.when()
		.patch(readProperty("create")+"/"+jp.getString("id"))
	.then()
		.log().all()
		.assertThat().statusCode(200)
		.extract().response().asString();
//	JsonPath jp=new JsonPath(patchResponse);
//	Assert.assertEquals("ShreeRaj", jp.getString("name"));
		
}
@Then("we delete user by using created id")
public void we_delete_user_by_using_created_id() throws FileNotFoundException {
    
	given()
		.spec(requestSpecification())
		.log().all()
	.when()
		.delete(readProperty("create")+"/"+jp.getString("id"))
	.then()
	.log().all()
		.assertThat().statusCode(204);
}


}
