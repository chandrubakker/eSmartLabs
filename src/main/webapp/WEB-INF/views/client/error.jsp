<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags/ajax" prefix="ajax"%>

<html>
	<head>
		<title>eSmartLabs: Lab Registration Confirmation Error</title>
	</head>
	<body>
		<div class="modal fade" id="errorModal" tabindex="-1"
			aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title" id="myModalLabel">Error occurred</h3>
					</div>
					<div class="modal-body custom-name">
						<div class="alert alert-warning text-justify">
							<c:set value="" var="url" />
							<c:choose>
								<c:when
									test="${eCode eq 'USED' or eCode eq 'EXP' or eCode eq 'IL' or eCode eq 'NF'}">
									<c:set value="The confirmation link is either expired or used. Contact administrator."
										var="message" />
									<h1>Oops...<small>${message}</small></h1>
								</c:when>
								<c:otherwise>
									<c:set value="The user is blocked/not found, contact administrator."
										var="message" />
									<h1>Oops...<small>${message}</small></h1>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="modal-footer">
						<c:set value="" var="url" />
						<c:choose>
							<c:when test="${eCode eq 'USED' or eCode eq 'EXP' or eCode eq 'IL' or eCode eq 'NF'}">
								<c:set value="/" var="url" />
								<a href="<c:url value="${url}" />" class="btn btn-info">
									Close
								</a>
							</c:when>
							<c:otherwise>
								<c:set value="/" var="url" />
								<a href="<c:url value="${url}" />" class="btn btn-default">Home</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(window).load(function() {
				$('#errorModal').modal('show');
			});
		</script>
	</body>
</html>