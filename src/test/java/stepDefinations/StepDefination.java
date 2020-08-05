package stepDefinations;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceSeriliaze;
import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	
	//AddPlaceSeriliaze p = TestDataBuild.addPlaceAPI();
	RequestSpecification request=null;
	Response response= null;
	JsonPath js = null;
	static String place_id;

	@Given("Add place payload {string} {string} {string} {string} {string} {string} {string}")
	public void add_place_payload(String name, String phoneNumber, String address, String website, String language, String types1, String types2) {
		 
			request = given().log().all().spec(requestSpecification()).body(TestDataBuild.addPlaceAPI(name,phoneNumber,address,website,language,types1,types2));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
		try {
			if(httpMethod.equalsIgnoreCase("POST")) {
			response = request.when().post(getPropertiesValue(resource))
					.then().log().all().spec(responseSpecification()).extract().response();
			}
			if(httpMethod.equalsIgnoreCase("GET")) {
				response = request.when().get(getPropertiesValue(resource))
						.then().log().all().spec(responseSpecification()).extract().response();
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}

	@Then("The API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		
		js = new JsonPath(response.asString());
		assertEquals(js.get(key).toString(),expectedValue);
	    
	}
	
	@Then("User extracts {string} and verifies {string} using {string}")
	public void user_extracts_and_verifies_using(String responseKey, String expectedValue, String resource) {
	    
		place_id = getResponseValue(response, responseKey);
		
		request = given().log().all().spec(requestSpecification()).queryParam(responseKey, place_id);
		
		user_calls_with_http_request(resource, "GET");
		
		String actualname = getResponseValue(response,"name");
		
		assertEquals(actualname,expectedValue);
	}
	
	@Given("deletePlace Payload")
	public void deleteplace_Payload() {
	    
		request = given().log().all().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(place_id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
