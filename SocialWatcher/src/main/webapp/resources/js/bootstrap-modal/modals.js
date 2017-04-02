$(function(){
  function hideModal(modalwindow){
    $(modalwindow).modal('hide');
  }
  
 $('#export').on('click', function(e){
    e.preventDefault();
    /*var reportFieldValues = $('#reportFields').val();
	alert("reportFieldValues" +reportFieldValues);*/
	 var reportFieldValues = new Array();
	 $('input:checkbox[name=reportFields]').each(function() 
			 {    
			     if($(this).is(':checked')) {
			    	 reportFieldValues.push($(this).val());
			 		}
			 });
    fnExport(reportFieldValues);
    hideModal('#lightboxexport');  
  });
  
  $('#closewin').on('click', function(e){
    e.preventDefault();    
    resetModal();
    hideModal('#lightboxexport');
  });
});

function fnExport(reportFileds) {

	  var currentid = $('input[name=export]:checked').attr('id');
	  //alert(currentid);	  	 
	  exportToFile(currentid,reportFileds);
	}


function exportToFile(type,reportFileds){
	var jsonData = {
			reportFields : reportFileds
		};
	//alert(JSON.stringify(jsonData));
	$.ajax({
        type: "POST",
        url: application_context+'/report/'+type+'/',
        data: JSON.stringify(jsonData),
        contentType: "application/json; charset=utf-8",
        success: function(response,status,xhr){
        	document.location.href=application_context+'/report/'+type+'/';
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
}

function resetModal() {
	    $('#csv').prop('checked', true);    
	}
