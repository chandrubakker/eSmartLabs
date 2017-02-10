<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Patients</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Patients</h1>
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
							<table id="patients-table" class="stripe hover cell-border">
								<thead>
									<tr>
										<th>Reg. No.</th>
										<th>Name</th>
										<th>Ref. Dr.</th>
										<th>Clinic</th>
										<th>Phone</th>
										<th>Report</th>
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
		
			var patientsTable = $('#patients-table')
					.DataTable(
							{
								dom: 'Bfrtip',
						        buttons: [
						            'copy', 'print'
						        ],
								"language" : {
									"emptyTable" : "patients not available."
								},
								
								"ajax" : {
									"url" : rootPath + "/patients.json",
									"dataSrc" : ""
								},
		
								"aoColumnDefs" : [ {
									"sClass" : "column-0",
									"aTargets" : [ 0, 1 ]
								}, {
									"sClass" : "column-1",
									"aTargets" : [ 6 ]
								}, {
									"targets" : [ 4, 6 ],
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
												
												if(obj.reportFound) {
													
													return "Generated";
												} else {
													
													return "Not Generated";
												}
											}
										},
										{
											"mRender" : function(data, type, obj) {
												var viewUrl = rootPath
														+ '/patient/'+obj.patientId+'/view';
												
												var delUrl = rootPath
												+ '/patient/'
												+ obj.patientId
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
														+ 'patientsTable'
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