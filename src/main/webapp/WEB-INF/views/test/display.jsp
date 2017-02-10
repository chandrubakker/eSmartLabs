<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<html>
	<head>
		<title>eSmartLabs: Tests</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Tests</h1>
					</div>
				</div>
				<br>
		
				<c:if test="${ not empty ExceptionMessage}">
					<div class="container-fluid">
						<div class="row">
							<h5 class="alert alert-warning text-center">${ExceptionMessage}</h5>
						</div>
					</div>
		
				</c:if>
		
				<div class="row">
					<div class="col-lg-12">
						<div class="table-responsive">
							<table id="tests-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Code</th>
										<th>Name</th>
										<th>Price (INR)</th>
										<th>Department</th>
										<th>Lab Name</th>
										<th class="no-print">Action</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			var rootPath = "${pageContext.request.contextPath}";
		
			var testsTable = $('#tests-table')
					.DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "tests not available."
								},
								
								"ajax" : {
									"url" : rootPath + "/tests.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : [ 0, 1 ]
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 5 ]
								}, {
									"targets" : [ 5 ],
									"orderable" : false
								} ],
		
								"columns" : [
										{
											"data" : "testCode"
										},
										{
											"data" : "testName"
										},
										{
											"data" : "price"
										},
										{
											"data" : "deptName"
										},
										{
											"data" : "labName"
										},
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/test/'+obj.testId+'/view';
												
												var delUrl = rootPath
												+ '/test/'
												+ obj.testId
												+ "/delete";
												
												return [ '<a class="btn btn-default btn-xs no-print" href='
														+ viewUrl
														+ '>'
														+ '<i class="fa fa-eye"></i>'
														+ '</a>'
														+ '<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_TECHNICIAN">'
														+ ' <a class="btn btn-danger btn-xs no-print" onclick="deleteConfirm(&quot;'
														+ delUrl
														+ '&quot;, &quot;'
														+ obj.testName
														+ '&quot;, &quot;'
														+ 'testsTable'
														+ '&quot;);" href='
														+ '#'
														+ '>'
														+ '<i class="fa fa-trash-o"></i>'
														+ '</a>'
														+ '</security:authorize>' ];
											}
										} ]
							});
		</script>
	</body>
</html>