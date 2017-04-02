var oTable;
var selected_id;
$(document).ready(function() {
	//$.blockUI();
	oTable = $('#audittoreviewtable').dataTable({
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
			"sInfoEmpty" : "Showing 0 to 0 of 0 records"
		},
		"bProcessing" : false,
		"bServerSide" : true,
		"sAjaxSource" : application_context+"/audit/toreview/",
		"aoColumns" : [ {"mData" : "id" },
		                {"mData" : "profileImageUrl" },
		                {"mData" : "socialMediaLogo"},		               		                
		                {"mData" : "title"},
		                {"mData" : "description"}, 
		                {"mData" : "url"},		                
		                {"mData" : "sourceCreatedAt"},
		                {"mData" : "place"},
		                {"mData" :  null}
		               ],
		"aoColumnDefs": [
		                 { "sName": "id",   "aTargets": [ 0 ] },
		                 { "sName": "profileImageUrl",  "aTargets": [ 1 ] },
		                 { "sName": "socialMediaLogo",  "aTargets": [ 2 ] },		                		                 
		                 { "sName": "title",  "aTargets": [ 3 ] },
		                 { "sName": "description", "aTargets": [ 4 ] },
		                 { "sName": "url",  "aTargets": [ 5 ] },		                 
		                 { "sName": "sourceCreatedAt",  "aTargets": [ 6 ] },
		                 { "sName": "place",  "aTargets": [ 7 ] },
		                 { "mRender": function (oObj) {
		                	 /*return '<div class="btn-group">'
		                	 +'<a href="#" onclick="fnReview(1);return false;" id="bt_positive" class="btn btn-mini btn-success">Postive</a>'
		                	 +'<a href="#" onclick="fnReview(0);return false;" id="bt_negetive" class="btn btn-mini btn-inverse">Negetive</a>'
		                	 +'<a href="#" onclick="fnReview(2);return false;" id="bt_comment" class="btn btn-mini btn-info">Comment</a>'
		                	 +'<a href="#" onclick="fnReview(-1);return false;" id="bt_delete" class="btn btn-mini btn-danger">Delete</a>'
		                	 +'</div>';*/
		                	 return '<div class="btn-group">'
		                	 +'<button class="btn btn-small btn-info">Action</button>'
		                	 +'<button class="btn btn-small btn-info dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>'
		                	 +'<ul class="dropdown-menu">'
		                	 +'<li><a href="#" onclick="fnReview(1);return false;" id="bt_positive">Positive</a></li>'
		                	 +'<li><a href="#" onclick="fnReview(0);return false;" id="bt_negetive">Negetive</a></li>'
		                	 +'<li><a href="#" onclick="fnReview(2);return false;" id="bt_comment">Comment</a></li>'
		                	 +'<li><a href="#" onclick="fnReview(-1);return false;" id="bt_delete">Delete</a></li>'
		                	 +'</ul>'
		                	 +'</div>';
		                   } ,  "aTargets": [ 8 ]},
		                 { "sClass" : "hide",   "aTargets" : [ 0 ]}
		               ],      
       "fnPreDrawCallback": function() {
    	   					$.blockUI({ message: loading_msg });
					       },
	  "fnDrawCallback": function ( oSettings ) {
		  					$(document).ajaxStop($.unblockUI); 
		                   $('#audittoreviewtable tbody tr').each( function () {
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
});

function fnReview(action){
	if (selected_id == null) {
    	  $('#errorModalLabel').html('Warning:');
    	  $('#errorModalMsg').html('Please select a record first.');
    	  $('#errorModelBody').attr('class', 'modal-body alert alert-warning');
    	  $('#errorModal').modal();
	} else if (action == '1') {
		fnPositiveSearchResult(selected_id);
	}
	
	else if (action == '0') {
		fnNegetiveSearchResult(selected_id);
	}
	
	else if (action == '-1') {
		fnDeleteSearchResult(selected_id);
	}
	
	else if (action == '2') {
		fnCommentSearchResult(selected_id);
	}	
}

/**
 * Delete button handler
 * @param type
 * @param id
 */
/*function fnDeleteSearchResult(id){
	$.ajax({
        type: "DELETE",
        url: application_context+'/audit/toreview/'+id,
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

function fnDeleteSearchResult(id){
	$.blockUI({ message: deleting_msg });
	var jsonData = {
			id : id,
			isDeleted : 'y',
			reviewedDate: new Date().getTime(),
			reviewedBy: 'admin'
		};
	$.ajax({
		type: "PUT",  
		  url: application_context+"/audit/toreview/"+id,  
		  data: JSON.stringify(jsonData),
		  contentType: "application/json; charset=utf-8",
		  success: function(response,status,xhr) { 
			  document.location.href=application_context+'/audit/toreview/list';
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
}


function fnPositiveSearchResult(id){
	$.blockUI({ message: reviewing_msg });
	var jsonData = {
			id : id,
			isReviewed : true
		};
	$.ajax({
		type: "PUT",  
		  url: application_context+"/audit/toreview/"+id,  
		  data: JSON.stringify(jsonData),
		  contentType: "application/json; charset=utf-8",
		  success: function(response,status,xhr) { 
			  document.location.href=application_context+'/audit/toreview/list';
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
}

function fnNegetiveSearchResult(id){
	$.blockUI({ message: reviewing_msg });
	var jsonData = {
			id : id,
			isReviewed : false
		};
	$.ajax({
		type: "PUT",  
		  url: application_context+"/audit/toreview/"+id,  
		  data: JSON.stringify(jsonData),
		  contentType: "application/json; charset=utf-8",
		  success: function(response,status,xhr) { 
			  document.location.href=application_context+'/audit/toreview/list';
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
}

function fnCommentSearchResult(id){
	$.blockUI({ message: commenting_msg });
	$.ajax({
		type: "GET",  
		  url: application_context+'/audit/toreview/update/' +id,  
		  data: null,
		  contentType: "application/json; charset=utf-8",
		  success: function(response,status,xhr) { 
			  //$.fancybox(data);
			  document.location.href=application_context+'/audit/toreview/update/' +id;
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
	
}

//unblock when ajax activity stops 
$(document).ajaxStop($.unblockUI); 



	

