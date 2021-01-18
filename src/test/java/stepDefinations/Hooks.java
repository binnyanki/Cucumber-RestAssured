package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	StepDefination s = new StepDefination();
	@Before("@deletePlace")
	public void before()
	{
		if(StepDefination.place_id==null)
		{
			s.add_place_payload("Shaw House","9518711374","Pune","https://google.com","English","Shoe park","shop");
			s.user_calls_with_http_request("AddPlaceAPI", "POST");
			s.user_extracts_and_verifies_using("place_id", "Shaw House", "getPlaceAPI");
		}
	}
}
