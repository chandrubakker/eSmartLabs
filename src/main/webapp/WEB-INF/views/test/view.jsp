<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>eSmartLabs: Test: ${test.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							${test.code} <small>${test.name}</small>
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
						<h3 class="page-header">Overview</h3>
						<div class="row">
							<div class="col-lg-4">
								 Department
							</div>
							<div class="col-lg-8">
								 <i class="fa fa-link" aria-hidden="true"></i> <a href="<c:url value="/department/${test.department.id}/view"/>">${test.department.name}</a>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								 Test Name
							</div>
							<div class="col-lg-8">
								 ${test.name}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								 Test Code
							</div>
							<div class="col-lg-8">
								 ${test.code}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								 Test Price
							</div>
							<div class="col-lg-8">
								 <i class="fa fa-inr" aria-hidden="true"></i> ${test.price}
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<h3 class="page-header">Reference Ranges</h3>
						
						<div class="row">
							<div class="col-lg-4">
								Unit Type
							</div>
							<div class="col-lg-8">
								<c:choose>
									<c:when test="${test.unitType eq 'ranges'}">
										Has Ranges
									</c:when>
									<c:otherwise>
										Test result will be either Positive/Negative
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:if test="${test.unitType eq 'ranges'}">
							<br>
							<div class="row">
								<div class="col-lg-4">
									Minimum Value
								</div>
								<div class="col-lg-8">
									${test.normalMin} ${test.unitUsed}
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-lg-4">
									Maximum Value
								</div>
								<div class="col-lg-8">
									${test.normalMax} ${test.unitUsed}
								</div>
							</div>
						</c:if>	
					</div>
				</div>
			</div>
		</div>
	</body>
</html>