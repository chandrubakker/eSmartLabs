<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>eSmartLabs: Lab Registration Success</title>
	</head>
	<body>
		<div class="modal fade" id="labConfirmModal" tabindex="-1"
			aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="labConfirmModal">Successful Registration</h4>
					</div>
					<div class="modal-body custom-name">
						<div class="alert alert-success text-center">
							<h1>Hurray...<small>Your lab registration successfully confirmed. You can login now.</small></h1>
						</div>
					</div>
					<div class="modal-footer">
						<a href="<c:url value="/" />" class="btn btn-default">Go To Home</a>
						<a href="<c:url value="/login" />" class="btn btn-info">Login</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(window).load(function() {
				$('#labConfirmModal').modal('show');
			});
		</script>
	</body>
</html>