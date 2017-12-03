<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Page Content -->
<html>
<head>
<title>eSmartLabs: Technician</title>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row noPrint">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-flask text-warning"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>My Lab</p>
										1.0
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/lab/1/profile" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-folder-open-o text-green-one"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Departments</p>
										+8
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/departments" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-hourglass-o text-danger"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Tests</p>
										400
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/tests" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-h-square text-success"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Clinics</p>
										+45
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/clinics" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-stethoscope text-info"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Doctors</p>
										+120
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/doctors" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-heartbeat text-red-one"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Patients</p>
										+1000
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/patients" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-file-pdf-o text-primary"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>Reports</p>
										+800
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/reports" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-3 col-sm-6">
					<div class="card card-stats">
						<div class="content">
							<div class="row">
								<div class="col-xs-5">
									<div class="icon-big text-center icon-warning">
										<i class="fa fa-user-md text-md-user"></i>
									</div>
								</div>
								<div class="col-xs-7">
									<div class="numbers">
										<p>My Account</p>
										1.0
									</div>
								</div>
							</div>
							<div class="footer">
								<hr>
								<a href="<c:url value="/user/${loggedInUser.id}/account" />" class="card-link">
									<div class="stats">
										<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> View
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>