$(document).ready(function() {
	var validator = $("#searchEngineEditForm").validate({
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
				name : $("input#name").val(),
				isEnabled : $('input#isEnabled').is(':checked')
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/management/socialmedia/"+$("input#id").val(),  
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
	// Handling reset button
	resetButtonHandler();
});


function resetButtonHandler(){
	$("#resetButton").click(function() {
		  return false;
	});	
}



