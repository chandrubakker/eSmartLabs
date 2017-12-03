<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>
<html>
	<head>
		<title>eSmartLabs: Login</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="row">
					<div class="col-md-6 login-form-div">
						<h2 class="page-header">Login</h2>
						<c:if test="${! empty logout}">
							<div class="alert alert-success custom-alert text-center">${logout}</div>
						</c:if>
						<c:if test="${! empty sessionExp}">
							<div class="alert alert-success custom-alert text-center">${sessionExp}</div>
						</c:if>
						<div class="alert alert-info progressAlert hide">
							<p class="text-center">
								<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
							</p>
							<p class="text-center progressText">Processing your request...
							</p>
						</div>
						<c:url value="/processLogin" var="login" />
						<form:form id="login-form" action="${login}" method="post"
							modelAttribute="user">
							<div id="form-group-enabled" class="form-group">
								<form:hidden path="enabled" />
								<div class="text-danger text-center">
									<form:errors path="enabled" />
								</div>
							</div>
							<div id="form-group-email" class="form-group">
								<label class="control-label">Your email id</label>
								<form:input path="email" class="form-control"
									placeholder="registered email id" />
								<div class="text-danger">
									<form:errors path="email" />
								</div>
							</div>
							<div id="form-group-password" class="form-group">
								<label class="control-label">Password</label>
								<form:password path="password" class="form-control"
									placeholder="Password" />
								<div class="text-danger">
									<form:errors path="password" />
								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<button type="submit" class="btn btn-success submit_c">Sign
									In</button>
								<button type="reset" class="btn btn-default">Reset</button>
								<div class="custom-link">
									<a href="<c:url value="/password/forgot" />" class="app-link">Forgot
										Password</a>
								</div>
							</div>
						</form:form>
	
						<validation:CRUD_NEW validateURL="/login.json" requestMethod="POST"
							busyMessage="processing ..." asynch="false"
							formId="login-form" noSubmit="0" />
					</div>
					<div class="col-md-6 login-text-div">
					</div>
				</div>
			</div>
		</section>
	</body>
</html>