 $(document).ready(function() {
	
	var validator = $("#partnerForm").validate({
		rules: {
			partnerName: {
				required: true,
				remote: application_context+"/validator/checkpartnername"
			},
			street1: "required",
			city: "required",
			state: "required",
			postCode: "required",
			country: "required",
			allowedClientsCount: "required",
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
			partnerName: {
				required : "Enter partner name",
				remote: jQuery.format("{0} is already in use")
			},
			street1: {required : "Enter your Street"},
			city: {required : "Enter your City"},
			state: {required : "Enter your State"},
			postCode: {required : "Enter your Postal Code"},
			country: {required : "Enter your Country"},
			allowedClientsCount: {required : "Enter your Clients Count"},
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
			var addrJsonData = {"street1" : $("input#street1").val(),"city" : $("input#city").val(),
					"zipPostal" : $("input#postCode").val(),"country" : $("#country :selected").val(),"state" : $("#state :selected").val()};
			var loginJsonData = {"name" : $("input#name").val(),"password" : $("input#password").val()};
			var jsonData = {
				partnerName : $("input#partnerName").val(),
				title : $("input#title").val(),
				description : $("input#description").val(),
				allowedClientsCount : $("input#allowedClientsCount").val(),
				login	: loginJsonData,
				address	: addrJsonData,
				isEnabled : $('input#isEnabled').is(':checked') 
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/partner/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/partner/list';
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
		document.location.href=application_context+'/management/partner/list';
	});	
}

