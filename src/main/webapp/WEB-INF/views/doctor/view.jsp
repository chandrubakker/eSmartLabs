<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>eSmartLabs: Profile: Dr. ${doctor.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							Profile <small>Dr. ${doctor.name}</small>
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
					<div class="col-lg-12">
						<h3 class="custom-heading-view">Overview</h3>
						
						<div class="row">
							<div class="col-lg-4">
								Hospital/Clinic
							</div>
							<div class="col-lg-8">
								<i class="fa fa-link" aria-hidden="true"></i> <a href="<c:url value="/clinic/${doctor.clinic.id}/view"/>">${doctor.clinic.name}</a>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Doctor Name
							</div>
							<div class="col-lg-8">
								${doctor.name}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Gender
							</div>
							<div class="col-lg-8">
								${doctor.gender}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Doctor's Specialization
							</div>
							<div class="col-lg-8">
								${doctor.specialization}
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>