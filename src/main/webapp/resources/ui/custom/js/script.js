/**
 * 
 */

$(function() {
	
	var url = document.URL;
	if(url.endsWith("contact-us")) {
		
		$(".link_login").removeClass("active");
		$(".link_contact").addClass("active");
		$(".link_register").removeClass("active");
	} else if(url.endsWith("/client/register")) {
		
		$(".link_login").removeClass("active");
		$(".link_contact").removeClass("active");
		$(".link_register").addClass("active");
	} else if(url.endsWith("/login") || url.endsWith("/login?logout") || url.endsWith("/login?sessionExp")){
		
		$(".link_login").addClass("active");
		$(".link_contact").removeClass("active");
		$(".link_register").removeClass("active");
	} 
});