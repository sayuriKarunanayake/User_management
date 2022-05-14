/*
   implement both the controller and client-server in main.js.
*/
$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// Insert function
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateUserForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidUserIDsave value is null set as POST else set as PUT
    var type = ($("#hidUserIDsave").val() == "") ? "POST" : "PUT";

    // ajax call
    $.ajax({
        url: "userAPI",
        type: type,
        data: $("#formUser").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onUserSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onUserSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divUsersGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidUserIDsave").val("");
    $("#formUser")[0].reset();
}

// Update function
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    //get user id from the data-userid attribute in update button
    $("#hidUserIDsave").val($(this).data('userid')); 
    
    //get data from <td> element
    $("#userName").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#userNic").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#userEmail").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#userAdd").val($(this).closest("tr").find('td:eq(4)').text()); 
    $("#userTeleNo").val($(this).closest("tr").find('td:eq(5)').text()); 
    $("#AcctNo").val($(this).closest("tr").find('td:eq(6)').text()); 
}); 

// Delete function
$(document).on("click",".btnRemove", function(event) {
	
    // ajax call
    $.ajax({
        url: "userAPI",
        type: "DELETE",
        data: "userID=" + $(this).data("userid"),
        dataType: "text",
        complete: function(response, status) {
            onUserDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onUserDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divUsersGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// Validations in the form
function validateUserForm() { 
    // name 
    if ($("#userName").val().trim() == "") 
    { 
        return "Insert Name."; 
    } 
    
    // nic
    if ($("#userNic").val().trim() == "") 
    { 
        return "Insert NIC."; 
    } 
    
    // email
    if ($("#userEmail").val().trim() == "") 
    { 
        return "Insert Email."; 
    } 
    
    // address
    if ($("#userAdd").val().trim() == "") 
    { 
        return "Insert Address."; 
    } 
    
    // telephone no.
    if ($("#userTeleNo").val().trim() == "") 
    { 
        return "Insert Telephone Number."; 
    } 
    
    // accct no.
    if ($("#AcctNo").val().trim() == "") 
    { 
        return "Insert User's Account Number.'"; 
    } 
    
    return true; 
} 
 