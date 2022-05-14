<%@page import="com.userModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-8">
 
 
 <h1 class="m-3">User details</h1>

 <form id="formUser" name="formUser" method="post" action="user.jsp">
 
 <!-- NAME -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lblName">Name: </span>
 </div>
 <input type="text" id="userName" name="userName">
 </div>
 <!-- NIC -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lblNic">NIC: </span>
 </div>
 <input type="text" id="userNic" name="userNic" maxlength="10">
 </div>
 <!-- Email -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lblemail">Email: </span>
 </div>
 <input type="text" id="userEmail" name="userEmail">
 </div> 
 <!-- Address -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lbladd">Address: </span>
 </div>
 <input type="text" id="userAdd" name="userAdd">
 </div>
  <!-- Telephone no. -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lblteleNo">Phone Number: </span>
 </div>
 <input type="text" id="userTeleNo" name="userTeleNo" maxlength="10">
 </div>
  <!-- Account no. -->
 <div class="input-group input-group-sm mb-3">
 <div class="input-group-prepend">
 <span class="input-group-text" id="lblacctNo">Account Number: </span>
 </div>
 <input type="text" id="AcctNo" name="AcctNo" maxlength="10">
 </div>
 <div id="alertSuccess" class="alert alert-success"></div>
 <div id="alertError" class="alert alert-danger"></div>
<input type="button" id="btnSave" value="Save" class="btn btn-primary">
<br>
<!-- hidden element to hold the the userID for update function -->
<input type="hidden" id="hidUserIDsave" name="hidUserIDsave" >

</form>


<br>
<div id="divUsersGrid">
 <%
 userModel userObj = new userModel(); 
 out.print(userObj.readService()); 
 %>
</div>
 
</div>
</div>
</div>

</body>
</html>