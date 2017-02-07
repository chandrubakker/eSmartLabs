<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>eSmartLabs: Profile: Dr. ${doctor.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Profile <small>Dr. ${doctor.name}</small>
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
					<div class="col-lg-12">
						<h3>Overview</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Hospital/Clinic</td>
									<th><i class="fa fa-link" aria-hidden="true"></i> <a href="<c:url value="/clinic/${doctor.clinic.id}/view"/>">${doctor.clinic.name}</a></th>
								</tr>
								<tr>
									<td>Doctor Name</td>
									<th>${doctor.name}</th>
								</tr>
								<tr>
									<td>Gender</td>
									<th>${doctor.gender}</th>
								</tr>
								<tr>
									<td>Doctor's Specialization</td>
									<th>${doctor.specialization}</th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>