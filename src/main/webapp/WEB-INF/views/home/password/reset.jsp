<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>

<section style="margin-top: 100px;" class="login-back">
	<div class="container">
		<div class="row ">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class=""><center>Reset Password</center></strong>
					</div>
					<div class="panel-body">
						<c:url value="#" var="login" />
						<form:form cssClass="form-horizontal" id="resetPass-form"
							action="${login}" method="post" modelAttribute="user">
							
							<form:hidden path="id"/>
							
							<div id="form-group-newPassword" class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<form:password path="newPassword" class="form-control"
										id="inputEmail3" placeholder="New Password" />
								</div>
								<div class="col-lg-8 col-lg-offset-2 text-danger">
									<form:errors path="newPassword" />
								</div>
							</div>
							
							<div id="form-group-confirmPassword" class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<form:password path="confirmPassword" class="form-control"
										id="inputEmail3" placeholder="Confirm password" />
								</div>
								<div class="col-lg-8 col-lg-offset-2 text-danger">
									<form:errors path="confirmPassword" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<button type="submit" class="btn btn-success">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</div>
						</form:form>
						
						<ajax:formPartialRefresh validateUrl="/password/reset.json"
							formName="resetPass-form" noSubmit="1" callBackOnSuccess="resetSuccessPage" />
					</div>
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
			<div class="modal-body custom-name">
			</div>
			<div class="modal-footer">
				<a href="<c:url value="/login"/>"
					class="btn btn-default">Login</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function resetSuccessPage() {
		$(".custom-name").text("Your account password successfully reset");
		$("#myModal").modal('show');
	}
</script>