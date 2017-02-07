<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<title>eSmartLabs: Lab Profile</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Lab <small>Profile</small>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<a href="<c:url value="#"/>" class="btn btn-default btn-sm"> <i
							class="fa fa-hand-o-left"></i> Back
						</a>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-6">
						<h3>Lab details</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<th>Lab Name</th>
									<td>${lab.name}</td>
								</tr>
								<tr>
									<th>Estd. Date</th>
									<td><fmt:formatDate value="${lab.estdDate}"
											pattern="dd MMM, yyyy" /></td>
								</tr>
								<tr>
									<th>About lab</th>
									<td>${lab.info}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-6">
						<h3>Lab address</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<th>Address line</th>
									<td>${lab.address.address}</td>
								</tr>
								<tr>
									<th>Locality/Area</th>
									<td>${lab.address.locality}</td>
								</tr>
								<tr>
									<th>City</th>
									<td>${lab.address.city}</td>
								</tr>
								<tr>
									<th>State</th>
									<td>${lab.address.state}</td>
		
								</tr>
								<tr>
									<th>Country</th>
									<td>${lab.address.country} - ${lab.address.zipCode}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>