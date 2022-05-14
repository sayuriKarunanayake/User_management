package service;

import com.userModel;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Service")
public class Service {
	
userModel serviceObj = new userModel();
	
	@POST
	@Path("/insert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertService(@FormParam("userName") String userName, 
	 @FormParam("userNic") String userNic, 
	 @FormParam("userEmail") String userEmail, 
	 @FormParam("userAdd") String userAdd,
	 @FormParam("userTeleNo") String userTeleNo,
	 @FormParam("AcctNo") String AcctNo ) 
	{ 
	 String output = serviceObj.insertService(userName, userNic, userEmail, userAdd, userTeleNo,AcctNo); 
	return output; 
	}
	
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readService() 
	 { 
	 return serviceObj.readService(); 
	}
	
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateService(String serviceData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String userID = serviceObject.get("userID").getAsString(); 
	 String userName = serviceObject.get("userName").getAsString(); 
	 String userNic = serviceObject.get("userNic").getAsString(); 
	 String userEmail = serviceObject.get("userEmail").getAsString(); 
	 String userAdd = serviceObject.get("userAdd").getAsString();
	 String userTeleNo = serviceObject.get("userTeleNo").getAsString();
	 String AcctNo = serviceObject.get("AcctNo").getAsString();
	 String output = serviceObj.updateService(userID, userName, userNic, userEmail, userAdd, userTeleNo,AcctNo); 
	return output; 
	}


	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteService(String serviceData) 
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text(); 
	 String output = serviceObj.deleteService(userID); 
	return output; 
	}
	

}
