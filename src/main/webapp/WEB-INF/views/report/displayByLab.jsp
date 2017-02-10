<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Reports</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Reports <small>${loggedInUser.client.lab.name}</small></h1>
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
							<table id="clinic-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Reg. Num</th>
										<th>Patient Name</th>
										<th>Ref. By Dr.</th>
										<th>Clinic Name</th>
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
		
			var clinicTable = $('#clinic-table')
					.DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "clinics not available."
								},
								
								"ajax" : {
									"url" : rootPath + "/lab/${loggedInUser.client.lab.id}/reports.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : []
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 5 ]
								}, {
									"targets" : [ 4, 5 ],
									"orderable" : false
								} ],
		
								"columns" : [
										{
											"data" : "regNum"
										},
										{
											"data" : "patientName"
										},
										{
											"data" : "refBy"
										},
										{
											"data" : "clinicName"
										},
										{
											"data" : "phone"
										},
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/patient/'+obj.patientId+'/report/view';
												
												var delUrl = rootPath
												+ '/report/'
												+ obj.reportId
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
														+ obj.patientName
														+ '&quot;, &quot;'
														+ 'clinicTable'
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