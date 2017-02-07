<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>eSmartLabs: ${department.name} - Department</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							${department.code} <small>${department.name}</small>
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
					<div class="col-md-12">
						<h3>Overview</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Department Name</td>
									<th>${department.name}</th>
								</tr>
								<tr>
									<td>Department Code</td>
									<th>${department.code}</th>
								</tr>
								<tr>
									<td>About department</td>
									<td>${department.info}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>