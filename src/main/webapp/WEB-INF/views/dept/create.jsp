<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>
<html>
	<head>
		<title>eSmartLabs: Create New Dept.</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Create <small>New Department</small>
						</h1>
					</div>
				</div>
				
				<div class="row">
					<c:url value="#" var="createDept" />
					<form:form role="form" action="${createDept}" method="POST"
						modelAttribute="department" id="create-dept">
						<fieldset class="col-lg-6 col-lg-offset-3">
							<legend>Department Details</legend>
							<form:hidden path="id" />
							<form:hidden path="lab.id" value="${loggedInUser.client.lab.id}" />
							<div id="form-group-name" class="form-group">
								<label class="control-label">Department Name</label>
								<form:input path="name" placeholder="department name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
							
							<div id="form-group-code" class="form-group">
								<label class="control-label">Department Code</label>
								<form:input path="code" placeholder="department code"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="code" />
								</div>
							</div>
		
							<div id="form-group-info" class="form-group">
								<label class="control-label">About the department</label>
								<form:textarea path="info" rows="4" placeholder="about the department"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="info" />
								</div>
							</div>
							
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm">
									<i class="fa fa-database"></i> Create
								</button>
								<button type="reset" class="btn btn-default btn-sm">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
							
						</fieldset>
					</form:form>
					<validation:CRUD validateURL="/department/update" requestMethod="POST" busyMessage="creating department..." asynch="false" callBackOnSuccess="deptCreationSuccess" formId="create-dept" noSubmit="1" />
				</div>
			</div>
		</div>
		<div class="modal fade" id="lab-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/department/create"/>" class="btn btn-info"> Add New</a>
						<a href="<c:url value="/departments"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function deptCreationSuccess() {
				var name = $("#name").val();
				$(".custom-modal-body")
						.text(
								name+" created successfully.");
				$("#lab-create-modal").modal('show');
			}
		</script>
	</body>
</html>