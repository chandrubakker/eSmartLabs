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
					Company <small>Profile</small>
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
							<div class="col-lg-6 view-col">Company Name</div>
							<div class="col-lg-6 view-col">${company.name}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Estd. Date</div>
							<div class="col-lg-6 view-col">
								<c:choose>
									<c:when test="${not empty company.estdDate}">
										<fmt:formatDate value="${company.estdDate}"
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
									<c:when test="${not empty company.info}">
												${company.info}
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
									<c:when test="${not empty company.address.phone}">
										<a href="tel:${company.address.phone}">${company.address.phone}</a>
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
									<c:when test="${not empty company.address.address}">
												${company.address.address}
											</c:when>
									<c:otherwise>
												Not Available
											</c:otherwise>
								</c:choose>
							</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Locality</div>
							<div class="col-lg-6 view-col">${company.address.locality}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">City</div>
							<div class="col-lg-6 view-col">${company.address.city}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">State</div>
							<div class="col-lg-6 view-col">${company.address.state}</div>
						</div>
	
						<div class="row view-row">
							<div class="col-lg-6 view-col">Country</div>
							<div class="col-lg-6 view-col">${company.address.country}-
								${company.address.zipCode}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>