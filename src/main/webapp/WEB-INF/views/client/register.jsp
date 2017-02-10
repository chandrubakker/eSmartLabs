<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>

<!-- Add School Section -->
<html>
	<head>
		<title>eSmartLabs: Register Your Lab</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="row">
					<div
						class="col-lg-6 col-lg-offset-3 col-md-6 col-sm-8 col-xs-12 col-sm-offset-2 col-md-offset-3">
						<h1 class="page-header">Register Your Lab</h1>
						<div class="panel panel-default custom-panel">
							<div class="panel-heading text-center">
								<strong>enter details</strong>
							</div>
							<div class="panel-body">
								<c:url value="#" var="register" />
								<form:form id="client-reg-form" action="${register}" method="POST"
									modelAttribute="client">
									<form:hidden path="id"/>
									<form:hidden path="lab.id"/>
									<form:hidden path="user.id"/>
									<div id="form-group-lab-name" class="form-group">
										<div>
											<form:input path="lab.name" id="lab-name" class="form-control"
												placeholder="Laboratory Name" />
											<div class="text-danger">
												<form:errors path="lab.name" />
											</div>
										</div>
									</div>
									<div id="form-group-user-email" class="form-group">
										<div>
											<form:input path="user.email" id="client-email" class="form-control"
												placeholder="Email" />
											<div class="text-danger">
												<form:errors path="user.email" />
											</div>
										</div>
									</div>
									<div id="form-group-user-password" class="form-group">
										<div>
											<form:password path="user.password" class="form-control"
												placeholder="Password" />
											<div class="text-danger">
												<form:errors path="user.password" />
											</div>
										</div>
									</div>
									<div id="form-group-user-confirmPassword" class="form-group">
										<div>
											<form:password path="user.confirmPassword" class="form-control"
												placeholder="Confirm password" />
											<div class="text-danger">
												<form:errors path="user.confirmPassword" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-success">Sign Up</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</form:form>
								<validation:CRUD validateURL="/client/register" requestMethod="POST" busyMessage="registering your lab..." asynch="false" callBackOnSuccess="clientRegistrationSuccessPage" formId="client-reg-form" noSubmit="1" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<div class="modal fade" id="client-reg-modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body">
						<p class="custom-modal-textOne"></p>
						<p class="custom-modal-textTwo"></p>
					</div>
					<div class="modal-footer">
						<a href="<c:url value="/login"/>" class="btn btn-default">Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		
			function clientRegistrationSuccessPage() {
				var clientName = $("#lab-name").val();
				var clientEmail = $("#client-email").val();
				$(".custom-modal-textOne").text(
						clientName + ' successfully segistered.')
						.addClass("text-center");
				$(".custom-modal-textTwo").text('An confirmation link sent to email: ' + 
						clientEmail + ', please check and confirm.')
						.addClass("text-center");
				$("#client-reg-modal").modal('show');
			}
			
		</script>
	</body>
</html>