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
				font-family: lato-light;
				src: url("<c:url  value='/resources/ui/fonts/lato/Lato-Light.ttf' />");
			}
			
		</style>
		
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/css/bootstrap.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/font-awesome/css/font-awesome.css' />" />
		
		<link rel="stylesheet" href="<c:url  value='/resources/ui/adminTheme/css/style.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/date-picker/bootstrap-datepicker.min.css' />" />
		
		<!-- DataTables CSS-->
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/media/css/jquery.dataTables.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/extensions/Buttons/css/buttons.dataTables.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/pluggins/dataTable/extensions/Select/css/select.dataTables.css' />" />
		
		<!-- Chosen Plug-in -->	
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/choosen/prism.css" />" />
		<link rel="stylesheet" href="<c:url value="/resources/ui/pluggins/choosen/choosen.css" />" />
		
		<!-- No Print -->
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/ui/adminTheme/css/no-print.css" />" media="print" />
		
		<!-- JQuery -->
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/jquery.js' />"></script>
	</head>
	<body>
		<div class="row">
		    <!-- uncomment code for absolute positioning tweek see top comment in css -->
		    <!-- <div class="absolute-wrapper"> </div> -->
		    <!-- Menu -->
		    <div class="side-menu">
		    	
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
				
			    <nav class="navbar navbar-default" role="navigation">
			    	<!-- Brand and toggle get grouped for better mobile display -->
			    	<div class="navbar-header">
			        	<div class="brand-wrapper">
			            	<!-- Hamburger -->
			            	<button type="button" class="navbar-toggle">
				                <span class="sr-only">Toggle navigation</span>
				                <span class="icon-bar"></span>
				                <span class="icon-bar"></span>
				                <span class="icon-bar"></span>
			            	</button>
			
				            <!-- Brand -->
				            <div class="brand-name-wrapper">
				                <a class="navbar-brand" href="<c:url value="${brandLink}" />">
				                    <i class="${brandIcon}" aria-hidden="true"></i> ${brandTitle}
				                </a>
				            </div>
				
				            <!-- Search -->
				            <a data-toggle="collapse" href="#search" class="btn btn-default" id="search-trigger">
				                <span class="glyphicon glyphicon-search"></span>
				            </a>
			
				            <!-- Search body -->
				            <div id="search" class="panel-collapse collapse">
				                <div class="panel-body">
				                    <form class="navbar-form" role="search">
				                        <div class="form-group">
				                            <input type="text" class="form-control" placeholder="Search">
				                        </div>
				                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-ok"></span></button>
				                    </form>
				                </div>
				            </div>
			        	</div>
			    	</div>
			
				    <!-- Main Menu -->
				    <div class="side-menu-container">
				        <ul class="nav navbar-nav">
							
							<security:authorize access="isAuthenticated()">
					            <li class="esm-single-link active">
					            	<a href="<c:url value="${brandLink}" />">
					            		<span class="fa fa-tachometer"></span> Dashboard
					            	</a>
					            </li>
				            </security:authorize>
				            
				            <security:authorize ifAnyGranted="ROLE_ADMIN">
				            	
				            	<!-- Company Links -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-org-lvl1">
					                	<span class="fa fa-building-o"></span> My Company <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-org-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/company/profile" />">Profile</a></li>
					                            <li class="esm-single-link"><a href="<c:url value="/company/update" />">Update</a></li>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                 <!-- Labs Links -->
				                 <li class="panel panel-default esm-side-link">
					             	<a data-toggle="collapse" href="#dropdown-labs-lvl1">
					                    <span class="fa fa-flask"></span> Labs <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					             	</a>
					
					                <!-- Dropdown level 1 -->
					                <div id="dropdown-labs-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/labs" />">All</a></li>
					
												<c:if test="${lab.id gt 0}">
						                            <!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                <a data-toggle="collapse" href="#dropdown-labs-lvl2">
						                                    <span class="fa fa-bars"></span> ${lab.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
						                                </a>
						                                <div id="dropdown-labs-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/lab/${lab.id}/profile" />">View</a>
						                                            </li>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                        </ul>
					                    </div>
					                </div>
					            </li>
				            </security:authorize>
				            
				            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
				            
				            	<!-- My Lab -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-lab-lvl1">
					                	<span class="fa fa-flask"></span> My Lab <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-lab-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/lab/${loggedInUser.client.lab.id}/profile" />">Profile</a></li>
					                            <li class="esm-single-link"><a href="<c:url value="/lab/${loggedInUser.client.lab.id}/update" />">Update</a></li>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				            </security:authorize>
				            
				            <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_TECHNICIAN')">
				            
				            	<!-- Departments -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-dept-lvl1">
					                	<span class="fa fa-folder-open-o"></span> Departments <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-dept-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/departments" />">All</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/department/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${department.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(department.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-dept-lvl2">
								                                    <span class="fa fa-bars"></span> ${department.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-dept-lvl2" data-toggle="tooltip" title="${department.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(department.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-dept-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/department/${department.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/department/${department.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                 <!-- Tests -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-test-lvl1">
					                	<span class="fa fa-hourglass-o"></span> Tests <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-test-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/tests" />">All</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/test/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${test.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(test.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-test-lvl2">
								                                    <span class="fa fa-bars"></span> ${test.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-test-lvl2" data-toggle="tooltip" title="${test.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(test.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-test-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/test/${test.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/test/${test.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                  <!-- Test Group -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-testgrp-lvl1">
					                	<span class="fa fa-object-group"></span> Test Groups <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-testgrp-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/test-groups" />">All</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/test-group/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${testGroup.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(testGroup.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-testgrp-lvl2">
								                                    <span class="fa fa-bars"></span> ${testGroup.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-testgrp-lvl2" data-toggle="tooltip" title="${testGroup.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(testGroup.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-testgrp-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/test-group/${testGroup.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/test-group/${testGroup.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                 <!-- Clinics -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-clinic-lvl1">
					                	<span class="fa fa-h-square"></span> Clinics <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-clinic-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/clinics" />">All</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/clinic/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${clinic.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(clinic.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-clinic-lvl2">
								                                    <span class="fa fa-bars"></span> ${clinic.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-clinic-lvl2" data-toggle="tooltip" title="${clinic.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(clinic.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-clinic-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/clinic/${clinic.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/clinic/${clinic.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                 <!-- Doctor -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-doctor-lvl1">
					                	<span class="fa fa-stethoscope"></span> Doctors <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-doctor-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/doctors" />">All</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/doctor/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${doctor.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(doctor.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-doctor-lvl2">
								                                    <span class="fa fa-bars"></span> ${doctor.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-doctor-lvl2" data-toggle="tooltip" title="${doctor.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(doctor.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-doctor-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/doctor/${doctor.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/doctor/${doctor.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				                 
				                 <!-- Patient -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-patient-lvl1">
					                	<span class="fa fa-heartbeat"></span> Patients <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-patient-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/patients" />">All Patients</a></li>
					                            <li class="esm-single-link"><a href="<c:url value="/reports" />">All Reports</a></li>
					                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
					                            	<li class="esm-single-link">
					                            		<a href="<c:url value="/patient/create" />">Create</a>
					                            	</li>
					                            </security:authorize>
					                            <c:if test="${patient.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(patient.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-patient-lvl2">
								                                    <span class="fa fa-bars"></span> ${patient.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-patient-lvl2" data-toggle="tooltip" title="${patient.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(patient.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-patient-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/patient/${patient.id}/view" />">View</a>
						                                            </li>
						                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
							                                            <li class="esm-single-link">
							                                            	<a href="<c:url value="/patient/${patient.id}/update" />">Update</a>
							                                            </li>
						                                            </security:authorize>
						                                            <c:choose>
						                                            	<c:when test="${empty patient.report or patient.report.id le 0}">
						                                            		<security:authorize access="hasRole('ROLE_TECHNICIAN')">
						                                            			<li class="esm-single-link">
									                                            	<a href="<c:url value="/patient/${patient.id}/report/generate" />">Create Report</a>
									                                            </li>
						                                            		</security:authorize>
						                                            	</c:when>
						                                            	<c:otherwise>
						                                            		<li class="esm-single-link">
								                                            	<a href="<c:url value="/patient/${patient.id}/report/view" />">Show Report</a>
								                                            </li>
								                                            <security:authorize access="hasRole('ROLE_TECHNICIAN')">
						                                            			<li class="esm-single-link">
									                                            	<a href="<c:url value="/patient/${patient.id}/report/update" />">Update Report</a>
									                                            </li>
						                                            		</security:authorize>
						                                            	</c:otherwise>
						                                            </c:choose>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				            	
				            </security:authorize>
				            
				            <security:authorize access="isAuthenticated()">
				            	<!-- User Links -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-user-lvl1">
					                	<span class="fa fa-users"></span> My Account <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-user-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/user/${loggedInUser.id}/account" />">View</a></li>
					                            <li class="esm-single-link"><a href="<c:url value="/user/${loggedInUser.id}/update" />">Update</a></li>
					                            <li class="esm-single-link"><a href="<c:url value="/user/${loggedInUser.id}/password" />">Change Password</a></li>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				            </security:authorize>
				            
				            <security:authorize access="hasRole('ROLE_ADMIN')">
				            	
				            	<!-- Users -->
				            	<li class="panel panel-default esm-side-link">
					                <a data-toggle="collapse" href="#dropdown-users-lvl1">
					                	<span class="fa fa-stethoscope"></span> Users <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
					                </a>
					
					                <!-- Dropdown level 1 -->
					               	<div id="dropdown-users-lvl1" class="panel-collapse collapse">
					                    <div class="panel-body">
					                        <ul class="nav navbar-nav">
					                            <li class="esm-single-link"><a href="<c:url value="/users" />">All</a></li>
					                            <c:if test="${user.id gt 0}">
					                            	
					                            	<!-- Dropdown level 2 -->
						                            <li class="panel panel-default esm-side-link">
						                                
						                                <c:choose>
															<c:when test="${fn:length(user.name) lt 30 }">
								                                <a data-toggle="collapse" href="#dropdown-users-lvl2">
								                                    <span class="fa fa-bars"></span> ${user.name} <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:when>
						                                	<c:otherwise>
						                                		<a data-toggle="collapse" href="#dropdown-users-lvl2" data-toggle="tooltip" title="${user.name}">
								                                    <span class="fa fa-bars"></span> ${fn:substring(user.name, 0, 20)} ... <i class="fa fa fa-plus-square-o link-caret" aria-hidden="true"></i>
								                                </a>
						                                	</c:otherwise>
						                                </c:choose>	
						                                <div id="dropdown-users-lvl2" class="panel-collapse collapse">
						                                    <div class="panel-body">
						                                        <ul class="nav navbar-nav">
						                                            <li class="esm-single-link">
						                                            	<a href="<c:url value="/user/${user.id}/account" />">View</a>
						                                            </li>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </li>
					                            </c:if>
					                       	</ul>
					                    </div>
					                </div>
				                 </li>
				            </security:authorize>
				            
				            <li class="esm-single-link"><a href="#"><span class="fa fa-info-circle"></span> Help</a></li>
				
				        </ul>
				    </div><!-- /.navbar-collapse -->
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
		
		    <!-- Main Content -->
		    <div class="container-fluid">
		        <div class="side-body">
		         	<sitemesh-decorator:body />
		        </div>
		    </div>
		</div>
		
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/bootstrap.min.js' />"></script>
		
		<!-- ChoosenPlug -->
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/choosen/choosen.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/choosen/prism.js" />"></script>
		
		<!-- DateTime Picker -->
		<script type="text/javascript" src="<c:url value="/resources/ui/pluggins/dateTimePicker/js/bootstrap-datetimepicker.min.js" />"></script>
		
		<script src="<c:url value="/resources/ui/adminTheme/js/script.js" />"></script>
		<script type="text/javascript" src="<c:url  value='/resources/ui/date-picker/bootstrap-datepicker.min.js' />"></script>
		
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