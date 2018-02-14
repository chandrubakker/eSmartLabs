/**
 * ADMIN SCRIPT
 */

$(function () {
	$('.navbar-toggle').click(function () {
		$('.navbar-nav').toggleClass('slide-in');
		$('.side-body').toggleClass('body-slide-in');
		$('#search').removeClass('in').addClass('collapse').slideUp(200);

		/// uncomment code for absolute positioning tweek see top comment in css
		//$('.absolute-wrapper').toggleClass('slide-in');

	});

	// Remove menu for searching
	$('#search-trigger').click(function () {
		$('.navbar-nav').removeClass('slide-in');
		$('.side-body').removeClass('body-slide-in');

		/// uncomment code for absolute positioning tweek see top comment in css
		//$('.absolute-wrapper').removeClass('slide-in');

	});

	$('.panel-collapse').on('hidden.bs.collapse', function (e) {

		var linkCaret = $(e.currentTarget.parentElement).find('.link-caret');

		linkCaret.toggleClass(function() {

			if(linkCaret.hasClass('fa-plus-square-o')) {

				return 'fa-minus-square-o';
			} else {

				return 'fa-plus-square-o';
			}
		});
	});

	$('.panel-collapse').on('shown.bs.collapse', function (e) {

		var linkCaret = $(e.currentTarget.parentElement).find('.link-caret');

		if(linkCaret.hasClass('fa-plus-square-o')) {

			linkCaret.removeClass('fa-plus-square-o');
			linkCaret.addClass('fa-minus-square-o');
		}

	});
	
	$(".esm-back-btn").on('click', function() {
		
		window.history.go(-1); 
		return false;
	});
});

function removeActiveLink() {

	if($('.esm-single-link').hasClass('active')) {

		$('.esm-single-link').removeClass('active');
	}
}

function addActiveLink(link) {

	if(link.hasClass('active')) {

		console.log(link.attr('href') + ": Already Active");
	} else {

		link.addClass('active')
	}
}

function expandDropDown(link) {


}

$(window).load(function(){

	updateSideMenuLinks();
});

function updateSideMenuLinks() {

	var location = window.location;
	var host = location.host;
	var pathName = location.pathname;
	var currentURL = location.href;

	console.log(pathName);

	var allSingleLinks = $(".esm-single-link a");
	if(allSingleLinks.length > 0) {

		var currentLink = {};
		for(var l = 0; l < allSingleLinks.length; l++) {

			currentLink = allSingleLinks[l];
			if(currentLink.href === currentURL) {

				removeActiveLink();
				addActiveLink($(currentLink).parent());

				if($(currentLink).parent().parent().hasClass('nav')) {

					if($(currentLink).parent().parent().parent().hasClass('panel-body')) {

						if($(currentLink).parent().parent().parent().parent().attr('aria-expanded')) {

							console.log("EXPANDED");
						} else {

							console.log("EXPANDING");
							$(currentLink).parent().parent().parent().parent().collapse('show');
							
							/*Checking for first level*/
							var hasSecondLvl = $(currentLink).parent().parent().parent().parent().attr('id').endsWith('-lvl2');
							if(hasSecondLvl) {
								
								console.log("2nd Level ");
								var firstLvlId = $(currentLink).parent().parent().parent().parent().attr('id').replace('lvl2', 'lvl1');
								if($("#" + firstLvlId)) {
									
									$("#" + firstLvlId).collapse('show');
								} else {
									
									console.log("1st lvl elem not found");
								}
							} else {
								
								console.log("No 2nd Level");
							}
						}
					} else {

						console.log($(currentLink).parent().parent().hasClass('nav') ? "DROP" : "SINGLE");
					}
				} else {

					console.log($(currentLink).parent().parent().hasClass('nav') ? "DROP" : "SINGLE");
				}

				break;
			} else {

				continue;
			}
		}
	} else {

		console.log("NO SINGLE LINK");
	}
}

function deleteConfirm(targetUrl, name, dataTable) {
	$(".deleteMsg").text("Are you sure want to delete?");
	$(".delName").text(name);
	$(".deleteTarget").attr("onclick", 'deleteItem("'+targetUrl+'", "'+name+'", "'+dataTable+'");');
	$("#deleteConfirm").modal('show');
}

function printPage() {
	window.print();
}

function deleteItem(url, itemName, dataTable) {
	$("#deleteConfirm").modal('hide');
	$(".busy-modal-title").text("Deleting " + itemName + "...");
	$("#busy-modal").modal('show');
	setTimeout(function() {
		$.ajax({
			async: true,
			type: 'DELETE',
			url: url,
			beforeSend: function(jqXHR) {
				jqXHR.setRequestHeader('X-CSRF-Token', "${_csrf.token}");
			},
			success: function(data, textStatus, jqXHR) {
				var status = jqXHR.status;
				console.log("STATUS: " + status);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, jqXHR.status);
				console.log(errorThrown);
			},
			statusCode: {
				404: function() {
					console.log("404: resource not found.");
				},
				400: function() {
					console.log("400: validation errors.");
				},
				500: function() {
					console.log("500: internal server error.");
				},
				403: function() {
					console.log("403: access denied.");
				},
				201: function() {
					console.log("201: object created.");
				},
				200: function() {
					console.log("200: object updated.");
				},
				204: function() {
					console.log("204: object deleted.");
				},
				405: function() {
					console.log("405: Method Not Allowed.");
				}
			}
		});
		clearTimeout(this);
		$("#busy-modal").modal('hide');

		setTimeout(function(){
			eval(dataTable).ajax.reload();
			clearTimeout(this);
		}, 1000);

	}, 1500);
}

var config = {
	'.chosen-select' : {}
};

for (var selector in config) {
	
	$(selector).chosen(config[selector]);
}

function refreshPage() {
	
	location.reload();
}