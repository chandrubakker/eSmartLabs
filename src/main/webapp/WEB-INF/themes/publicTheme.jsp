<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Medical Laboratory Management Application">
		<meta name="keywords" content="lab software, medical laboratory software, pathology laboratory software, blood test management, lab management">
		<meta name="author" content="www.sorcererpaws.com">
		<title>
			<sitemesh-decorator:title default="Public Theme" />
		</title>
		
		<style type="text/css">
			@font-face {
				font-family: osans-normal;
				src: url("<c:url  value='/resources/ui/fonts/open-sans/OpenSans-Regular.ttf' />");
			}
			
			@font-face {
				font-family: osans-light;
				src: url("<c:url  value='/resources/ui/fonts/open-sans/OpenSans-Light.ttf' />");
			}
			
			@font-face {
				font-family: phil-normal;
				src: url("<c:url  value='/resources/ui/fonts/philosopher/Philosopher-Regular.ttf' />");
			}
			
			@font-face {
				font-family: lato-hair;
				src: url("<c:url  value='/resources/ui/fonts/lato/Lato-Hairline.ttf' />");
			}
			
			@font-face {
				font-family: lato-thin;
				src: url("<c:url  value='/resources/ui/fonts/lato/Lato-Thin.ttf' />");
			}
			
			@font-face {
				font-family: lato-light;
				src: url("<c:url  value='/resources/ui/fonts/lato/Lato-Light.ttf' />");
			}
			
			@font-face {
				font-family: arsenal-regular;
				src: url("<c:url  value='/resources/ui/fonts/arsenal/Arsenal-Regular.otf' />");
			}
			
			@font-face {
				font-family: colab-light;
				src: url("<c:url  value='/resources/ui/fonts/Colaborate/ColabLig.otf' />");
			}
			
			@font-face {
				font-family: colab-thin;
				src: url("<c:url  value='/resources/ui/fonts/Colaborate/ColabThi.otf' />");
			}
			
			@font-face {
				font-family: colab-regular;
				src: url("<c:url  value='/resources/ui/fonts/Colaborate/ColabReg.otf' />");
			}
		</style>
		
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/css/bootstrap.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/css/bootstrap.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/font-awesome/css/font-awesome.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/custom/css/style.css' />" />
		
		<!-- JQuery -->
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/jquery.js' />"></script>
	</head>
	<body>
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-static-top nav-style">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<c:url value="/" var="home" />
					<a class="navbar-brand" href="${home}"><!-- Home -->
						<!-- es-nav-brand add class if logo -->
						<%-- <img alt="brand-logo" class="img-responsive" src="<c:url value="/resources/ui/images/eLab_logo_white.png" />" /> --%>
						<strong><i class="fa fa-plus-square" aria-hidden="true"></i> eSmartLabs</strong>
					</a>
				</div>
		
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-left navbar-left_c">
						<security:authorize access="hasRole('ROLE_TECHNICIAN')">
							<li class="dropdown ">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
									Patients <span class="fa fa-bars"></span>
								</a>
								<ul class="dropdown-menu dropdown-menu_c" role="menu">
									<li class=""><a href="<c:url value="/patient/create" />">New Patient</a></li>
									<li class=""><a href="<c:url value="/search/patients" />">Search Patients</a></li>
								</ul>
							</li>
							<li class="dropdown ">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
									Reports <span class="fa fa-bars"></span>
								</a>
								<ul class="dropdown-menu dropdown-menu_c" role="menu">
									<li class=""><a href="#">New Report</a></li>
									<li class=""><a href="#">Search Reports</a></li>
								</ul>
							</li>
						</security:authorize>
					</ul>
					<ul class="nav navbar-nav navbar-right navbar-right_c">
						<security:authorize access="isAnonymous()">
							<li class="link_register"><a href="<c:url value="/client/register" />">Register</a></li>
							<li class="link_login"><a href="<c:url value="/login" />">Login</a></li>
						</security:authorize>
						<li class="link_contact"><a href="<c:url value="/contact-us" />">Contact Us</a></li>
						<security:authorize access="isAuthenticated()">
							<li class="dropdown "><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false"> <c:set
										value="" var="dashboard" /> <c:set value="" var="roleName" />
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<c:url value="/admin/dashboard" var="dashboard" />
										<c:set value="Admin" var="roleName" />
									</security:authorize> <security:authorize access="hasRole('ROLE_TECHNICIAN')">
										<c:url value="/technician/dashboard" var="dashboard" />
										<c:set value="Technician" var="roleName" />
									</security:authorize> <security:authorize access="hasRole('PATIENT')">
										<c:url value="/patient/dashboard" var="dashboard" />
										<c:set value="Patient" var="roleName" />
									</security:authorize> <security:authorize access="hasRole('DOCTOR')">
										<c:url value="/doctor/dashboard" var="dashboard" />
										<c:set value="Doctor" var="roleName" />
									</security:authorize> ${loggedInUser.name} (${roleName}) <span class="fa fa-bars"></span></a>
								<ul class="dropdown-menu dropdown-menu_c" role="menu">
									<li class="dropdown-header">SETTINGS</li>
									<li class=""><a href="${dashboard}">My Dashboard</a></li>
									<li class=""><a
										href="<c:url value="/user/${loggedInUser.id}/account" />">My
											Account</a></li>
									<li class=""><a
										href="<c:url value="/user/${loggedInUser.id}/password" />">Change
											Password</a></li>
									<!-- <li class="divider"></li> -->
									<c:url value="/logout" var="logout" />
									<li><a href="${logout}"><span class="fa fa-sign-out"></span>
											Logout</a></li>
								</ul></li>
						</security:authorize>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container">
			<sitemesh-decorator:body />
		</div>
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/bootstrap.min.js' />"></script>
		<script type="text/javascript" src="<c:url  value='/resources/ui/custom/js/script.js' />"></script>
		
		<!-- Busy Modal -->
		<div class="modal fade" id="busy-modal" tabindex="-1"
			role="dialog" aria-labelledby="busyModal" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content busy-modal-content text-center">
					<div class="modal-header">
						<h4 class="modal-title busy-modal-title" id="busyModal">Processing your request...</h4>
					</div>
					<div class="modal-body custom-modal-body">
						<i class="fa fa-cog fa-spin fa-5x fa-fw"></i>
						<span class="sr-only">Loading...</span>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>