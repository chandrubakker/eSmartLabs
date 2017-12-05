<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<html>
	<head>
		<title>eSmartLabs: Create New Test Group</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Create <small>New Test Group</small>
						</h1>
					</div>
				</div>
		
				<div class="row">
					<c:url value="#" var="createTestGroup" />
					<form:form role="form" action="${createTestGroup}" method="POST" modelAttribute="testGroup" id="create-test-group">
						<div class="col-lg-6 col-lg-offset-3 test_details">
							
							<h3 class="custom-heading-view">Test Details</h3>
							<form:hidden path="id" />
							
							<div id="form-group-name" class="form-group">
								<label class="control-label">Test Group Name</label>
								<form:input path="name" placeholder="test group name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
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
					<validation:CRUD validateURL="/test-group/create" requestMethod="POST"
						busyMessage="creating test group..." asynch="false"
						callBackOnSuccess="testGroupCreationSuccess" formId="create-test-group"
						noSubmit="1" />
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="test-group-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/test-group/create"/>" class="btn btn-info"> Add New</a>
						<a href="<c:url value="/test-groups"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function testGroupCreationSuccess() {
				
				var name = $("#name").val();
				$(".custom-modal-body").text(name + " created successfully.");
				$("#test-group-create-modal").modal('show');
			}
		</script>
	</body>
</html>