var oTable;
var selected_id;
$(document).ready(function() {
	
	//Start:Added to multi select on 10 July 2013 for test
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
	 
	// End:Added to multi select on 10 July 2013 for test -->
	
	oTable = $('#exporttable').dataTable({
		"sDom": 'l<"toolbar">frtip',
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
		"bProcessing" : true,
		"bServerSide" : true,
		"sAjaxSource" : application_context+"/report/",
		"aoColumns" : [ {"mData" : "id" },
		                {"mData" : "title"},
		                {"mData" : "description"}, 
		                {"mData" : "url"},
		                {"mData" : "socialMediaLogo"},
		                {"mData" : "sourceId"},
		                {"mData" : "sourceCreatedAt"}
		               ],
		"aoColumnDefs": [
		                 { "sName": "id",   "aTargets": [ 0 ] },
		                 { "sName": "title",  "aTargets": [ 1 ] },
		                 { "sName": "description", "aTargets": [ 2 ] },
		                 { "sName": "url",  "aTargets": [ 3 ] },
		                 { "sName": "socialMediaLogo",  "aTargets": [ 4 ] },
		                 { "sName": "sourceId",  "aTargets": [ 5 ] },
		                 { "sName": "sourceCreatedAt",  "aTargets": [ 6 ] },
		                 { "sClass" : "hide",   "aTargets" : [ 0 ]}
		               ],           
	  "fnDrawCallback": function ( oSettings ) {
		                   $('#exporttable tbody tr').each( function () {
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
	initExportButton();
	//initExportButtons();
});

/**
 * Initializing Export buttons 
 */
function initExportButton(){
	$("div.toolbar").html('<a href="#lightboxexport" data-toggle="modal" class="btn btn-small btn-info">Export</a>');	
}


/**
 * Initializing crud buttons 
 */
function initExportButtons(){
	$("div.toolbar").html('<a href="#lightboxexport" data-toggle="modal" class="btn btn-small btn-info">CSV</a> <a href="#" id="bt_excel" class="btn btn-small btn-info">EXCEL</a> <a href="#" id="bt_pdf" class="btn btn-small btn-info">PDF</a>');
	
	/*$("#bt_csv").click(function() {
		$("#bt_csv").attr("href", application_context+"/report/csv/");
	});*/
	
	$("#bt_excel").click(function() {
		$("#bt_excel").attr("href", application_context+"/report/excel/");
	});
	
	$("#bt_pdf").click(function() {
		$("#bt_pdf").attr("href", application_context+"/report/pdf/");
	});
	
	/*$("div.toolbar").html('<button id="bt_csv" type="button" class="btn btn-mini btn-info">CSV</button> <button id="bt_excel" type="button" class="btn btn-mini btn-info">EXCEL</button> <button id="bt_pdf" type="button" class="btn btn-mini btn-info">PDF</button>');
	
	$("#bt_csv").click(function() {
		exportToFile('csv');
	});
	
	$("#bt_excel").click(function() {
		exportToFile('excel');
	});
	
	$("#bt_pdf").click(function() {
		exportToFile('pdf');
	});*/
	
}

/**
 * Export button handler
 * @param type
 * @param query id of selected query text
 */
function exportToFile(type){
	$.ajax({
        type: "GET",
        url: application_context+'/report/'+type+'/',
        success: function(response,status,xhr){
            	alert(response);
        },
	    error: function (xhr, ajaxOptions, thrownError) {
	  	  $('#errorModalLabel').html("Error Server "+xhr.status+":");
	  	  $('#errorModalMsg').html(xhr.responseText);
	  	  $('#errorModelBody').attr('class', 'modal-body error alert-error');
	  	  $('#errorModal').modal();
	    }
   });
}




	

