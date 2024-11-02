package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseUtils {

	protected RequestSpecification req;
	protected RequestSpecification reqInfo;
	
	public RequestSpecification requestSpecification() throws FileNotFoundException
	{
		

		if(req == null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder()
					.setBaseUri(readProperty("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
		}
	return req;
	}
	public ResponseSpecification responseSpecification()
	{
		ResponseSpecification res=new ResponseSpecBuilder()
						.expectContentType(ContentType.JSON)
						.build();
		return res;
	}
	public String readProperty(String key) throws FileNotFoundException
	{
		Properties prop=new Properties();
		FileInputStream fls=new FileInputStream("C:\\Users\\Swapnil\\eclipse-workspace\\Reqres\\src\\test\\java\\resources\\global.properties");
		try {
			prop.load(fls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String value=prop.getProperty(key);
		return value;
	}
	
	
}
