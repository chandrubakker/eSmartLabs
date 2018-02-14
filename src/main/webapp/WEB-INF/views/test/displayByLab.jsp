<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html>
	<head>
		<title>eSmartLabs: ${loggedInUser.client.lab.name}: Tests</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 title-col">
				<h1 class="page-title-one text-center">
					Tests <small>${loggedInUser.client.lab.name}</small>
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
					<table id="tests-table" class="stripe hover cell-border">
						<thead>
							<tr>
								<th>Code</th>
								<th>Name</th>
								<th>Price (INR)</th>
								<th>Department</th>
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
			
			var tableOptions = {
				dom: 'Bfrtip',
		        buttons: [
		            'copy', 'print'
		        ],
				"language" : {
					"emptyTable" : "tests not available."
				},
				"pageLength": 6,
				"ajax" : {
					"url" : rootPath + "/lab/${loggedInUser.client.lab.id}/tests.json",
					"dataSrc" : ""
				},

				"aoColumnDefs" : [ {
					"sClass" : "column-0",
					"aTargets" : []
				}, {
					"sClass" : "column-1",
					"aTargets" : [ 4 ]
				}, {
					"targets" : [ 4 ],
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
						"mRender" : function(data, type, obj) {
							
							var viewUrl = rootPath + '/test/'+obj.testId+'/view';
							var delUrl = rootPath + '/test/' + obj.testId + "/delete";
							
							
							var viewBtn = '<a class="btn btn-info btn-xs no-print"';
							viewBtn = viewBtn + ' href=\"' + viewUrl + '\" >';
							viewBtn = viewBtn + '<i class="fa fa-eye" aria-hidden="true"></i>';
							viewBtn = viewBtn + '</a>';
							
							var deleteBtn = '<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_TECHNICIAN">';
							deleteBtn = deleteBtn +	'<a class="btn btn-danger btn-xs no-print"';
							deleteBtn = deleteBtn + 'onclick="deleteConfirm(\''+ delUrl +'\', \''+ obj.testName +'\', \'testsTable\');" >';
							deleteBtn = deleteBtn + '<i class="fa fa-trash-o" aria-hidden="true"></i>';
							deleteBtn = deleteBtn + '</a>';
							deleteBtn = deleteBtn + '</security:authorize>';
							
							return [ viewBtn + ' ' + deleteBtn ];
						}
					} 
				]
			};
			
			var testsTable = $('#tests-table').DataTable(tableOptions);
		</script>
	</body>
</html>