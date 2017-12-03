<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>eSmartLabs: ${department.name} - Department</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row noPrint">
					<div class="col-lg-12">
						<h1 class="page-header">
							${department.code} <small>${department.name}</small>
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
					
						<h3 class="page-header">Overview</h3>
						<div class="row">
							<div class="col-lg-4">
								Department Name
							</div>
							<div class="col-lg-8">
								${department.name}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								Department Code
							</div>
							<div class="col-lg-8">
								${department.code}
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-4">
								About Department
							</div>
							<div class="col-lg-8">
								${department.info}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>