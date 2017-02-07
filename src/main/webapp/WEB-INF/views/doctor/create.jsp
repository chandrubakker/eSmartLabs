<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>

<html>
	<head>
		<title>eSmartLabs: Create New Doctor</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Create <small>New Doctor</small>
						</h1>
					</div>
				</div>
				
				<div class="row">
					<c:url value="#" var="createDoctor" />
					<form:form role="form" action="${createDoctor}" method="POST"
						modelAttribute="doctor" id="create-doctor">
						<div class="col-lg-6 col-lg-offset-3">
							<legend>Doctor Details</legend>
							<form:hidden path="id" />
							<div id="form-group-clinic-id" class="form-group">
								<label class="control-label">Select Hospital</label>
								<form:select path="clinic.id" cssClass="form-control">
									<form:option value="-1">Choose Hospital</form:option>
									<c:forEach items="${clinics}" var="cliniq">
										<form:option value="${cliniq.id}">${cliniq.name}</form:option>
									</c:forEach>
								</form:select>
								<div class="text-danger">
									<form:errors path="clinic.id" />
								</div>
							</div>
							
							<div id="form-group-name" class="form-group">
								<label class="control-label">Doctor Name</label>
								<form:input path="name" placeholder="doctor name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
							
							<div id="form-group-gender" style="padding-bottom: 10px;">
								<label class="control-label">Gender</label>
								
								<label class="radio-inline">
								 	<form:radiobutton path="gender" value="Male" />Male
								</label>
								
								<label class="radio-inline">
								 	<form:radiobutton path="gender" value="Female" />Female
								</label>
								
								<label class="radio-inline">
								 	<form:radiobutton path="gender" value="Others" />Others
								</label>
								<div class="text-danger">
									<form:errors path="gender" />
								</div>
							</div>
							<div id="form-group-specialization" class="form-group">
								<label class="control-label">Doctor's Specialization</label>
								<form:input path="specialization" placeholder="doctor's specialization"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="specialization" />
								</div>
							</div>
							
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm">
									<i class="fa fa-save"></i> Create
								</button>
								<button type="reset" class="btn btn-default btn-sm">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</div>
					</form:form>
					<validation:CRUD validateURL="/doctor/update" requestMethod="POST" busyMessage="creating doctor..." asynch="false" callBackOnSuccess="doctorCreationSuccess" formId="create-doctor" noSubmit="1" />
				</div>
			</div>
		</div>
		<div class="modal fade" id="doctor-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/doctor/create"/>" class="btn btn-info"> Add New</a>
						<a href="<c:url value="/doctors"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function doctorCreationSuccess() {
				var name = $("#name").val();
				$(".custom-modal-body")
						.text(
								name+" created successfully.");
				$("#doctor-create-modal").modal('show');
			}
		</script>
	</body>
</html>