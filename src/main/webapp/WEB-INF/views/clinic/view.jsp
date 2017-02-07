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
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							${clinic.name} <small>Clinic</small>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<a href="<c:url value="#"/>" onclick="history.go(-1);" class="btn btn-default btn-sm"> <i
							class="fa fa-hand-o-left"></i> Back
						</a>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-6">
						<h3>Clinic details</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<th>Clinic Name</th>
									<td>${clinic.name}</td>
								</tr>
								<tr>
									<th>About clinic</th>
									<td>${clinic.info}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-6">
						<h3>Clinic address</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<th>Address line</th>
									<td>${clinic.address.address}</td>
								</tr>
								<tr>
									<th>Locality/Area</th>
									<td>${clinic.address.locality}</td>
								</tr>
								<tr>
									<th>City</th>
									<td>${clinic.address.city}</td>
								</tr>
								<tr>
									<th>State</th>
									<td>${clinic.address.state}</td>
								</tr>
								<tr>
									<th>Country</th>
									<td>${clinic.address.country} - ${clinic.address.zipCode}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>