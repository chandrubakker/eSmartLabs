<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<!-- Add School Section -->
<html>
<head>
<title>eSmartLabs: Contact Us</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script> -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<section>
		<div class="container">
			<div class="row">

				<div class="col-md-6 col-md-offset-3">
					<h2 class="page-header">
						Any queries? <small>Feel free to say hello.</small>
					</h2>
					<div class="alert alert-success successAlert hide">Your
						message successfully sent. Our executive will contact you soon.</div>
					<div class="alert alert-info progressAlert hide">
						<p class="text-center">
							<i class="fa fa-cog fa-spin fa-3x fa-fw"></i>
						</p>
						<p class="text-center progressText">Processing your request...
						</p>
					</div>
					<c:url value="#" var="contact_us" />
					<form:form id="contact-us-form" action="${contact_us}"
						method="POST" modelAttribute="contact">
						<form:hidden path="id" />
						<div id="form-group-name" class="form-group">
							<label class="control-label">Your Name</label>
							<form:input path="name" id="senderName" class="form-control"
								placeholder="enter your name" />
							<div class="text-danger">
								<form:errors path="name" />
							</div>
						</div>
						<div id="form-group-email" class="form-group">
							<label class="control-label">Your Email</label>
							<form:input path="email" class="form-control"
								placeholder="enter your email" />
							<div class="text-danger">
								<form:errors path="email" />
							</div>
						</div>
						<div id="form-group-phone" class="form-group">
							<label class="control-label">Contact Number</label>
							<form:input path="phone" class="form-control"
								placeholder="Enter your contact number (Optional)" />
							<div class="text-danger">
								<form:errors path="phone" />
							</div>
						</div>
						<div id="form-group-query" class="form-group">
							<label class="control-label">Your Message</label>
							<form:textarea path="query" class="form-control"
								placeholder="Your message here" />
							<div class="text-danger">
								<form:errors path="query" />
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-success">Send</button>
							<button type="reset" class="btn btn-default">Reset</button>
						</div>
					</form:form>
					<validation:CRUD_NEW validateURL="/contact_us" requestMethod="POST"
						busyMessage="sending your message..." asynch="false"
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