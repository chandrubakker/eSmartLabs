<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Labs</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Labs</h1>
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
							<table id="labs-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Name</th>
										<th>Owner Name</th>
										<th>Owner Email</th>
										<th>Location</th>
										<th>Phone</th>
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
		
			var labTable = $('#labs-table')
					.DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "labs not available."
								},
								
								"ajax" : {
									"url" : rootPath + "/labs.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : [ 0 ]
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 5 ]
								}, {
									"targets" : [ 2, 4, 5 ],
									"orderable" : false
								} ],
		
								"columns" : [
										{
											"data" : "labName"
										},
										{
											"data" : "adminName"
										},
										{
											"data" : "adminEmail"
										},
										{
											"data" : "location"
										},
										{
											"data" : "phone"
										},
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/lab/'+obj.labId+'/profile';
												
												var delUrl = rootPath
												+ '/lab/'
												+ obj.labId
												+ "/delete";
												
												return [ '<a class="btn btn-default btn-xs no-print" href='
														+ viewUrl
														+ '>'
														+ '<i class="fa fa-eye"></i>'
														+ '</a>'
														+ '<security:authorize ifAnyGranted="ROLE_ADMIN">'
														+ ' <a class="btn btn-danger btn-xs no-print" onclick="deleteConfirm(&quot;'
														+ delUrl
														+ '&quot;, &quot;'
														+ obj.labName
														+ '&quot;, &quot;'
														+ 'labTable'
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