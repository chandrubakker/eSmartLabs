<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<html>
	<head>
	<title>eSmartLabs: SignUp</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2 admin-register-form">
							<h2 class="page-header">Admin SignUp</h2>
							<div class="alert alert-success alert-white successAlert hide">
								<p class="text-justify" style="font-size: 22px;">
									<strong class="adminName"></strong> successfully registered.
								</p>
								<p class="text-justify" style="font-size: 18px;">
									An confirmation link sent to email: <strong class="adminEmail"></strong>
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
							<form:form id="admin-reg-form" action="${register}" method="POST"
								modelAttribute="user">
								<form:hidden path="id" />
								<div class="row">
									<div class="col-lg-12">
										<div class="row">
	
											<div class="col-lg-6">
												<div id="form-group-name" class="form-group">
													<label class="control-label">Full Name</label>
													<form:input path="name" id="admin-name"
														class="form-control" placeholder="Full Name" />
													<div class="text-danger">
														<form:errors path="name" />
													</div>
												</div>
											</div>
	
											<div class="col-lg-6">
												<div id="form-group-email" class="form-group">
													<label class="control-label">Your Email</label>
													<form:input path="email" id="admin-email"
														class="form-control" placeholder="Email" />
													<div class="text-danger">
														<form:errors path="email" />
													</div>
												</div>
											</div>
	
										</div>
									</div>
									<div class="col-lg-12">
										<div class="row">
	
											<div class="col-lg-6">
												<div id="form-group-password" class="form-group">
													<label class="control-label">Password</label>
													<form:password path="password" class="form-control"
														placeholder="Password" />
													<div class="text-danger">
														<form:errors path="password" />
													</div>
												</div>
											</div>
	
											<div class="col-lg-6">
												<div id="form-group-confirmPassword" class="form-group">
													<label class="control-label">Confirm Password</label>
													<form:password path="confirmPassword"
														class="form-control" placeholder="Confirm password" />
													<div class="text-danger">
														<form:errors path="confirmPassword" />
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
							<validation:CRUD_NEW validateURL="/register.json"
								requestMethod="POST" busyMessage="creating admin account..."
								asynch="false" callBackOnSuccess="adminRegistrationSuccessPage"
								formId="admin-reg-form" noSubmit="1" />
						</div>
					</div>
				</div>
			</div>
		</section>
	
		<script type="text/javascript">
			function adminRegistrationSuccessPage() {
	
				var adminName = $("#admin-name").val() ? $("#admin-name").val() : "Your account";
				var adminEmail = $("#admin-email").val() ? $("#admin-email").val() : "Registered Email";
	
				$(".adminName").text(adminName);
				$(".adminEmail").text(adminEmail);
	
				$(".successAlert").removeClass("hide");
				var registerForm = document.getElementById("admin-reg-form");
				registerForm.reset();
				setTimeout(function() {
					/* $(".successAlert").hide(1000, function() {
	
					}); */
				}, 6000);
			}
		</script>
	</body>
</html>