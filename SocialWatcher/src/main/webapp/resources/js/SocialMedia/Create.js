$(document).ready(function() {
	var validator = $("#searchEngineForm").validate({
		rules: {
			name: "required"
		},
		messages: {
			name: {required : "Enter social media name"}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			var jsonData = {
				name : $("input#name").val(),
				isEnabled : $('input#isEnabled').is(':checked') 
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/management/socialmedia/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/management/socialmedia/list';
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
		document.location.href=application_context+'/management/socialmedia/list';
	});	
}

