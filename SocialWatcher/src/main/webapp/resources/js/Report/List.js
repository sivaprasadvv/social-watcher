var oTable;
var selected_id;
$(document).ready(function() {
	console.log(application_context+"/resources/js/TableTools/media/swf/copy_csv_xls_pdf.swf");
	oTable = $('#reporttable').dataTable({
		"sDom": 'T<"clear">lfrtip',
		"oTableTools": {
            "sSwfPath": application_context+"/resources/js/TableTools/media/swf/copy_csv_xls_pdf.swf"
        },
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
		                   $('#reporttable tbody tr').each( function () {
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




	

