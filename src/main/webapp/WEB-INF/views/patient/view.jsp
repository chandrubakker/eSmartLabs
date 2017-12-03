<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>eSmartLabs: Patient Profile: ${patient.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							Profile <small> ${patient.name}</small>
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
						<h3 class="custom-heading-view">Overview</h3>
						<div class="row">
							<div class="col-lg-4">
								Registration Number
							</div>
							<div class="col-lg-8">
								${patient.regNum}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Registered On
							</div>
							<div class="col-lg-8">
								<fmt:formatDate value="${patient.dateOfAdmission}" pattern="dd MMM, YYYY"/>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Patient Name
							</div>
							<div class="col-lg-8">
								${patient.name}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Gender
							</div>
							<div class="col-lg-8">
								${patient.gender}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Age
							</div>
							<div class="col-lg-8">
								${patient.age} Year(s)
							</div>
						</div>
					</div>
					
					<div class="col-lg-6">
						<h3 class="custom-heading-view">Medical Information</h3>
						<div class="row">
							<div class="col-lg-4">
								Referred By
							</div>
							<div class="col-lg-8">
								<c:choose>
									<c:when test="${patient.selfInterest eq true}">
										Self Interest
									</c:when>
									<c:otherwise>
										<i class="fa fa-link" aria-hidden="true"></i> 
										<a href="<c:url value="/doctor/${patient.referredDoctor.id}/view"/>" class="noPrint" target="_blank">
											Dr. ${patient.referredDoctor.name}
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Tests Required
							</div>
							<div class="col-lg-8">
								<ol>
									<c:forEach items="${patient.tests}" var="test">
										<li>
											<a href="<c:url value="/test/${test.id}/view" />" class="noPrint" target="_blank">
												${test.name}
											</a>
										</li>
									</c:forEach>	
								</ol>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Report
							</div>
							<div class="col-lg-8">
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
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Address
							</div>
							<div class="col-lg-8">
								${patient.shortAddress}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Phone
							</div>
							<div class="col-lg-8">
								${patient.phone}
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>