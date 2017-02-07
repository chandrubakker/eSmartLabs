<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Users</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Users</h1>
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
							<table id="users-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Name</th>
										<th>Email</th>
										<th>Active</th>
										<th>Role</th>
										<th>Last Login</th>
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
		
			$('#users-table').DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "Users not available."
								},
								
								"ajax" : {
									"url" : rootPath + "/users.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : [ 0, 1, 3 ]
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 2, 5 ]
								}, {
									"targets" : [ 5 ],
									"orderable" : false
								} ],
		
								"columns" : [
										{
											"data" : "name"
										},
										{
											"data" : "email"
										},
										{
											"data" : "enabled"
										},
										{
											"data" : "role.name"
										},
										{
											"data" : "lastLogin"
										},
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/user/'+obj.id+'/account';
												
												var delUrl = rootPath
														+ '/admin/user/delete?userId='
														+ obj.id;
												return [ '<a class="btn btn-default btn-xs no-print" href='
														+ viewUrl
														+ '>'
														+ '<i class="fa fa-eye"></i>'
														+ '</a>'
														+ '<security:authorize ifAnyGranted="ROLE_ADMIN">'
														+ ' <a class="btn btn-danger btn-xs no-print" onclick="deleteConfirm(&quot;'
														+ delUrl
														+ '&quot;, &quot;'
														+ obj.name
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