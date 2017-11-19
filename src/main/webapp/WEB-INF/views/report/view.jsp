<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>eSmartLabs: Report: ${patient.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Report <small> of ${patient.name}</small>
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
						<table class="table table-responsive">
							<tbody>
								<tr>
									<td>Name</td>
									<td>${patient.name}</td>
									<td>Age</td>
									<td>${patient.age}</td>
								</tr>
								<tr>
									<td>Gender</td>
									<td>${patient.gender}</td>
									<td>Ref. By Dr.</td>
									<td>
										<c:choose>
											<c:when test="${patient.selfInterest eq true}">
												Self Interest 
											</c:when>
											<c:otherwise>
												${patient.referredDoctor.name}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td>Age</td>
									<td>${patient.age}</td>
									<td>Date</td>
									<td>
										<fmt:formatDate value="${patient.dateOfAdmission}" pattern="dd, MMM, YYYY" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							Test Results
						</h3>
					</div>
					<div class="col-lg-12">
						<table class="table table-responsive">
							<thead>
								<tr>
									<th>Test</th>
									<th>Department</th>
									<th>Min. Normal Value</th>
									<th>Observed Value</th>
									<th>Max. Normal Value</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${patient.report.testResults}" var="testResult">
									<tr>
										<td>${testResult.test.name}</td>
										<td>${testResult.test.department.name}</td>
										<td>${testResult.test.normalMin} ${testResult.test.unitUsed}</td>
										<c:choose>
											<c:when test="${testResult.observedValue gt testResult.test.normalMax}">
												<td>
													<strong>${testResult.observedValue} ${testResult.test.unitUsed}</strong>
												</td>
											</c:when>
											<c:when test="${testResult.observedValue lt testResult.test.normalMin}">
												<td>
													<strong>${testResult.observedValue} ${testResult.test.unitUsed}</strong>
												</td>
											</c:when>
											<c:otherwise>
												<td>${testResult.observedValue} ${testResult.test.unitUsed}</td>
											</c:otherwise>
										</c:choose>
										<td>${testResult.test.normalMax} ${testResult.test.unitUsed}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>