<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="App Description">
		<meta name="author" content="Chandrashekhar">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>
			<sitemesh-decorator:title default="eSmartLabs" />
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
		
		<!-- Date Picker -->
		<link rel="stylesheet" href="<c:url  value='/resources/ui/adminTheme/css/style.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/date-picker/bootstrap-datepicker.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/magicSuggest/magicsuggest-min.css' />" />
		
		<!-- Switch -->
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/switch/bootstrap-switch.css' />" />
		
		<!-- DataTables CSS-->
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/media/css/jquery.dataTables.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/extensions/Buttons/css/buttons.dataTables.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/extensions/Select/css/select.dataTables.css' />" />
		
		<!-- Chosen Plug-in -->	
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/choosen/prism.css" />" />
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/choosen/choosen.css" />" />
		
		<!-- MetisMenu CSS&JS -->
		<link rel="stylesheet" href='<c:url value="/resources/ui/adminTheme/css/metisMenu.min.css" />' />
		
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/dateTimePicker/css/bootstrap-datetimepicker.min.css" />" />
		<link rel="stylesheet" href='<c:url value="/resources/ui/adminTheme/css/dash.css" />' />
		
		<!-- Gallery Corosol -->
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/blue-imp-gallery/css/blueimp-gallery.min.css" />">
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/blue-imp-gallery/css/bootstrap-image-gallery.css" />">
		
		<!-- Bootstrap MultiSelect CSS and JS: -->
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/Boo_MultiSelect/css/bootstrap-multiselect.css" />" type="text/css"/>
		
		<!-- No Print -->
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/ui/adminTheme/css/no-print.css" />" media="print" />
		<!-- JQuery -->
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/jquery.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
		
			<nav class="navbar navbar-default navbar-static-top custom_navbar" role="navigation">
				<div class="navbar-header">
					
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					
					<c:set value="#" var="brandLink" scope="application" />
					<c:set value="" var="brandTitle" />
					<c:set value="fa fa-desktop" var="brandIcon" />
					<c:choose>
						<c:when test="${loggedInUser.role.id eq 1}">
							<c:set value="/admin/dashboard" var="brandLink" />
							<c:set value="Admin" var="brandTitle" />
							<c:set value="fa fa-desktop" var="brandIcon" />
						</c:when>
						<c:when test="${loggedInUser.role.id eq 2}">
							<c:set value="/technician/dashboard" var="brandLink" />
							<c:set value="${loggedInUser.client.lab.name}" var="brandTitle" />
							<c:set value="fa fa-flask" var="brandIcon" />
						</c:when>
						<c:when test="${loggedInUser.role.id eq 3}">
							<c:set value="/doctor/dashboard" var="brandLink" />
							<c:set value="Doctor" var="brandTitle" />
							<c:set value="fa fa-desktop" var="brandIcon" />
						</c:when>
						<c:otherwise>
							<c:set value="/patient/dashboard" var="brandLink" />
							<c:set value="Patient" var="brandTitle" />
							<c:set value="fa fa-desktop" var="brandIcon" />
						</c:otherwise>
					</c:choose>
					<a class="navbar-brand" href="<c:url value="${brandLink}" />">
						<i class="${brandIcon}" aria-hidden="true"></i> ${brandTitle}
					</a>
				</div>
				<!-- /.navbar-header -->
			
				<ul class="nav navbar-top-links navbar-right">
					<li>
						<a href="<c:url value="/" />" target="_blank">
							<i class="fa fa-home" aria-hidden="true"></i> Home
						</a>
					</li>
					
					<!-- /.dropdown -->
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="fa fa-caret-down"></i>
							<i class="fa fa-user fa-fw"></i>
							${loggedInUser.name}
						</a>
						
						<ul class="dropdown-menu dropdown-user">
							<li>
								<a href="<c:url value="/user/${loggedInUser.id}/account" />">
									<i class="fa fa-user-md" aria-hidden="true"></i> User Account
								</a>
							</li>
							<li>
								<a href="<c:url value="/user/${loggedInUser.id}/password" />">
									<i class="fa fa-key" aria-hidden="true"></i> Change password
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="<c:url value="/logout" />">
									<i class="fa fa-sign-out" aria-hidden="true"></i> Logout
								</a>
							</li>
						</ul> <!-- /.dropdown-user -->
					</li>
					<!-- /.dropdown -->
				</ul>
				<!-- /.navbar-top-links -->
			
				<div class="navbar-default sidebar custom-sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<security:authorize access="isAuthenticated()">
								<li>
									<a href="<c:url value="${brandLink}" />">
										<i class="fa fa-tachometer" aria-hidden="true"></i> Dashboard
									</a>
								</li>
							</security:authorize>
							
							<security:authorize ifAnyGranted="ROLE_ADMIN">
								<li>
									<a href="#">
										<i class="fa fa-building-o" aria-hidden="true"></i> My Company <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/company/profile" />">
												<i class="fa fa-eye" aria-hidden="true"></i> Profile
											</a>
										</li>
										<li>
											<a href="<c:url value="/company/update" />">
												<i class="fa fa-edit" aria-hidden="true"></i> Update
											</a>
										</li>
									</ul>
								</li>
							</security:authorize>
							
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="labLI">
									<a href="#">
										<i class="fa fa-flask" aria-hidden="true"></i> Labs <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/labs" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<c:if test="${lab.id gt 0}">
											<li>
												<a href="#">
													<i class="fa fa-bars" aria-hidden="true"></i> ${lab.name} <span class="fa arrow"></span>
												</a>
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/lab/${lab.id}/profile" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<%-- <li>
														<a href="<c:url value="/lab/${lab.id}/update" />">
															<i class="fa fa-edit" aria-hidden="true"></i> Update
														</a>
													</li> --%>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
							</security:authorize>
							
							<security:authorize access="hasRole('ROLE_TECHNICIAN')">
								<li>
									<a href="#">
										<i class="fa fa-flask" aria-hidden="true"></i> My Lab <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/lab/${loggedInUser.client.lab.id}/profile" />">
												<i class="fa fa-eye" aria-hidden="true"></i> Profile
											</a>
										</li>
										<li>
											<a href="<c:url value="/lab/${loggedInUser.client.lab.id}/update" />">
												<i class="fa fa-edit" aria-hidden="true"></i> Update
											</a>
										</li>
									</ul>
								</li>
							</security:authorize>
							
							<security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_TECHNICIAN')">
								<li class="deptLI">
									<a href="#">
										<i class="fa fa-folder-open-o" aria-hidden="true"></i> Departments <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/departments" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<security:authorize access="hasRole('ROLE_TECHNICIAN')">
											<li>
												<a href="<c:url value="/department/create" />">
													<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create
												</a>
											</li>
										</security:authorize>
										<c:if test="${department.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(department.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${department.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${department.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(department.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/department/${department.id}/view" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<security:authorize access="hasRole('ROLE_TECHNICIAN')">
														<li>
															<a href="<c:url value="/department/${department.id}/update" />">
																<i class="fa fa-edit" aria-hidden="true"></i> Update
															</a>
														</li>
													</security:authorize>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
								
								<li class="testLI">
									<a href="#">
										<i class="fa fa-hourglass-o" aria-hidden="true"></i> Tests <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/tests" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<security:authorize access="hasRole('ROLE_TECHNICIAN')">
											<li>
												<a href="<c:url value="/test/create" />">
													<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create
												</a>
											</li>
										</security:authorize>
										<c:if test="${test.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(test.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${test.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${test.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(test.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/test/${test.id}/view" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<security:authorize access="hasRole('ROLE_TECHNICIAN')">
														<li>
															<a href="<c:url value="/test/${test.id}/update" />">
																<i class="fa fa-edit" aria-hidden="true"></i> Update
															</a>
														</li>
													</security:authorize>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
								
								<li class="clinicLI">
									<a href="#">
										<i class="fa fa-h-square" aria-hidden="true"></i> Clinics <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/clinics" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<security:authorize access="hasRole('ROLE_TECHNICIAN')">
											<li>
												<a href="<c:url value="/clinic/create" />">
													<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create
												</a>
											</li>
										</security:authorize>
										<c:if test="${clinic.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(clinic.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${clinic.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${clinic.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(clinic.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/clinic/${clinic.id}/view" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<security:authorize access="hasRole('ROLE_TECHNICIAN')">
														<li>
															<a href="<c:url value="/clinic/${clinic.id}/update" />">
																<i class="fa fa-edit" aria-hidden="true"></i> Update
															</a>
														</li>
													</security:authorize>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
								
								<li class="doctorLI">
									<a href="#">
										<i class="fa fa-stethoscope" aria-hidden="true"></i> Doctors <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/doctors" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<security:authorize access="hasRole('ROLE_TECHNICIAN')">
											<li>
												<a href="<c:url value="/doctor/create" />">
													<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create
												</a>
											</li>
										</security:authorize>
										<c:if test="${doctor.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(doctor.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${doctor.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${doctor.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(doctor.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/doctor/${doctor.id}/view" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<security:authorize access="hasRole('ROLE_TECHNICIAN')">
														<li>
															<a href="<c:url value="/doctor/${doctor.id}/update" />">
																<i class="fa fa-edit" aria-hidden="true"></i> Update
															</a>
														</li>
													</security:authorize>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
								
								<li class="patientLI">
									<a href="#">
										<i class="fa fa-female" aria-hidden="true"></i> <i class="fa fa-male" aria-hidden="true"></i> Patients <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/patients" />">
												<i class="fa fa-table" aria-hidden="true"></i> All Patients
											</a>
										</li>
										<li>
											<a href="<c:url value="/reports" />">
												<i class="fa fa-file-image-o" aria-hidden="true"></i> All Reports
											</a>
										</li>
										<security:authorize access="hasRole('ROLE_TECHNICIAN')">
											<li>
												<a href="<c:url value="/patient/create" />">
													<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create
												</a>
											</li>
										</security:authorize>
										<c:if test="${patient.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(patient.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${patient.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${patient.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(patient.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/patient/${patient.id}/view" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
													<security:authorize access="hasRole('ROLE_TECHNICIAN')">
														<li>
															<a href="<c:url value="/patient/${patient.id}/update" />">
																<i class="fa fa-edit" aria-hidden="true"></i> Update
															</a>
														</li>
													</security:authorize>
													<c:choose>
														<c:when test="${empty patient.report or patient.report.id le 0}">
															<security:authorize access="hasRole('ROLE_TECHNICIAN')">
																<li>
																	<a href="<c:url value="/patient/${patient.id}/report/generate" />">
																		<i class="fa fa-plus-square-o" aria-hidden="true"></i> Create Report
																	</a>
																</li>
															</security:authorize>
														</c:when>
														<c:otherwise>
															<li>
																<a href="<c:url value="/patient/${patient.id}/report/view" />">
																	<i class="fa fa-eye" aria-hidden="true"></i> Show Report
																</a>
															</li>
															<security:authorize access="hasRole('ROLE_TECHNICIAN')">
																<li>
																	<a href="<c:url value="/patient/${patient.id}/report/update" />">
																		<i class="fa fa-edit" aria-hidden="true"></i> Update Report
																	</a>
																</li>
															</security:authorize>
														</c:otherwise>
													</c:choose>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
							</security:authorize>
							
							<security:authorize access="isAuthenticated()">
								<li>
									<a href="#">
										<i class="fa fa-user-md" aria-hidden="true"></i> My Account <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/user/${loggedInUser.id}/account" />">
												<i class="fa fa-eye" aria-hidden="true"></i> View
											</a>
										</li>
										<li>
											<a href="<c:url value="/user/${loggedInUser.id}/update" />">
												<i class="fa fa-edit" aria-hidden="true"></i> Update
											</a>
										</li>
										<li>
											<a href="<c:url value="/user/${loggedInUser.id}/password" />">
												<i class="fa fa-key" aria-hidden="true"></i> Change Password
											</a>
										</li>
									</ul>
								</li>
							</security:authorize>
							
							<%-- <security:authorize access="hasRole('ROLE_ADMIN')">
								<li>
									<a href="<c:url value="/users"/>">
										<i class="fa fa-users" aria-hidden="true"></i> Users
									</a>
								</li>
							</security:authorize> --%>
							
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="userLI">
									<a href="#">
										<i class="fa fa-users" aria-hidden="true"></i> Users <span class="fa arrow"></span>
									</a>
									<ul class="nav nav-second-level">
										<li>
											<a href="<c:url value="/users" />">
												<i class="fa fa-table" aria-hidden="true"></i> All
											</a>
										</li>
										<c:if test="${user.id gt 0}">
											<li>
												<c:choose>
													<c:when test="${fn:length(user.name) lt 30 }">
														<a href="#">
															<i class="fa fa-bars" aria-hidden="true"></i> ${user.name} <span class="fa arrow"></span>
														</a>
													</c:when>
													<c:otherwise>
														<a href="#" data-toggle="tooltip" title="${user.name}">
															<i class="fa fa-bars" aria-hidden="true"></i> ${fn:substring(user.name, 0, 20)} ...  <span class="fa arrow"></span>
														</a>
													</c:otherwise>
												</c:choose>
												<ul class="nav nav-third-level collapse in">
													<li>
														<a href="<c:url value="/user/${user.id}/account" />">
															<i class="fa fa-eye" aria-hidden="true"></i> View
														</a>
													</li>
												</ul>							
											</li>
										</c:if>
									</ul>
								</li>
							</security:authorize>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		
		<!-- DataTables JS-->
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/media/js/jquery.dataTables.min.js"/>">	
		</script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Buttons/js/dataTables.buttons.js"/>">	
		</script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Buttons/js/buttons.colVis.js"/>">	
		</script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Buttons/js/buttons.print.js"/>">	
		</script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Buttons/js/buttons.flash.js"/>">	
		</script>
		
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Buttons/js/buttons.html5.js"/>">	
		</script>
		
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dataTable/extensions/Select/js/dataTables.select.js"/>">	
		</script>
		
		<sitemesh-decorator:body />
		
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/bootstrap.min.js' />"></script>
		
		<!-- MGK SUGGEST JS-->
		<script type="text/javascript" src="<c:url  value='/resources/ui/magicSuggest/magicsuggest-min.js' />"></script>
		
		<!-- ChoosenPlug -->
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/choosen/choosen.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/choosen/prism.js" />"></script>
		<script src="<c:url value="/resources/ui/adminTheme/js/metisMenu.min.js" />"></script>
		
		<!-- DateTime Picker -->
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dateTimePicker/js/bootstrap-datetimepicker.min.js" />"></script>
		<script src="<c:url value="/resources/ui/adminTheme/js/dash.js" />"></script>
		
		<script type="text/javascript" src="<c:url  value='/resources/ui/adminTheme/js/adminScript.js' />"></script>
		<script type="text/javascript" src="<c:url  value='/resources/ui/date-picker/bootstrap-datepicker.min.js' />"></script>
		
		<script src="<c:url value="/resources/ui/pluggins/blue-imp-gallery/js/jquery.blueimp-gallery.min.js" />"></script>
		<script src="<c:url value="/resources/ui/pluggins/blue-imp-gallery/js/bootstrap-image-gallery.js" />"></script>
		
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/Boo_MultiSelect/js/bootstrap-multiselect.js" />"></script>

		<script type="text/javascript">
			$(document).ready(function() {
			    console.log( "ready!" );
			    var pathname = window.location.pathname; // Returns path only
			    console.log("PATH: " + pathname);
			    var url = window.location.href;     // Returns full URL
			    console.log("URL: " + url);
			    
			    if(url.indexOf("/lab/") !== -1 /* && url.indexOf("/create") === -1 */) {
			    	$(".labLI").addClass("active");
			    	$(".labLI>ul").addClass("in");
			    	$(".labLI>ul").attr("style", "max-height: 200px");
			    }
			    
			    if(url.indexOf("/department/") !== -1 && url.indexOf("/create") === -1) {
			    	$(".deptLI").addClass("active");
			    	$(".deptLI>ul").addClass("in");
			    	$(".deptLI>ul").attr("style", "max-height: 200px");
			    }
			    
			    if(url.indexOf("/test/") !== -1 && url.indexOf("/create") === -1) {
			    	$(".testLI").addClass("active");
			    	$(".testLI>ul").addClass("in");
			    	$(".testLI>ul").attr("style", "max-height: 200px");
			    }
			    
			    if(url.indexOf("/clinic/") !== -1 && url.indexOf("/create") === -1) {
			    	$(".clinicLI").addClass("active");
			    	$(".clinicLI>ul").addClass("in");
			    	$(".clinicLI>ul").attr("style", "max-height: 200px");
			    }
			    
			    if(url.indexOf("/doctor/") !== -1 && url.indexOf("/create") === -1) {
			    	$(".doctorLI").addClass("active");
			    	$(".doctorLI>ul").addClass("in");
			    	$(".doctorLI>ul").attr("style", "max-height: 200px");
			    }
			    
			    if(url.indexOf("/patient/") !== -1 && url.indexOf("/create") === -1) {
			    	$(".patientLI").addClass("active");
			    	$(".patientLI>ul").addClass("in");
			    	$(".patientLI>ul").attr("style", "max-height: 500px");
			    }
			    
			    if(url.indexOf("/user/") !== -1 && "${loggedInUser.id}" !== "${user.id}") {
			    	$(".userLI").addClass("active");
			    	$(".userLI>ul").addClass("in");
			    	$(".userLI>ul").attr("style", "max-height: 200px");
			    }
			});
		</script>
	
		<!-- Delete Confirm -->
		
		<script type="text/javascript">
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
		</script>
		
		<!-- Chosen PluggIn -->
		<script type="text/javascript">
			var config = {
				'.chosen-select' : {},
			}
			for ( var selector in config) {
				$(selector).chosen(config[selector]);
			}
			
			function refreshPage() {
				location.reload();
			}
		</script>
	
		<!-- Delete Confirm Modal -->
		<div class="modal fade" id="deleteConfirm" tabindex="-1" role="dialog"
			data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content delModalContent">
					<div class="modal-header">
						<h4 class="modal-title text-center">Warning</h4>
					</div>
					<div class="modal-body text-center">
						<p class="deleteMsg"></p>
						<h3 class="delName"></h3>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-warning btn-sm deleteTarget" onclick="">Delete</a>
						<a href="#" class="btn btn-default btn-sm" data-dismiss="modal">Cancel</a>
					</div>
				</div>
			</div>
		</div>
	
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