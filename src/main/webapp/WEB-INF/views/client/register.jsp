<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<html>
	<head>
	<title>eSmartLabs: Register Your Lab</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2 register-form">
							<h2 class="page-header">Register Your Lab</h2>
							<div class="alert alert-success alert-white successAlert hide">
								<p class="text-justify" style="font-size: 22px;">
									<strong class="labName"></strong> successfully registered.
								</p>
								<p class="text-justify" style="font-size: 18px;">
									An confirmation link sent to email: <strong class="clientEmail"></strong>
									please check and confirm.
								</p>
								<p class="text-justify" style="font-size: 16px;">
									Note: In case you did not received the mail, please contact us
									via <a href="<c:url value="/contact-us" />">Contact</a> page
								</p>
							</div>
							<div class="alert alert-info progressAlert hide">
								<p class="text-center">
									<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
								</p>
								<p class="text-center progressText">Processing your
									request...</p>
							</div>
							<br>&nbsp;
							<c:url value="#" var="register" />
							<form:form id="client-reg-form" action="${register}" method="POST"
								modelAttribute="client">
								<form:hidden path="id" />
								<form:hidden path="lab.id" />
								<form:hidden path="user.id" />
								<div class="row">
									<div class="col-lg-12">
										<div class="row">
	
											<div class="col-lg-6">
												<div id="form-group-lab-name" class="form-group">
													<label class="control-label">Laboratory Name</label>
													<form:input path="lab.name" id="lab-name"
														class="form-control" placeholder="Laboratory Name" />
													<div class="text-danger">
														<form:errors path="lab.name" />
													</div>
												</div>
											</div>
	
											<div class="col-lg-6">
												<div id="form-group-user-email" class="form-group">
													<label class="control-label">Your Email</label>
													<form:input path="user.email" id="client-email"
														class="form-control" placeholder="Email" />
													<div class="text-danger">
														<form:errors path="user.email" />
													</div>
												</div>
											</div>
	
										</div>
									</div>
									<div class="col-lg-12">
										<div class="row">
	
											<div class="col-lg-6">
												<div id="form-group-user-password" class="form-group">
													<label class="control-label">Password</label>
													<form:password path="user.password" class="form-control"
														placeholder="Password" />
													<div class="text-danger">
														<form:errors path="user.password" />
													</div>
												</div>
											</div>
	
											<div class="col-lg-6">
												<div id="form-group-user-confirmPassword" class="form-group">
													<label class="control-label">Confirm Password</label>
													<form:password path="user.confirmPassword"
														class="form-control" placeholder="Confirm password" />
													<div class="text-danger">
														<form:errors path="user.confirmPassword" />
													</div>
												</div>
											</div>
										</div>
									</div>
	
									<div class="col-lg-12">
										<div class="form-group">
											<button type="submit" class="btn btn-success">Sign Up</button>
											<button type="reset" class="btn btn-default">Reset</button>
										</div>
									</div>
								</div>
							</form:form>
							<validation:CRUD_NEW validateURL="/client/register"
								requestMethod="POST" busyMessage="registering your lab..."
								asynch="false" callBackOnSuccess="clientRegistrationSuccessPage"
								formId="client-reg-form" noSubmit="1" />
						</div>
					</div>
				</div>
			</div>
		</section>
	
		<script type="text/javascript">
			function clientRegistrationSuccessPage() {
	
				var labName = $("#lab-name").val() ? $("#lab-name").val() : "Your Lab";
				var clientEmail = $("#client-email").val() ? $("#client-email").val() : "Registered Email";
	
				$(".labName").text(labName);
				$(".clientEmail").text(clientEmail);
	
				$(".successAlert").removeClass("hide");
				var registerForm = document.getElementById("client-reg-form");
				registerForm.reset();
				setTimeout(function() {
					/* $(".successAlert").hide(1000, function() {
	
					}); */
				}, 6000);
			}
		</script>
	</body>
</html>