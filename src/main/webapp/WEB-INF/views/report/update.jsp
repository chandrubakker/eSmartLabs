<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation" %>
<html>
	<head>
		<title>eSmartLabs: Update Report: ${patient.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Update <small>Report of ${patient.name}</small>
						</h1>
					</div>
				</div>
				
				<div class="row">
					<c:url value="#" var="createReport" />
					<form:form role="form" action="${createReport}" method="POST"
						modelAttribute="patient" id="create-report">
						<div class="col-lg-6">
							<form:hidden path="id" />
							<form:hidden path="report.id" />
							
							<div class="form-group">
								<label class="control-label">Reg. Number</label>
								<form:input path="regNum" readonly="true" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label class="control-label">Patient Name</label>
								<form:input path="name" id="patient-name" readonly="true" placeholder="patient name"
									cssClass="form-control" />
							</div>
						</div>
						
						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label">Age</label>
								<form:input path="age" readonly="true" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label class="control-label">Phone</label>
								<form:input path="phone" readonly="true" cssClass="form-control" />
							</div>
						</div>
						
						<fieldset class="col-lg-12">
							<legend>enter test results</legend>
							<div class="table-responsive">
								<table class="table table-striped table-hover" id="testsResultsTable">
									<thead>
										<tr>
											<th scope="row" class="text-center">#</th>
											<th class="text-center">Test Name</th>
											<th class="text-center">Min. Value</th>
											<th class="text-center">Max. Value</th>
											<th class="text-center">Observed Value</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${patient.report.testResults}" varStatus="status" var="testResult">
											<tr>
												<th scope="row" class="text-center">${status.index+1}</th>
												<form:hidden path="report.testResults[${status.index}].id" />
												<form:hidden path="report.testResults[${status.index}].test.id" />
												<form:hidden path="report.testResults[${status.index}].patient.id" />
												<td>
													<div class="form-group">
														<form:input class="form-control" readonly="true" path="report.testResults[${status.index}].test.name" />
													</div>
												</td>
												<td>
													<div class="form-group">
														<form:input class="form-control" readonly="true" path="report.testResults[${status.index}].test.normalMin" />
													</div>
												</td>
												<td>
													<div class="form-group">
														<form:input class="form-control" readonly="true" path="report.testResults[${status.index}].test.normalMax" />
													</div>
												</td>
												<td>
													<div id="form-group-report-testResults-${status.index}--observedValue" class="form-group">
														<form:input class="form-control" path="report.testResults[${status.index}].observedValue" placeholder="Observed Value" />
														<div class="text-danger">
															<form:errors path="report.testResults[${status.index}].observedValue"
																class="text-danger" />
														</div>
													</div>
												</td>
											</tr>	
										</c:forEach>	
									</tbody>
								</table>
							</div>
							
						</fieldset>
						<div class="col-lg-12">
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm">
                  <i class="fa fa-database"></i> Update
								</button>
								<button type="reset" class="btn btn-default btn-sm">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</div>
					</form:form>
					<validation:CRUD validateURL="/patient/report/update" requestMethod="POST" busyMessage="updating report of ${patient.name}..." asynch="false" callBackOnSuccess="reportCreationSuccess" formId="create-report" noSubmit="1" />
				</div>
			</div>
		</div>
		<div class="modal fade" id="report-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/patient/${patient.id}/report/view"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function reportCreationSuccess() {
				var name = $("#patient-name").val();
				$(".custom-modal-body")
						.text(
								name+" report created successfully.");
				$("#report-create-modal").modal('show');
			}
		</script>
	</body>
</html>