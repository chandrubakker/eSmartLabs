<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>eSmartLabs: Bad Request</title>
		<style type="text/css">
	
			.error-page-btn {
				padding: 5px 10px;
				font-size: 25px;
			}
			
			.img404 {
			    height: auto;
			    width: 50%;
			}
			
			.error-title {
			    font-size: 24px;
			    line-height: 24px;
			    margin: 30px 0px 0px;
			}
			
			.error-head {
				font-size: 80px; 
				font-weight: 600;
				color: rgba(0,0,0,.5);
			}
		</style>
	</head>
	<body>
		<section style="margin-top: 100px;" class="login-back">
			<div class="container">
				<div class="row">
					<div class="text-center">
						<img alt="error" class="img404" src="<c:url value="/resources/ui/images/500.png" />">
						<div class="error-desc">
							<br>
							<br>
							<br>
							
							<a  class="btn btn-info error-page-btn" onclick="history.go(-1);">
								<i class="fa fa fa-angle-double-left"></i> Go Back
							</a>
						</div>
					</div>
				</div>
			</div>	
		</section>
	</body>
</html>