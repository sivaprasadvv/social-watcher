$('#navigation li').on('click', function(e) {
	var clientId = $(this).find('a').attr('id');
	//alert($(this).find('a').attr('id')+"/"+$(this).text());
	$('li').removeClass('active'); // remove all active classes
	$(this).addClass('active');
	e.preventDefault();
	  $.ajax({
	    type: "GET",
	    url: application_context+"/management/userbyclient/"+clientId,
	    data: null,
	    success: function(response,status,xhr){
	    	var users ='';
	    	$.each (response, function (i) {
	    		if (response[i].username == 'No users found'){
	    			users = 'No users found';
	    		} else {	    	    
	    			users +='<a href="#" id="name_'+i+'" class="btn btn-mini btn-primary">'+response[i].username+'</a>';
	    		}
	    	});
	    	$("#usersdiv").html('<div class="control-group">'+users+'</div>');
	    	$("#usersdiv").show();
	    },
	    error: function (xhr, ajaxOptions, thrownError) {
	        alert("error:" +thrownError);
	      }
	  });
	  return false;
    
});