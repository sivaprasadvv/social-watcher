$(document).ready(function() {
	var validator = $("#auditToReviewUpdateForm").validate({
		rules: {
			comment: "required"
		},
		messages: {
			comment: "Please enter comment"
		},
		errorPlacement: function(error, element) {
			error.appendTo( element.next() );
		},
		submitHandler: function() {
			var jsonData = {
				id : $("input#id").val(),
				comment : $("input#comment").val(),
				reviewedDate: $("input#reviewedDate").val(),
				reviewedBy: $("input#reviewedBy").val()
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/audit/toreview/"+$("input#id").val(),  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) { 
					  document.location.href=application_context+'/audit/toreview/list';
				  }
				});  
			return false; 
		},
		success: function(label) {
		}
	});
	// Handling reset button
	resetButtonHandler();
});


function resetButtonHandler(){
	$("#resetButton").click(function() {
		$("input#comment").val($("input#comment").attr("data-reset"));
		  return false;
	});	
}