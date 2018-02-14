<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>
<html>
	<head>
		<title>eSmartLabs: Update Company</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<h1 class="page-title-one text-center">
					Update <small>Company</small>
				</h1>
			</div>
		</div>
	
		<div class="row esm-back-row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<a href="#" class="btn btn-default btn-sm esm-button esm-back-btn">
					<i class="fa fa-hand-o-left"></i> Back
				</a>
			</div>
		</div>
	
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<c:url value="#" var="updateCompany" />
				<form:form role="form" action="${updateCompany}" method="POST"
					modelAttribute="company" id="update-company">
					<form:hidden path="id" />
					<form:hidden path="user.id" />
					<div class="row">
						<div class="col-lg-8">
							<div id="form-group-name" class="form-group">
								<label class="control-label">Company Name</label>
								<form:input path="name" placeholder="registered name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
						</div>
	
						<div class="col-lg-4">
							<div id="form-group-estdDate" class="form-group">
								<label class="control-label">Established On</label>
								<form:input path="estdDate" id="company-estd"
									placeholder="MM/YYYY" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="estdDate" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="col-lg-4">
							<div id="form-group-address-phone" class="form-group">
								<label class="control-label">Phone Number</label>
								<form:input path="address.phone" placeholder="contact number"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.phone" />
								</div>
							</div>
						</div>
						<div class="col-lg-8">
							<div id="form-group-info" class="form-group">
								<label class="control-label">About the company</label>
								<form:textarea path="info" rows="1"
									placeholder="about the company" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="info" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="col-lg-8">
							<form:hidden path="address.id" />
							<div id="form-group-address-address" class="form-group">
								<label class="control-label">Address Line</label>
								<form:textarea path="address.address" rows="1"
									placeholder="whole address here" cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.address" />
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div id="form-group-address-locality" class="form-group">
								<label class="control-label">Locality</label>
								<form:input path="address.locality" placeholder="locality name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.locality" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="col-lg-6">
							<div id="form-group-address-city" class="form-group">
								<label class="control-label">City</label>
								<form:input path="address.city" placeholder="city name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.city" />
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div id="form-group-address-state" class="form-group">
								<label class="control-label">State</label>
								<form:input path="address.state" placeholder="state name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.state" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="col-lg-6">
							<div id="form-group-address-country" class="form-group">
								<label class="control-label">Country</label>
								<form:input path="address.country" placeholder="country name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.country" />
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div id="form-group-address-zipCode" class="form-group">
								<label class="control-label">PIN Code</label>
								<form:input path="address.zipCode" placeholder="PIN code"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="address.zipCode" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<button type="submit"
									class="btn btn-success btn-sm esm-button esm-sub-btn">
									<i class="fa fa-database"></i> Update
								</button>
								<button type="reset" class="btn btn-default btn-sm esm-button">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</div>
					</div>
				</form:form>
				<validation:CRUD validateURL="/company/update" requestMethod="POST"
					busyMessage="Processing your request..." asynch="false"
					callBackOnSuccess="companyUpdateSuccess" formId="update-company"
					noSubmit="1" />
			</div>
		</div>
	
		<div class="modal fade" id="company-update-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/company/profile"/>" class="btn btn-success">
							Close</a>
					</div>
				</div>
			</div>
		</div>
	
		<script type="text/javascript">
			function companyUpdateSuccess() {
				var name = $("#name").val();
				$(".custom-modal-body").text(
						name + " company updated successfully.");
				$("#company-update-modal").modal('show');
			}
	
			$(document).ready(function() {
				$('#company-estd').datepicker({
					format : "mm/yyyy",
					viewMode : "months",
					minViewMode : "months"
				});
			});
		</script>
	</body>
</html>