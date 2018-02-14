<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
	<head>
		<title>eSmartLabs: Departments of ${loggedInUser.client.lab.name}</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 title-col">
				<h1 class="page-title-one text-center">
					Departments <small>${loggedInUser.client.lab.name}</small>
				</h1>
			</div>
		</div>
		
		<div class="row esm-back-row">
			<div class="col-lg-10 col-lg-offset-1 title-col">
				<a href="#" class="btn btn-default btn-sm esm-button esm-back-btn">
					<i class="fa fa-hand-o-left"></i> Back
				</a>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 title-col">
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
		
		<script type="text/javascript">
		
			var rootPath = "${pageContext.request.contextPath}";
			var refreshDT = false;
			
			var tableOptions = {
				dom: 'Bfrtip',
		        buttons: [
		            'copy', 'print'
		        ],
				"language" : {
					"emptyTable" : "departments not available."
				},
				"pageLength": 10,
				"ajax" : {
					"url" : rootPath + "/lab/${loggedInUser.client.lab.id}/departments.json",
					"dataSrc" : ""
				},

				"aoColumnDefs" : [ {
					"sClass" : "esm-dt-justify",
					"aTargets" : []
				}, {
					"sClass" : "esm-dt-center",
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
					{
						"mRender" : function(data, type, obj) {
							
							var viewUrl = rootPath + '/department/'+obj.deptId+'/view';
							var delUrl = rootPath + '/department/' + obj.deptId + "/delete";
							
							var viewBtn = '<a class="btn btn-info btn-xs no-print"';
							viewBtn = viewBtn + ' href=\"' + viewUrl + '\" >';
							viewBtn = viewBtn + '<i class="fa fa-eye" aria-hidden="true"></i>';
							viewBtn = viewBtn + '</a>';
							
							var deleteBtn = '<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_TECHNICIAN">';
							deleteBtn = deleteBtn +	'<a class="btn btn-danger btn-xs no-print"';
							deleteBtn = deleteBtn + 'onclick="deleteConfirm(\''+ delUrl +'\', \''+ obj.deptName +'\');" >';
							deleteBtn = deleteBtn + '<i class="fa fa-trash-o" aria-hidden="true"></i>';
							deleteBtn = deleteBtn + '</a>';
							deleteBtn = deleteBtn + '</security:authorize>';
							
							return [ viewBtn + ' ' + deleteBtn ];
						}
					}
				]
			};
			
			var deptTable = $('#dept-table').DataTable(tableOptions);
		</script>
	</body>
</html>	