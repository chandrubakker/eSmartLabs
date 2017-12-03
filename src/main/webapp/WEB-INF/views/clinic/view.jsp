<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>eSmartLabs: Clinic: ${clinic.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							${clinic.name} <small>Clinic</small>
						</h1>
					</div>
				</div>
				
				<div class="row noPrint">
					<div class="col-lg-12">
						<a href="<c:url value="#"/>" onclick="history.go(-1);" class="btn btn-default btn-sm"> <i
							class="fa fa-hand-o-left"></i> Back
						</a>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-6">
						
						<h3 class="custom-heading-view">Clinic details</h3>
						
						<div class="row">
							<div class="col-lg-4">
								Clinic Name
							</div>
							<div class="col-lg-8">
								${clinic.name}
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-4">
								About clinic
							</div>
							<div class="col-lg-8">
								${clinic.info}
							</div>
						</div>
					</div>
					
					<div class="col-lg-6">
						<h3 class="custom-heading-view">Clinic address</h3>
						
						<div class="row">
							<div class="col-lg-4">
								Address line
							</div>
							<div class="col-lg-8">
								${clinic.address.address}
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-4">
								Locality/Area
							</div>
							<div class="col-lg-8">
								${clinic.address.locality}
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-4">
								City
							</div>
							<div class="col-lg-8">
								${clinic.address.city}
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-4">
								State
							</div>
							<div class="col-lg-8">
								${clinic.address.state}
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-4">
								Country
							</div>
							<div class="col-lg-8">
								${clinic.address.country} - ${clinic.address.zipCode}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>