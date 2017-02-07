<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>eSmartLabs: Test: ${test.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							${test.code} <small>${test.name}</small>
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
						<h3>Overview</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Department</td>
									<th><i class="fa fa-link" aria-hidden="true"></i> <a href="<c:url value="/department/${test.department.id}/view"/>">${test.department.name}</a></th>
								</tr>
								<tr>
									<td>Test Name</td>
									<th>${test.name}</th>
								</tr>
								<tr>
									<td>Test Code</td>
									<th>${test.code}</th>
								</tr>
								<tr>
									<td>Test Price</td>
									<th><i class="fa fa-inr" aria-hidden="true"></i> ${test.price}</th>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-6">
						<h3>Reference ranges</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Minimum Value</td>
									<th>${test.normalMin} ${test.unitUsed}</th>
								</tr>
								<tr>
									<td>Maximum Value</td>
									<th>${test.normalMax} ${test.unitUsed}</th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>