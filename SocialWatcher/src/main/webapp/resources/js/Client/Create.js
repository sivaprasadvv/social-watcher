jQuery.validator.addMethod("zipcodeUS", function(value, element) {
    return this.optional(element) || /\d{5}-\d{4}$|^\d{5}$/.test(value);
}, "The specified US ZIP Code is invalid");

$(document).ready(function() {
	
	$('.multiselect').multiselect({
	      buttonClass: 'btn',
	      buttonWidth: 'auto',
	      buttonContainer: '<div class="btn-group" />',
	      maxHeight: false,
	      includeSelectAllOption: true,
	      selectAllValue: 'multiselect-all',
	      buttonText: function(options) {
	        if (options.length == 0) {
	          return 'None selected <b class="caret"></b>';
	        }
	        else if (options.length > 3) {
	          return options.length + ' selected  <b class="caret"></b>';
	        }
	        else {
	          var selected = '';
	          options.each(function() {
	            selected += $(this).text() + ', ';
	          });
	          return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
	        }
	      }
	    });
	
	var validator = $("#clientForm").validate({
		rules: {
			clientName: {
				required: true,
				remote: application_context+"/validator/checkclientname"
			},
			street1: "required",
			city: "required",
			state: "required",
			postCode: "zipcodeUS",
			country: "required",
			allowedQueryWordsCount: "required",
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
			clientName: {
				required : "Enter your clientname",
				remote: jQuery.format("{0} is already in use")
			},
			street1: {required : "Enter your Street"},
			city: {required : "Enter your City"},
			state: {required : "Enter your State"},
			postCode: {zipcodeUS : "Enter valid US Postal Code"},//99999-9999
			country: {required : "Enter your Country"},
			allowedQueryWordsCount: {required : "Enter your Query Words Count"},
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
			var addressJsonData = {"street1" : $("input#street1").val(),"city" : $("input#city").val(),
					"zipPostal" : $("input#postCode").val(),"country" : $("#country :selected").val(),"state" : $("#state :selected").val()};
			var loginJsonData = {"name" : $("input#name").val(),"password" : $("input#password").val()};
			var jsonData = {
				clientName : $("input#clientName").val(),
				title : $("input#title").val(),
				description : $("input#description").val(),
				allowedQueryWordsCount : $("input#allowedQueryWordsCount").val(),
				login	: loginJsonData,
				address	: addressJsonData,
				isEnabled : $('input#isEnabled').is(':checked') 
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/clients/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/clients/list';
					  //document.location.href=application_context+'/management/users/create';
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
		document.location.href=application_context+'/management/clients/list';
	});	
}

