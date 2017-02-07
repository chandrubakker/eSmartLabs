<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Page Content -->
<html>
	<head>
		<title>eSmartLabs: Technician</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
			</div>
			<div class="row">
				<p class="col-lg-12 text-right">
					Last Visited On<strong> <fmt:formatDate
							value="${loggedInUser.lastLogin}"
							pattern="dd/MM/yyyy [ hh:mm:SS a ]" /></strong>
				</p>
			</div>
		</div>
	</body>
</html>