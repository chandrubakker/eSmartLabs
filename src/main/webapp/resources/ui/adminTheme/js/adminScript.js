/**
 *  Admin_Theme Script
 */

$(function () {
  	$('.navbar-toggle-sidebar').click(function () {
  		$('.navbar-nav').toggleClass('slide-in');
  		$('.side-body').toggleClass('body-slide-in');
  		$('#search').removeClass('in').addClass('collapse').slideUp(200);
  	});

  	$('#search-trigger').click(function () {
  		$('.navbar-nav').removeClass('slide-in');
  		$('.side-body').removeClass('body-slide-in');
  		$('.search-input').focus();
  	});
  });

/*
 *Used to show the modal on page load 
 */

function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    console.log("url"+sPageURL);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

$(function() {
	
	var schoolExists = getUrlParameter('schoolExists');
	var message = "There is no school info available!"
	if(schoolExists == 0){	
		
		$("#modal-info").modal('show').find('.no-school').text(message).addClass('text-center');			
	}
	
});
