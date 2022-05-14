package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class userModel {

	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement", "root", "root"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	 
	 
	 
//insert
	public String insertService(String userName, String userNic, String userEmail, String userAdd, String userTeleNo, String AcctNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into user (`userID`,`userName`,`userNic`,`userEmail`, `userAdd`,`userTeleNo`, `AcctNo`)"
	 + " values (?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, userName); 
	 preparedStmt.setString(3, userNic); 
	 preparedStmt.setString(4, userEmail); 
	 preparedStmt.setString(5, userAdd);
	 preparedStmt.setString(6, userTeleNo);
	 preparedStmt.setString(7, AcctNo);
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 String newUser = readService();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newUser + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 	
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	
//read	
	
	public String readService() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>User ID</th><th>Name</th>"+
    "<th>NIC</th>" +
	 "<th>Email</th>" + 
	 "<th>Address</th>" +
	 "<th>Telephone no.</th>"+
	 "<th>Account No.</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from user"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String userID = Integer.toString(rs.getInt("userID")); 
	 String userName = rs.getString("userName"); 
	 String userNic = rs.getString("userNic"); 
	 String userEmail = rs.getString("userEmail");
	 String userAdd = rs.getString("userAdd");
	 String userTeleNo = rs.getString("userTeleNo");
	 String AcctNo = rs.getString("AcctNo");
	 // Add into the html table
	 output += "<tr><td>" + userID + "</td>"; 
	 output += "<td>" + userName + "</td>"; 
	 output += "<td>" + userNic + "</td>"; 
	 output += "<td>" + userEmail + "</td>";
	 output += "<td>" + userAdd + "</td>";
	 output += "<td>" + userTeleNo + "</td>";
	 output += "<td>" + AcctNo + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-warning' data-userid='" + userID + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-userid='" + userID + "'></td></tr>";
	 

	 } 
	 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }



//update
	public String updateService(String userID, String userName, String userNic, String userEmail, String userAdd, String userTeleNo, String AcctNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 System.out.println(userEmail);
	 String query = "UPDATE user SET userName=?, userNic=?, userEmail=?, userAdd=?, userTeleNo=?, AcctNo=? WHERE userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, userName); 
	 preparedStmt.setString(2, userNic); 
	 preparedStmt.setString(3, userEmail); 
	 preparedStmt.setString(4, userAdd); 
	 preparedStmt.setString(5, userTeleNo);
	 preparedStmt.setString(6, AcctNo);	 
	 preparedStmt.setInt(7, Integer.parseInt(userID));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 
	 String newUser = readService();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newUser + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 		
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}"; 
		 System.err.println(e.getMessage()); 
		 System.out.println(userID);
	 } 
	 return output; 
	 } 
	
	
//delete	
	public String deleteService(String userID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from user where userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newUser = readService();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newUser + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}"; 
		 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	 
}
