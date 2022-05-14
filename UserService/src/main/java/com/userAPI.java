package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.userModel;
import java.io.IOException;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
/**
 * Servlet implementation class userAPI
 */
@WebServlet("/userAPI")
public class userAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	userModel userObj = new userModel();
	
	/** Convert request parameters to a Map
	 **/
	private static Map<String, String> getParasMap(HttpServletRequest request) 
	{ 
		Map map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	 
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
    public userAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sending values to insert function
		String output = userObj.insertService(request.getParameter("userName"), 
				request.getParameter("userNic"), 
				request.getParameter("userEmail"), 
				request.getParameter("userAdd"),
				request.getParameter("userTeleNo"),
				request.getParameter("AcctNo")); 
		
		//sending output to client
		response.getWriter().write(output); 
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		//sending values to update function
		String output = userObj.updateService(	paras.get("hidUserIDsave").toString(),
												paras.get("userName").toString(), 
												paras.get("userNic").toString(), 
												paras.get("userEmail").toString(), 
												paras.get("userAdd").toString(),
												paras.get("userTeleNo").toString(),
												paras.get("AcctNo").toString());
		
		//check whether value is coming correctly
		System.out.println(request.getParameter("userID"));
		
		//sending output to client
		 response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		//taking values from map and sending to delete function
		String output = userObj.deleteService(paras.get("userID").toString()); 
		System.out.println(paras.get("userID").toString());//check whether value is coming correctly
		//sending output to client
		response.getWriter().write(output); 
	}
	
	
	


}