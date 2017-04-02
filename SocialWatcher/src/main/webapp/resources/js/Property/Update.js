$(document).ready(function() {
	var validator = $("#propertyEditForm").validate({
		rules: {
			propertyValue: "required"
		},
		messages: {
			propertyValue: "Enter property value"
		},
		errorPlacement: function(error, element) {
			error.appendTo( element.next() );
		},
		submitHandler: function() {
			var jsonData = {
				id : $("input#id").val(),
				propertyName : $("input#propertyName").val(),
				propertyValue : $("input#propertyValue").val()
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/management/properties/"+$("input#id").val(),  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) { 
					  document.location.href=application_context+'/management/properties/list';
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
		$("input#propertyValue").val($("input#propertyValue").attr("data-reset"));
		  return false;
	});	
}



