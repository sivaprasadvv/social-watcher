$('#navigation li').on('click', function(e) {
	var clientId = $(this).find('a').attr('id');
	//alert($(this).find('a').attr('id')+"/"+$(this).text());
	$('li').removeClass('active'); // remove all active classes
	$(this).addClass('active');
	e.preventDefault();
	  $.ajax({
	    type: "GET",
	    url: application_context+"/management/clientbypartner/"+clientId,
	    data: null,
	    success: function(response,status,xhr){
	    	var clients ='';
	    	$.each (response, function (i) {
	    		if (response[i].clientname == 'No clients found'){
	    			clients = response[i].clientname;
	    		} else {	    	    
	    			clients +='<a href="#" id="name_'+i+'" class="btn btn-mini btn-primary">'+response[i].clientname+'</a>';
	    		}
	    	});
	    	$("#clientsdiv").html('<div class="control-group">'+clients+'</div>');
	    	$("#clientsdiv").show();
	    },
	    error: function (xhr, ajaxOptions, thrownError) {
	        alert("error:" +thrownError);
	      }
	  });
	  return false;
    
});