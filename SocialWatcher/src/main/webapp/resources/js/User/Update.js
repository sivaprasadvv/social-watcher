$(document).ready(function() {
	var validator = $("#userEditForm").validate({
		rules: {
			
		},
		messages: {
			
		},
		errorPlacement: function(error, element) {
			error.appendTo( element.next() );
		},
		submitHandler: function() {
			var jsonData = {
				id : $("input#id").val(),
				userName : $("input#userName").val(),
				title : $("input#title").val(),
				description : $("input#description").val(),
				isEnabled : $('input#isEnabled').is(':checked')
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/management/users/"+$("input#id").val(),  
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
	// Handling reset button
	resetButtonHandler();
});


function resetButtonHandler(){
	$("#resetButton").click(function() {
		$("input#title").val($("input#title").attr("data-reset"));
		$("input#description").val($("input#description").attr("data-reset"));
		  return false;
	});	
}



