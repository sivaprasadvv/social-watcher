$.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg != value;
 }, "Value must not equal arg.");

$(document).ready(function() {
	
	 $('#daterange').daterangepicker();
	 
	 // Start: Checkbox styles
	 /*$('input[type="checkbox"].checkstyle').checkbox({
			buttonStyleChecked: 'btn-success',
		    checkedClass: 'icon-check',
		    uncheckedClass: 'icon-check-empty'
		});*/
	 
	 //$('input[type="checkbox"].checkstyle').prettyCheckable();
	 
	 /*$('input[type="checkbox"]').iCheck({
		    checkboxClass: 'icheckbox_minimal'
		  });*/
	// End: Checkbox styles
	
	var validator = $("#createReportForm").validate({
		rules: {
			query: {
				required: true,
				minlength: 1
			},
			daterange: {
				required:true
			}
		},
		messages: {
			query: {
				required: "Please check atleast one query",
				minlength : "Please select a query from dropdown"
			},
			daterange: {
				required: "Please select date range"
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
		submitHandler: function() {
			$.blockUI({ message: loading_msg });
			var queryArr = [];
			$('input:checkbox[name=query]').each(function() 
					{  
				 if($(this).is(':checked')) {
					 //alert($(this).val());
					queryArr.push($(this).val());
				 }
			});
			//alert("checked query:" +queryArr);
			var jsonData = {
				queries : queryArr,
				dateRange : $('#daterange').val()
			};
			$.ajax({  
				  type: "POST",  
				  url: application_context+"/report/",  
				  data: JSON.stringify(jsonData),
				  contentType: "application/json; charset=utf-8",
				  success: function(response,status,xhr) {
					  //Table tools export 
					  //document.location.href=application_context+'/report/list';
					  //Custom export 
					  document.location.href=application_context+'/report/export';
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
		document.location.href=application_context+'/audit/toreview/list';
	});	
}
