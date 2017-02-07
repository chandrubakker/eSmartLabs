<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>

<html>
	<head>
		<title>eSmartLabs: Create New Patient</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Create <small>New Patient</small>
						</h1>
					</div>
				</div>
				
				<div class="row">
					<c:url value="#" var="createPatient" />
					<form:form role="form" action="${createPatient}" method="POST"
						modelAttribute="patient" id="create-patient">
						<div class="col-lg-6">
							<legend>Patient Details</legend>
							
							<form:hidden path="id" />
							
							<div id="form-group-regNum" class="form-group">
								<label class="control-label">Registration Number</label>
								<form:input path="regNum" placeholder="Registration Number"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="regNum" />
								</div>
							</div>
							
							<div id="form-group-name" class="form-group">
								<label class="control-label">Patient Name</label>
								<form:input path="name" placeholder="Patient name"
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
							
							<div id="form-group-age" class="form-group">
								<label class="control-label">Age</label>
								<form:input path="age" placeholder="Age"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="age" />
								</div>
							</div>
							
							<div id="form-group-dateOfAdmission" class="form-group">
								<label class="control-label">Admission Date</label>
								<form:input path="dateOfAdmission" placeholder="DD/MM/YYYY" id="admn-date"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="dateOfAdmission" />
								</div>
							</div>
						</div>
						
						<div class="col-lg-6">
							<legend>Medical Details</legend>
							
							<div id="form-group-selfInterest" style="padding-bottom: 10px;">
								<label class="control-label">Self Interest</label>
								
								<label class="radio-inline">
								 	<form:radiobutton path="selfInterest" checked="true" value="No" />No
								</label>
								
								<label class="radio-inline">
								 	<form:radiobutton path="selfInterest" value="Yes" />Yes
								</label>
								
								<div class="text-danger">
									<form:errors path="selfInterest" />
								</div>
							</div>
							
							<div id="form-group-referredDoctor-id" class="form-group">
								<label class="control-label">Select Referred Doctor</label>
								<form:select path="referredDoctor.id" cssClass="form-control">
									<form:option value="-1">Choose Doctor</form:option>
									<c:forEach items="${doctors}" var="doctor">
										<form:option value="${doctor.id}">${doctor.name}</form:option>
									</c:forEach>
								</form:select>
								<div class="text-danger">
									<form:errors path="referredDoctor.id" />
								</div>
							</div>
							
							<div id="form-group-tests" class="form-group">
								<label class="control-label">Select Tests</label>
								<select name="testsToDo" class="form-control chosen-select" multiple="multiple" data-placeholder="Choose tests">
									<c:forEach items="${tests}" var="test">
										<option value="${test.id}">${test.name}</option>
									</c:forEach>
								</select>
								<div class="text-danger">
									<form:errors path="tests" />
								</div>
							</div>
							
							<div id="form-group-shortAddress" class="form-group">
								<label class="control-label">Short Address</label>
								<form:textarea path="shortAddress" rows="4" placeholder="short address here."
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="shortAddress" />
								</div>
							</div>
							
							<div id="form-group-phone" class="form-group">
								<label class="control-label">Phone Number</label>
								<form:input path="phone" placeholder="contact number"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="phone" />
								</div>
							</div>
						</div>
						
						<div class="col-lg-12">
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
					<validation:CRUD validateURL="/patient/create" requestMethod="POST" busyMessage="creating patient..." asynch="false" callBackOnSuccess="patientCreationSuccess" formId="create-patient" noSubmit="1" />
				</div>
			</div>
		</div>
		<div class="modal fade" id="patient-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/patient/create"/>" class="btn btn-info"> Create New</a>
						<a href="<c:url value="/patients"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			
			function patientCreationSuccess() {
				
				var name = $("#name").val();
				$(".custom-modal-body")
						.text(
								name+" created successfully.");
				$("#patient-create-modal").modal('show');
			}
			
			$(document).ready(function() {
				$('#admn-date').datepicker({
					format : "dd/mm/yyyy"
				});
			});
		</script>
	</body>
</html>