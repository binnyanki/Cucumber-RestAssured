package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	ResponseSpecification res = null;
	RequestSpecification request=null;
	Response response= null;
	PrintStream log = null;
	public RequestSpecification requestSpecification()
	{
		if(req==null) {
		try {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getPropertiesValue("baseURI"))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		return req;
	}
	public ResponseSpecification responseSpecification()
	{
		res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
				
		return res;
	}
	
	public String getPropertiesValue(String key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getResponseValue(Response response, String key)
	{
		JsonPath js = new JsonPath(response.asString());
		
		return js.get(key).toString();
	}

}
