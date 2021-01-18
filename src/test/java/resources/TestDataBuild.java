package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceSeriliaze;
import pojo.Location;

public class TestDataBuild {

	public static AddPlaceSeriliaze addPlaceAPI(String name, String phoneNumber, String address, String website, String language, String types1, String types2)
	{
		AddPlaceSeriliaze p = new AddPlaceSeriliaze();
		Location l = new Location();
		List<String> list = new ArrayList<String>();
		l.setLat(-38.383494);
		  l.setLng(33.427362); 
		  list.add(types1);
		  list.add(types2);
		  p.setAccuracy(50);
		  p.setAddress(address);
		  p.setLanguage(language);
		  p.setName(name);
		  p.setWebsite(website);
		  p.setPhone_number(phoneNumber);
		  p.setTypes(list);
		  p.setLocation(l);
		  
		  return p;
	}
	
	public static String deletePlacePayload(String place_id)
	{
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
	}
}
