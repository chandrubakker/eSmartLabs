<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>eSmartLabs: Patient Profile: ${patient.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Profile <small> ${patient.name}</small>
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
					<div class="col-lg-6">
						<h3>Overview</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Registration Number</td>
									<th>${patient.regNum}</th>
								</tr>
								<tr>
									<td>Registered On</td>
									<th>
										<fmt:formatDate value="${patient.dateOfAdmission}" pattern="dd MMM, YYYY"/>
									</th>
								</tr>
								<tr>
									<td>Patient Name</td>
									<th>${patient.name}</th>
								</tr>
								<tr>
									<td>Gender</td>
									<th>${patient.gender}</th>
								</tr>
								<tr>
									<td>Age</td>
									<th>${patient.age} Year(s)</th>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-lg-6">
						<h3>Medical Information</h3>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Referred By</td>
									<th>
										<c:choose>
											<c:when test="${patient.selfInterest eq true}">
												Self Interest
											</c:when>
											<c:otherwise>
												<i class="fa fa-link" aria-hidden="true"></i> 
												<a href="<c:url value="/doctor/${patient.referredDoctor.id}/view"/>" target="_blank">
													Dr. ${patient.referredDoctor.name}
												</a>
											</c:otherwise>
										</c:choose>
									</th>
								</tr>
								<tr>
									<td>Tests Required</td>
									<th>
										<ol>
											<c:forEach items="${patient.tests}" var="test">
												<li>
													<a href="<c:url value="/test/${test.id}/view" />" target="_blank">
														${test.name}
													</a>
												</li>
											</c:forEach>	
										</ol>
									</th>
								</tr>
								<tr>
									<td>Report</td>
									<c:choose>
										<c:when test="${not empty patient.report}">
											<th>
												Report Generated
											</th>
										</c:when>
										<c:otherwise>
											<th>
												Report Not Generated
											</th>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>Address</td>
									<th>${patient.shortAddress}</th>
								</tr>
								<tr>
									<td>Phone</td>
									<th>${patient.phone}</th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>