<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Add School Section -->
<html>
	<head>
		<title>eSmartLabs: Register</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="row">
					<div
						class="col-lg-6 col-lg-offset-3 col-md-6 col-sm-8 col-xs-12 col-sm-offset-2 col-md-offset-3">
						<h1 class="page-header">Client registration</h1>
						<div class="panel panel-default custom-panel">
							<div class="panel-heading text-center">
								<strong>enter details</strong>
							</div>
							<div class="panel-body">
								<c:url value="#" var="register" />
								<form:form role="form" id="client-reg" action="" method="post"
									modelAttribute="user">
									<div id="form-group-name" class="form-group">
										<div>
											<form:input path="name" class="form-control"
												placeholder="Full Name" />
											<div class="text-danger">
												<form:errors path="name" />
											</div>
										</div>
									</div>
									<div id="form-group-email" class="form-group">
										<div>
											<form:input path="email" class="form-control"
												placeholder="Email" />
											<div class="text-danger">
												<form:errors path="email" />
											</div>
										</div>
									</div>
									<div id="form-group-password" class="form-group">
										<div>
											<form:password path="password" class="form-control"
												placeholder="Password" />
											<div class="text-danger">
												<form:errors path="password" />
											</div>
										</div>
									</div>
									<div id="form-group-confirmPassword" class="form-group">
										<div>
											<form:password path="confirmPassword" class="form-control"
												placeholder="Confirm password" />
											<div class="text-danger">
												<form:errors path="confirmPassword" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<button type="button" onclick="registerClientSide();"
											class="btn btn-success">Sign Up</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</div>
								</form:form>
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
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-name"></div>
					<div class="modal-footer">
						<a href="<c:url value="/login"/>" class="btn btn-default">Close</a>
						<%-- <a href="<c:url value="/admin/faculty/add"/>"
							class="btn btn-primary">Add More</a> --%>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function registerServerSide() {
		
				var $form = $("#client-reg");
				var postData = $form.serializeArray();
				var formURL = "http://sorcererpaws.com/client/register?type=ePathLab";
		
				$.ajax({
					url : formURL,
					type : 'POST',
					data : postData,
					headers : {
						"Access-Control-Allow-Origin" : "*"
					},
					beforeSend : function() {
						$form.find('.form-group').removeClass('has-error');
						$form.find('.form-group').removeClass('alert alert-danger');
						$form.find('.text-danger').empty();
					},
					success : function(response, textStatus, jqXHR) {
						if (response.status == 'FAIL') {
							for (var i = 0; i < response.errorMessageList.length; i++) {
								var item = response.errorMessageList[i];
		
								var fieldId = item.fieldName;
								console.log(fieldId);
								fieldId = fieldId.replace(/\./g, '-');
		
								fieldId = fieldId.replace(/\[/g, '-');
								fieldId = fieldId.replace(/\]/g, '-');
								console.log(fieldId);
								var $controlGroup = $("#form-group-" + fieldId);
		
								//console.log(fieldId);
								$controlGroup.addClass('has-error');
								$controlGroup.find('.text-danger').html(item.message);
		
							}
		
						} else {
							/* $form.unbind('submit'); */
							registerClientSide();
							/* return true; */
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
					}
				});
		
			}
		
			function registerClientSide() {
		
				var $form = $("#client-reg");
				var postData = $form.serializeArray();
				var rootPath = "${pageContext.request.contextPath}";
				var formURL = rootPath + "/register.json";
		
				$.ajax({
					url : formURL,
					type : 'POST',
					data : postData,
					success : function(response, textStatus, jqXHR) {
						if (response.status == 'FAIL') {
							for (var i = 0; i < response.errorMessageList.length; i++) {
								var item = response.errorMessageList[i];
		
								var fieldId = item.fieldName;
								console.log(fieldId);
								fieldId = fieldId.replace(/\./g, '-');
		
								fieldId = fieldId.replace(/\[/g, '-');
								fieldId = fieldId.replace(/\]/g, '-');
								console.log(fieldId);
								var $controlGroup = $("#form-group-" + fieldId);
		
								//console.log(fieldId);
								$controlGroup.addClass('has-error');
								$controlGroup.find('.text-danger').html(item.message);
		
							}
		
						} else {
							/* $form.unbind('submit'); */
							registrationSuccessPage();
							return true;
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(errorThrown);
					}
				});
			}
		
			function registrationSuccessPage() {
				var name = $("#name").val();
				$(".custom-name").text(
						name + ' Successfully Registered. Confirm your email.')
						.addClass("text-center");
				$("#myModal").modal('show');
			}
		</script>
	</body>
</html>