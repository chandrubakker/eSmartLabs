<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>eSmartLabs: Create New Dept.</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 title-col">
				<h1 class="page-title-one text-center">
					Create <small>New Department</small>
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
				<c:url value="#" var="createDept" />
				<form:form role="form" action="${createDept}" method="POST" modelAttribute="department" id="create-dept">
					<form:hidden path="id" />
					<form:hidden path="lab.id" value="${loggedInUser.client.lab.id}" />
					
					<div class="row">
						<div class="col-lg-6">
							<div id="form-group-name" class="form-group">
								<label class="control-label">Department Name</label>
								<form:input path="name" placeholder="department name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div id="form-group-code" class="form-group">
								<label class="control-label">Department Code</label>
								<form:input path="code" placeholder="department code"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="code" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div id="form-group-info" class="form-group">
								<label class="control-label">About the department</label>
								<form:textarea path="info" rows="2" placeholder="about the department"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="info" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm esm-button esm-sub-btn">
									<i class="fa fa-save"></i> Create
								</button>
								<button type="reset" class="btn btn-default btn-sm esm-button">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</div>
					</div>		
					
				</form:form>
				<validation:CRUD validateURL="/department/update" requestMethod="POST" busyMessage="creating department..." asynch="false" callBackOnSuccess="deptCreationSuccess" formId="create-dept" noSubmit="1" />
			</div>
		</div>
		
		<div class="modal fade" id="dept-create-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-keyboard="false" data-backdrop="static">
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
				$(".custom-modal-body").text(name + " - Created Successfully.");
				$("#dept-create-modal").modal('show');
			}
		</script>
	</body>
</html>	