<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>
<html>
	<head>
		<title>eSmartLabs: Update Profile: ${user.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Update <small>User Account</small>
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
					<form:form role="form" action="${editUser}" method="POST"
						modelAttribute="user" id="update-user">
						<fieldset class="col-lg-6 col-lg-offset-3">
							<legend>change your account details</legend>
							<form:hidden path="id" />
		
							<div id="form-group-name" class="form-group">
								<label class="control-label">Name</label>
								<form:input path="name" id="user-name" placeholder="your name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
		
							<div id="form-group-email" class="form-group">
								<label class="control-label">Email</label>
								<form:input path="email" placeholder="your email"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="email" />
								</div>
							</div>
		
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm">
									<i class="fa fa-database"></i> Update
								</button>
								<button type="reset" class="btn btn-default btn-sm">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</fieldset>
					</form:form>
					<ajax:formPartialRefresh validateUrl="/user/update"
						formName="update-user" noSubmit="1"  callBackOnSuccess="userUpdateSuccess"/>
				</div>
			</div>
		</div>
		<div class="modal fade" id="updateUserModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/user/${user.id}/account"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function userUpdateSuccess() {
				var name = $("#user-name").val();
				$(".custom-modal-body")
						.text(
								name+" account updated.");
				$("#updateUserModal").modal('show');
			}
		</script>
	</body>
</html>	