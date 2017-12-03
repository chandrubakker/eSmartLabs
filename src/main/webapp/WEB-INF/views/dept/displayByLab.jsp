<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Departments of ${loggedInUser.client.lab.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Departments <small>${loggedInUser.client.lab.name}</small></h1>
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
							<table id="dept-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Code</th>
										<th>Name</th>
										<!-- <th>Lab Name</th> -->
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
		
			var deptTable = $('#dept-table')
					.DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "departments not available."
								},
								"pageLength": 6,
								"ajax" : {
									"url" : rootPath + "/lab/${loggedInUser.client.lab.id}/departments.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : []
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 2 ]
								}, {
									"targets" : [ 2 ],
									"orderable" : false
								} ],
		
								"columns" : [
										{
											"data" : "deptCode"
										},
										{
											"data" : "deptName"
										},
										/* {
											"data" : "labName"
										}, */
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/department/'+obj.deptId+'/view';
												
												var delUrl = rootPath
												+ '/department/'
												+ obj.deptId
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
														+ obj.deptName
														+ '&quot;, &quot;'
														+ 'deptTable'
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