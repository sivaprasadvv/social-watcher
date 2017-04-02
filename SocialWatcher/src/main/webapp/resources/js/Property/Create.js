$(document).ready(function() {
	var validator = $("#propertyForm").validate({
		rules: {
			roleName: {
				required:true,
				remote: application_context+"/validator/checkpropertyname"
			}
		},
		messages: {
			roleName: {
				required : "Enter a property name",
				remote: jQuery.format("{0} is already in use")
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			var jsonData = {
				propertyName : $("input#propertyName").val(),
				propertyValue : $("input#propertyValue").val()
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/properties/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/properties/list';
				  },
			      error: function (xhr, ajaxOptions, thrownError) {
			          alert(thrownError);
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
		document.location.href=application_context+'/management/properties/list';
	});	
}
