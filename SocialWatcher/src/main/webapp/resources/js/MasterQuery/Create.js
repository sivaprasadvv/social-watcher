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
	      selectAllValue: '-1',
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
	
	var validator = $("#masterQueryForm").validate({
		rules: {
			queryText: "required",
			socialmedia: "needsSelection"
		},
		ignore: ':hidden:not(.multiselect)',
		messages: {
			queryText: {required : "Enter your Query String"},
			socialmedia: {
				needsSelection : 'Please select atleast one'
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			var jsonData = '';
			var socialMediaValues = $('#socialmedia').val();
			if (role == 'admin' || role == 'partner') {
				var selectedClient = $('#selectedClient').val();
				jsonData = {
						queryText : $("input#queryText").val(),
						selectedClient : selectedClient,
						selectedSocialMedia : socialMediaValues
					};
			} else {
				jsonData = {
					queryText : $("input#queryText").val(),
					selectedSocialMedia : socialMediaValues
				};
			}
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/masterquery/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  document.location.href=application_context+'/masterquery/mlist';
				  },
				  error: function(xhr, status, error) {
					  if (error == 'Forbidden') {
						  alert("Sorry, you are exceeded your limit.\nWe cannot process your request.");
					  } else {
						  alert("Sorry, this combination is already exists.\nPlease try other");
					  }
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
	$("#addCancelButton").click(function() {
		document.location.href=application_context+'/masterquery/mlist';
	});	
}

/*$(function(){
	$("#editButton2").click(function(e) {
		alert("In Edit" +this.id);
		$("input#query2").removeAttr('readOnly');
	});
});*/

function fnEdit(index) {
	//alert(index);
	$("input#query"+index).removeAttr('readOnly');
	$("#editButton"+index).hide();
	$("#updateButton"+index).attr('disabled',false);
}

function fnUpdate(index) {
	alert("fnUpdate:" +index);
	//$("input#query"+index).removeAttr('readOnly');
	var jsonData = {
			id : $("input#id"+index).val(),
			queryText : $("input#query"+index).val()
		};
	alert(JSON.stringify(jsonData));
		$.ajax({  
			  type: "PUT",  
			  url: application_context+"/masterquery/"+$("input#id"+index).val(),  
			  data: JSON.stringify(jsonData),
			  contentType: "application/json; charset=utf-8",
			  success: function(response,status,xhr) { 
				  document.location.href=application_context+'/masterquery/mlist';
			  }
			});  
}

