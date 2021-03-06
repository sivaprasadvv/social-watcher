var oTable;
var selected_id;
$(document).ready(function() {
	oTable = $('#roletable').dataTable({
		"sDom": 'l<"toolbar">frtip',
		//"sDom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
		"bFilter" : false,
		"bStateSave" : false,
		"aaSorting" : [ [ 1, "asc" ] ],
		"sPaginationType" : "bootstrap",
		"oLanguage" : {
			"sLengthMenu" : "Display _MENU_ records per page",
			"sZeroRecords" : "Nothing found - sorry",
			"sInfo" : "Showing _START_ to _END_ of _TOTAL_ records",
			"sInfoEmpty" : "Showing 0 to 0 of 0 records",
		},
		"bProcessing" : true,
		"bServerSide" : true,
		"sAjaxSource" : application_context+"/management/roles/",
		"aoColumns" : [ {"mData" : "id" },
		                {"mData" : "roleName"},
		                {"mData" : "title"},
		                {"mData" : "description"}
		               ],
		"aoColumnDefs": [
		                 { "sName": "id",   "aTargets": [ 0 ] },
		                 { "sName": "roleName",  "aTargets": [ 1 ] },
		                 { "sName": "title",  "aTargets": [ 2 ] },
		                 { "sName": "description",  "aTargets": [ 3 ] },
		                 { "sClass" : "hide",   "aTargets" : [ 0 ]}
		               ],           
	  "fnDrawCallback": function ( oSettings ) {
		                   $('#roletable tbody tr').each( function () {
		                	   selected_id = null;
		                       $(this).click( function () {
		                    	   oTable.$('tr.row_selected').removeClass('row_selected');
		                           $(this).addClass('row_selected');
		                           selected_id =  $(this).children(":first").html();
		                       });
		                       $(this).hover( function () {
		                    	   $(this).addClass( 'row_highlighted' );
		                       }, function(){
		                    	   $(this).removeClass('row_highlighted');
		                       });
		                   });
		               }
	});
	initCrudButtons();
});

/**
 * Initializing crud buttons 
 */
function initCrudButtons(){
	$("div.toolbar").html('<a href='+application_context+'/management/roles/create id="bt_add" class="btn btn-small btn-primary">Create</a> <a href="#" id="bt_update" class="btn btn-small btn-success">Edit</a> <button id="bt_delete" type="button" class="btn btn-small btn-danger">Delete</button>');
	
	$("#bt_update").click(function() {
		if (selected_id == null) {
	    	  $('#errorModalLabel').html('Warning:');
	    	  $('#errorModalMsg').html('Please select a record first.');
	    	  $('#errorModelBody').attr('class', 'modal-body alert alert-warning');
	    	  $('#errorModal').modal();
		} else {
			$("#bt_update").attr("href", application_context+"/management/roles/update/" + selected_id);
		}
	});
	
	$("#bt_delete").click(function() {
		if (selected_id == null) {
	    	  $('#errorModalLabel').html('Warning:');
	    	  $('#errorModalMsg').html('Please select a record first.');
	    	  $('#errorModelBody').attr('class', 'modal-body alert alert-warning');
	    	  $('#errorModal').modal();
		} else {
			deleteOperation(selected_id);
		}
	});
	
}

/**
 * Delete button handler
 * @param type
 * @param id
 */
/*function deleteOperation(id){
	$.ajax({
        type: "DELETE",
        url: application_context+'/management/roles/'+id,
        success: function(response,status,xhr){
            	oTable.$('tr.row_selected').remove();
            	selected_id = null;
        },
	      error: function (xhr, ajaxOptions, thrownError) {
	    	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	    	  $('#errorModalMsg').html(xhr.responseText);
	    	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	    	  $('#errorModal').modal();
	        }
   });
}*/

function deleteOperation(id){
	$.ajax({
        type: "DELETE",
        url: application_context+'/management/roles/'+id,
        success: function(response,status,xhr){
        	document.location.href=application_context+'/management/roles/list';
        },
	      error: function (xhr, ajaxOptions, thrownError) {
	    	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	    	  $('#errorModalMsg').html(xhr.responseText);
	    	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	    	  $('#errorModal').modal();
	        }
   });
}


	

