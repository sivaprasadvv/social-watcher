jQuery.validator.addMethod("needsSelection", function(value, element) {
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
	
	var validator = $("#assignClientForm").validate({
		rules: {
			clients: "needsSelection"
		},
		ignore: ':hidden:not(.multiselect)',
		messages: {
			clients: {
				needsSelection : 'Please associate atleast one client.Create a client using <a href='+application_context+'/management/clients/create id="bt_add">Create</a>'
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo( element.next() );
		},
		submitHandler: function() {
			var clientArr = [];
			$('#clients :selected').each(function(){
				if ($(this).val() !== 'multiselect-all') {
					clientArr.push({ 
				        "id" : $(this).val()
				    });
				}
			});
			var jsonData = {
				id : $("input#id").val(),
				partnerName : $("input#partnerName").val(),
				clients	: clientArr
			};
			$.ajax({  
				  type: "PUT",  
				  url: application_context+"/management/partner/assign/update/"+$("input#id").val(),  
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



