$(document).ready(function() {
	var validator = $("#roleForm").validate({
		rules: {
			roleName: {
				required:true
				,
				remote: application_context+"/validator/checkrolename"
			}
		},
		messages: {
			roleName: {
				required : "Enter a role name"
					,
				remote: jQuery.format("{0} is already in use")
					}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			var jsonData = {
				roleName : $("input#roleName").val(),
				title : $("input#title").val(),
				description : $("input#description").val()
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/roles/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/roles/list';
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
		document.location.href=application_context+'/management/roles/list';
	});	
}
