jQuery.validator.addMethod("needsSelection", function(value, element) {
	/*alert("In needsSelection " +value);
	alert(value === 'None selected' || value === 'null'  || value === 'NULL' || value === null);
	alert(value !== 'None selected' || value !== 'null'  || value !== 'NULL' || value !== null);*/
	return (value !== 'None selected' && value !== 'null'  && value !== 'NULL' && value !== null);
});

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
	
	var validator = $("#assignUserForm").validate({
		rules: {
			users: "needsSelection"
		},
		ignore: ':hidden:not(.multiselect)',
		messages: {
			users: {
				needsSelection : "Please associate atleast one user"
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo( element.next() );
		},
		submitHandler: function() {
			var userArr = [];
			$('#users :selected').each(function(){
				if ($(this).val() !== 'multiselect-all') {
					userArr.push({ 
				        "id" : $(this).val()
				    });
				}
			});
			var jsonData = {
				id : $("input#id").val(),
				clientName : $("input#clientName").val(),
				users	: userArr
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/management/clients/assign/update/"+$("input#id").val(),  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) { 
					  document.location.href=application_context+'/management/clients/list';
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
		$("input#password").val($("input#password").attr("data-reset"));
		$("input#password_confirm").val($("input#password").attr("data-reset"));
		  return false;
	});	
}



