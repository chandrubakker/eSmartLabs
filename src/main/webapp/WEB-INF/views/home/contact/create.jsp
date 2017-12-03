<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<html>
	<head>
		<title>eSmartLabs: Contact Us</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-lg-offset-3 contact-form">
						<h2 class="page-header">
							Any queries?
						</h2>
						<div class="alert alert-success successAlert hide">Your
							message successfully sent. Our executive will contact you soon.</div>
						
						<div class="alert alert-info progressAlert hide">
							<p class="text-center">
								<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
							</p>
							<p class="text-center progressText">Processing your
								request...</p>
						</div>
						<br>&nbsp;
						<c:url value="#" var="contact_us" />
						<form:form id="contact-us-form" action="${contact_us}"
							method="POST" modelAttribute="contact">
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
	
										<div class="col-lg-6">
											<form:hidden path="id" />
											<div id="form-group-email" class="form-group">
												<label class="control-label">Your Email</label>
												<form:input path="email" class="form-control"
													placeholder="enter your email" />
												<div class="text-danger">
													<form:errors path="email" />
												</div>
											</div>
										</div>
	
										<div class="col-lg-6">
											<div id="form-group-phone" class="form-group">
												<label class="control-label">Contact Number</label>
												<form:input path="phone" class="form-control"
													placeholder="mobile/phone" />
												<div class="text-danger">
													<form:errors path="phone" />
												</div>
											</div>
										</div>
									</div>
	
									<div class="row">
										<div class="col-lg-12">
											<div id="form-group-query" class="form-group">
												<label class="control-label">Your Message</label>
												<form:textarea path="query" class="form-control"
													placeholder="what is your query/message" />
												<div class="text-danger">
													<form:errors path="query" />
												</div>
											</div>
										</div>
									</div>
	
									<div class="row">
										<div class="col-lg-12">
											<div class="form-group">
												<button type="submit" class="btn btn-success">Send</button>
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form:form>
						<validation:CRUD_NEW validateURL="/contact_us" requestMethod="POST"
							busyMessage="processing..." asynch="false"
							callBackOnSuccess="messageSendingSuccessPage"
							formId="contact-us-form" noSubmit="1" />
					</div>
				</div>
			</div>
		</section>
	
		<script type="text/javascript">
			function messageSendingSuccessPage() {
	
				$(".successAlert").removeClass("hide");
				var contactForm = document.getElementById("contact-us-form");
				contactForm.reset();
				setTimeout(function() {
					$(".successAlert").hide(1000, function() {
	
					});
				}, 4000);
			}
		</script>
	</body>
</html>