<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<html>
	<head>
		<title>eSmartLabs: Profile: ${user.name}</title>
	</head>
	<body>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							User Account <small>of ${user.name}<c:if
									test="${user.id eq loggedInUser.id}"> (me)</c:if></small>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<security:authorize access="hasRole('ROLE_LAB_ADMIN')">
							<c:if
								test="${loggedInUser.role.name eq 'admin' and user.id ne loggedInUser.id}">
								<a href="<c:url value="/users"/>"
									class="btn btn-default btn-sm"> <i class="fa fa-hand-o-left"></i>
									Back
								</a>
							</c:if>
							<c:if
								test="${loggedInUser.role.name eq 'admin' and user.id eq loggedInUser.id}">
								<a href="<c:url value="/technician/dashboard"/>"
									class="btn btn-default btn-sm"> <i class="fa fa-hand-o-left"></i>
									Back
								</a>
							</c:if>
						</security:authorize>
						<security:authorize access="hasRole('DOCTOR')">
							<a href="<c:url value="/doctor/dashboard"/>"
								class="btn btn-default btn-sm"> <i class="fa fa-hand-o-left"></i>
								Back
							</a>
						</security:authorize>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="profile-header-container">
							<div class="profile-header-img">
								<c:url value="/resources/ui/images/avatar-300x300.png"
											var="profile_pic" />
								<img class="img-circle" src="${profile_pic}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<fieldset>
							<legend>User details</legend>
						</fieldset>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<security:authorize
							access="hasRole('ROLE_LAB_ADMIN') or hasRole('DOCTOR') or hasRole('PATIENT')">
							<c:if test="${user.id eq loggedInUser.id}">
								<a href="<c:url value="/user/${user.id}/update"/>"
									class="btn btn-info btn-sm pull-right"><i class="fa fa-edit"></i>
									Edit</a>
							</c:if>
						</security:authorize>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-12">
						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<tbody>
									<tr>
										<th>User Name</th>
										<td><c:choose>
												<c:when test="${empty user.name}">
													NA
												</c:when>
												<c:otherwise>
													${user.name}
												</c:otherwise>
											</c:choose></td>
										<th>Email</th>
										<td><c:choose>
												<c:when test="${empty user.email}">
													NA
												</c:when>
												<c:otherwise>
													${user.email}
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<th>Role</th>
										<td><c:choose>
												<c:when test="${empty user.role.name}">
													NA
												</c:when>
												<c:otherwise>
													${user.role.name}
												</c:otherwise>
											</c:choose></td>
										<th>Last Visit</th>
										<td><c:choose>
												<c:when test="${empty user.lastLogin}">
													NA
												</c:when>
												<c:otherwise>
													<fmt:formatDate value="${user.lastLogin}"
														pattern="dd MMM, yyyy (hh:mm a)" />
												</c:otherwise>
											</c:choose></td>
									</tr>
									<security:authorize access="hasRole('ROLE_LAB_ADMIN')">
										<tr>
											<th>Is Active</th>
											<td><c:choose>
													<c:when test="${empty user.active}">
														NA
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${user.active eq true}">
															YES
														</c:when>
															<c:otherwise>
																NO
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose></td>
											<td colspan="2" class="text-center"><c:choose>
													<c:when test="${user.active eq true}">
														<a
															href="<c:url value="/user/${user.id}/activate"/>"
															class="btn btn-danger btn-sm"><i
															class="fa fa-power-off"></i> Disable</a>
													</c:when>
													<c:otherwise>
														<a
															href="<c:url value="/user/${user.id}/activate"/>"
															class="btn btn-info btn-sm"><i class="fa fa-power-off"></i>
															Enable</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</security:authorize>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>