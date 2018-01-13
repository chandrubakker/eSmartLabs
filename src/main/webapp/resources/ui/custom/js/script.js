/**
 * 
 */

$(function() {
	
	$("body").removeClass("loginPage");
	$("body").removeClass("registerPageBG");
	$("body").removeClass("contactPageBG");
	$("body").removeClass("adminRegisterPageBG");
	
	var url = document.URL;
	if(url.endsWith("contact-us")) {
		
		$("body").addClass("contactPageBG");
		
		$(".link_login").removeClass("active");
		$(".link_contact").addClass("active");
		$(".link_register").removeClass("active");
	} else if(url.endsWith("/client/register")) {
		
		$("body").addClass("registerPageBG");
		
		$(".link_login").removeClass("active");
		$(".link_contact").removeClass("active");
		$(".link_register").addClass("active");
	} else if(url.endsWith("/register")) {
		
		$("body").addClass("adminRegisterPageBG");
		
		$(".link_login").removeClass("active");
		$(".link_contact").removeClass("active");
		$(".link_register").removeClass("active");
	} else if(url.endsWith("/login") || url.endsWith("/login?logout") || url.endsWith("/login?sessionExp")){
		
		$("body").addClass("loginPage");
		
		$(".link_login").addClass("active");
		$(".link_contact").removeClass("active");
		$(".link_register").removeClass("active");
	} 
});