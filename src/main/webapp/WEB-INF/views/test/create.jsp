<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<html>
	<head>
		<title>eSmartLabs: Create New Test</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Create <small>New Test</small>
						</h1>
					</div>
				</div>
		
				<div class="row">
					<c:url value="#" var="createTest" />
					<form:form role="form" action="${createTest}" method="POST"
						modelAttribute="test" id="create-test">
						<div class="col-lg-6">
							<legend>Test Details</legend>
							<form:hidden path="id" />
							<div id="form-group-department-id" class="form-group">
								<label class="control-label">Select Department</label>
								<form:select path="department.id" cssClass="form-control">
									<form:option value="-1">Choose Department</form:option>
									<c:forEach items="${departments}" var="dept">
										<form:option value="${dept.id}">${dept.name}</form:option>
									</c:forEach>
								</form:select>
								<div class="text-danger">
									<form:errors path="department.id" />
								</div>
							</div>
							<div id="form-group-name" class="form-group">
								<label class="control-label">Test Name</label>
								<form:input path="name" placeholder="test name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
		
							<div id="form-group-code" class="form-group">
								<label class="control-label">Test Code</label>
								<form:input path="code" placeholder="test code"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="code" />
								</div>
							</div>
		
							<div id="form-group-price" class="form-group">
								<label class="control-label">Test Price</label>
								<div class="input-group">
									<form:input path="price" placeholder="test price"
										cssClass="form-control" aria-describedby="basic-addon2" />
									<span class="input-group-addon" id="basic-addon2">
										<i class="fa fa-inr" aria-hidden="true"></i>
									</span>
								</div>
								<div class="text-danger">
									<form:errors path="price" />
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<legend>Test Ranges</legend>
							<div id="form-group-normalMin" class="form-group">
								<label class="control-label">Min. Normal Value</label>
								<form:input path="normalMin" placeholder="min. value"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="normalMin" />
								</div>
							</div>
		
							<div id="form-group-normalMax" class="form-group">
								<label class="control-label">Max. Normal Value</label>
								<form:input path="normalMax" placeholder="max. value"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="normalMax" />
								</div>
							</div>
		
							<div id="form-group-unitUsed" class="form-group">
								<label class="control-label">Test Unit</label>
								<form:input path="unitUsed"
									placeholder="unit used ex. mg/dl, fl, pg, % etc."
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="unitUsed" />
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
					<validation:CRUD validateURL="/test/update" requestMethod="POST"
						busyMessage="creating test..." asynch="false"
						callBackOnSuccess="testCreationSuccess" formId="create-test"
						noSubmit="1" />
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
						<a href="<c:url value="/test/create"/>" class="btn btn-info"> Add New</a>
						<a href="<c:url value="/tests"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function testCreationSuccess() {
				var name = $("#name").val();
				$(".custom-modal-body")
						.text(
								name+" created successfully.");
				$("#lab-create-modal").modal('show');
			}
		</script>
	</body>
</html>