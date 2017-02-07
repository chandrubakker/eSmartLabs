<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>
<html>
	<head>
		<title>eSmartLabs: Change Password</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Account Setting <small>Change Password</small>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<a href="<c:url value="/user/${user.id}/account"/>"
							class="btn btn-default btn-sm"> <i class="fa fa-hand-o-left"></i>
							Back
						</a>
					</div>
				</div>
				<br>
				<div class="row">
					<c:url value="#" var="editUser" />
					<form:form id="change-pass" action="${editUser}" method="POST"
						modelAttribute="user">
						<form:hidden path="id" />
						<form:hidden path="name" />
						<fieldset class="col-lg-6 col-lg-offset-3">
							<legend> create a strong password </legend>
							<div id="form-group-password" class="form-group">
								<label class="control-label">Old Password</label>
								<form:password path="password" placeholder="old password" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="password" />
								</div>
							</div>
		
							<div id="form-group-newPassword" class="form-group">
								<label class="control-label">New Password</label>
								<form:password path="newPassword" placeholder="new password" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="newPassword" />
								</div>
							</div>
		
							<div id="form-group-confirmPassword" class="form-group">
								<label class="control-label">Confirm New Password</label>
								<form:password path="confirmPassword" placeholder="new password again" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="confirmPassword" />
								</div>
							</div>
		
							<div class=" form-group">
								<button type="submit" class="btn btn-success btn-sm">
									<i class=" fa fa-database "></i> Change
								</button>
								<button type="reset" class="btn btn-info btn-sm">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</fieldset>
					</form:form>
					<ajax:formPartialRefresh
						validateUrl="/user/password"
						formName="change-pass" noSubmit="1"
						callBackOnSuccess="passChangeSuccess" />
				</div>
			</div>
		</div>

		<div class="modal fade" id="passChangedModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/logout"/>" class="btn btn-success">Login
							Again</a>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			function passChangeSuccess() {
				$(".custom-modal-body")
						.text(
								'Your password successfully changed. Please Login Again To Continue.');
				$("#passChangedModal").modal('show');
			}
		</script>
	</body>
</html>