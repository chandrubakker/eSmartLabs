<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<title>eSmartLabs: Lab Profile</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							Lab <small>Profile</small>
						</h1>
					</div>
				</div>
				<div class="row noPrint">
					<div class="col-lg-12">
						<a href="<c:url value="#"/>" class="btn btn-default btn-sm"> <i
							class="fa fa-hand-o-left"></i> Back
						</a>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<h3 class="page-header">Lab details</h3>
						<div class="row">
							<div class="col-lg-3">
								Lab Name
							</div>
							
							<div class="col-lg-9">
								${lab.name}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								Estd. Date
							</div>
							<div class="col-lg-9">
								<fmt:formatDate value="${lab.estdDate}" pattern="dd MMM, yyyy" />
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								About Lab
							</div>
							<div class="col-lg-9">
								${lab.info}
							</div>
						</div>
					</div>
					
					<div class="col-lg-6">
						<h3 class="page-header">Lab address</h3>
						
						<div class="row">
							<div class="col-lg-3">
								Address line
							</div>
							<div class="col-lg-9">
								${lab.address.address}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								Locality/Area
							</div>
							<div class="col-lg-9">
								${lab.address.locality}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								City
							</div>
							<div class="col-lg-9">
								${lab.address.city}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								State
							</div>
							<div class="col-lg-9">
								${lab.address.state}
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-3">
								Country
							</div>
							<div class="col-lg-9">
								${lab.address.country} - ${lab.address.zipCode}
							</div>
						</div>
						
					</div>
				</div>
				<!-- <div class="row">
					<div class="col-lg-12 bg-grey">
						<div class="clearfix location-wrap cushion-both">
			             	<div class="col-xs-12 clear-hboth">
				                <div class="h3 col-xs-12 col-sm-6 clear-left location">
				                  Bengaluru
				                </div>
				                <div class="h3 col-xs-12 col-sm-6 text-right text-blue address">
				                	<a href="http://maps.google.co.in/maps?q=Practo, RPS Green, 165/5, Krishna Raju Layout, JP Nagar 4th Phase, Bengaluru, Karnataka 560076">Get Directions</a>
				                </div>
			             	</div>
			             	<div class="col-xs-12 clear-left text">Practo, RPS Green, 165/5, Krishna Raju Layout, JP Nagar 4th Phase, Bengaluru, Karnataka 560076</div>
			            </div>
					</div>
				</div> -->
			</div>
		</div>
	</body>
</html>