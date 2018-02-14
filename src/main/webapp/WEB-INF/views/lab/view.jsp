<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<title>eSmartLabs: Lab Profile</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<h1 class="page-title-one text-center">
					Lab <small>Profile</small>
				</h1>
			</div>
		</div>
	
		<div class="row esm-back-row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<a href="#" class="btn btn-default btn-sm esm-button esm-back-btn">
					<i class="fa fa-hand-o-left"></i> Back
				</a>
			</div>
		</div>
	
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<div class="row">
					<div class="col-lg-6 view-col">
						<div class="row view-row">
							<div class="col-lg-6 view-col">Lab Name</div>
							<div class="col-lg-6 view-col">${lab.name}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Estd. Date</div>
							<div class="col-lg-6 view-col">
								<c:choose>
									<c:when test="${not empty lab.estdDate}">
										<fmt:formatDate value="${lab.estdDate}"
											pattern="dd MMM, yyyy" />
									</c:when>
									<c:otherwise>
										Not Available
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						
						<div class="row view-row">
							<div class="col-lg-12 view-col esm-uline">About</div>
							<div class="col-lg-12 view-col">
								<c:choose>
									<c:when test="${not empty lab.info}">
												${lab.info}
											</c:when>
									<c:otherwise>
												Not Available
											</c:otherwise>
								</c:choose>
							</div>
						</div>
						
						<div class="row view-row">
							<div class="col-lg-6 view-col">Contact Number</div>
							<div class="col-lg-6 view-col">
								<c:choose>
									<c:when test="${not empty lab.address.phone}">
										<a href="tel:${lab.address.phone}">${lab.address.phone}</a>
									</c:when>
									<c:otherwise>
										Not Available
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						
					</div>
					
					<div class="col-lg-6 view-col">
						<div class="row view-row">
							<div class="col-lg-12 view-col esm-uline">Address line</div>
							<div class="col-lg-12 view-col">
								<c:choose>
									<c:when test="${not empty lab.address.address}">
												${lab.address.address}
											</c:when>
									<c:otherwise>
												Not Available
											</c:otherwise>
								</c:choose>
							</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Locality</div>
							<div class="col-lg-6 view-col">${lab.address.locality}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">City</div>
							<div class="col-lg-6 view-col">${lab.address.city}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">State</div>
							<div class="col-lg-6 view-col">${lab.address.state}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Country</div>
							<div class="col-lg-6 view-col">${lab.address.country}-
								${lab.address.zipCode}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
