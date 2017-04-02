$(document).ready(function() {
	
	var validator = $("#userForm").validate({
		rules: {
			userName: "required",	
			password: {
				required: true,
				minlength: 5,
				maxlength:10
			},
			password_confirm: {
				required: true,
				minlength: 5,
				maxlength: 10,
				equalTo: "#password"
			},
			name: {
				required: true,
				email: true,
				remote: application_context+"/validator/checkemail"
			}
		},
		messages: {
			userName: {required : "Enter your username"},
			password:  {
				required: "Provide a password",
				minlength: jQuery.format("Enter at least {0} characters"),
				maxlength: jQuery.format("Enter a maximum of {0} characters")
			},
			password_confirm: {
				required: "Repeat your password",
				equalTo: "Enter the same password as above"
			},
			email: {
				required: "Please enter a valid email address",
				minlength: "Please enter a valid email address"
				,remote: jQuery.format("{0} is already in use")
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			/*var addressJsonData = {"street1" : $("input#street1").val(),"city" : $("input#city").val(),
					"zipPostal" : $("input#postCode").val(),"country" : $("#country :selected").val(),"state" : $("#state :selected").val()};*/
			var loginJsonData = {"name" : $("input#name").val(),"password" : $("input#password").val()};
			var jsonData = {
				userName : $("input#userName").val(),
				title : $("input#title").val(),
				description : $("input#description").val(),
				login	: loginJsonData,
				isEnabled : $('input#isEnabled').is(':checked') 
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/users/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/users/list';
				  }
				});  
			return false; 
		},
		success: function(label) {
		}
	});
	cancelButtonHandler();
});

/**
 * Cancel button handler
 */
function cancelButtonHandler(){
	$("#cancelButton").click(function() {
		document.location.href=application_context+'/management/users/list';
	});	
}

