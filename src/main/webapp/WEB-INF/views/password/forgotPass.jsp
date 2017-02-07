<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>

<html>
	<head>
		<title>eSmartLabs: Generate Reset Password Link</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div
					class="col-lg-6 col-lg-offset-3 col-md-6 col-sm-8 col-xs-12 col-sm-offset-2 col-md-offset-3">
					<h1 class="page-header">Forgot password</h1>
					<div class="panel panel-default custom-panel">
						<div class="panel-heading">
							<strong class="text-center">request password reset link</strong>
						</div>
						<div class="panel-body">
							<c:url value="#" var="login" />
							<form:form id="forgotPass-form" action="${login}" method="post"
								modelAttribute="user">
		
								<div id="form-group-active" class="form-group">
									<div class="text-danger text-center">
										<form:errors path="enabled" />
									</div>
								</div>
		
								<div id="form-group-email">
									<div class="form-group">
										<form:input path="email" id="fEmail" class="form-control"
											placeholder="Email" />
		
										<div class="text-danger">
											<form:errors path="email" />
										</div>
									</div>
								</div>
		
								<div class="form-group">
									<button type="submit" class="btn btn-success submit_c">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</form:form>
							<ajax:formPartialRefresh validateUrl="/password/forgot.json"
								formName="forgotPass-form" noSubmit="1"
								callBackOnSuccess="sendSuccessPage" />
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body">
						<p class="custom-name"></p>
						<p>Please check the mail for more details.</p>
					</div>
					<div class="modal-footer">
						<a href="<c:url value="/"/>" class="btn btn-default">Home</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function sendSuccessPage() {
				var email = $("#fEmail").val();
				$(".custom-name").text(
						"Password reset link successfully sent to " + email);
				$("#myModal").modal('show');
			}
		</script>
	</body>
</html>