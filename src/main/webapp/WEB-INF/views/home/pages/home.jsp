<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
	<head>
		<title>eSmartLabs: Home</title>
	</head>
	<body>
		<section>
			<!-- Page Content -->
		    <div class="container">
		
		        <!-- Heading Row -->
		        <div class="row">
		            <div class="col-md-8">
		                <img class="img-responsive" src="<c:url value="/resources/ui/images/banner_four.jpg" />" alt="home_banner">
		            </div>
		            <div class="col-md-4">
		                <h1>eSmartLabs</h1>
		                <p>This is a medical laboratory management software. Used to maintain departments, tests, patients, doctors and associated clinics.</p>
		                <p>For more info mail to   <a href="mailto:pawssorcerer@gmail.com">pawssorcerer@gmail.com</a></p>
		                <br>
		                <security:authorize access="isAnonymous()">
		                	<a class="btn btn-primary btn-lg" href="<c:url value="/client/register" />">Register Now</a>
		                </security:authorize>
		            </div>
		        </div>
		        <hr>
		        <div class="row">
		            <div class="col-md-4">
		                <h2>Easy To Use</h2>
		                <ul>
		                	<li>Responsive UI - Can be used in PC, Tabs and SmartPhones</li>
		                	<li>Easy to use</li>
		                	<li>Profile based views</li>
		                </ul>
		            </div>
		            <div class="col-md-4">
		                <h2>Tests Management</h2>
		                <ul>
		                	<li>Departments Management (Ex. Hematology, Serology)</li>
		                	<li>Test managements</li>
		                	<li>Test ranges and prices</li>
		                </ul>
		            </div>
		            <div class="col-md-4">
		                <h2>Patients Tracking</h2>
		                <ul>
		                	<li>Associated clinics and doctors directory</li>
		                	<li>Patients tracking system</li>
		                	<li>Automated report generation</li>
		                </ul>
		            </div>
		        </div>
				<hr>
		        <footer>
		            <div class="row">
		                <div class="col-lg-12">
		                    <p class="pull-left"><i class="fa fa-copyright" aria-hidden="true"></i> <a href="<c:url value="/" />">eSmartLabs</a> - 2017</p>
		                    <%-- <p class="pull-right"><i class="fa fa-globe" aria-hidden="true"></i> <a href="<c:url value="/" />">www.sorcererpaws.com</a></p> --%>
		                </div>
		            </div>
		        </footer>
		    </div>
		</section>
	</body>
</html>