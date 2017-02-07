<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section style="margin-top: 100px;" class="login-back">
	<div class="container">
		<div class="row">
			<div class="text-center">
				<h1 class="error-head">Oh! Something went wrong : Global Errors</h1>

				<div class="error-desc">
					<br>
					<br>
					<br>
					
					<a  class="btn btn-info error-page-btn" onclick="history.go(-1);">
						<i class="fa fa-hand-o-left"></i> Go Back
					</a>
				</div>
			</div>
		</div>
	</div>	
</section>
<style type="text/css">

	.error-page-btn {
		padding: 10px 20px;
		font-size: 40px;
		font-weight: 600;
	}
	
	.error-head {
		font-size: 80px; 
		font-weight: 600;
		color: rgba(0,0,0,.5);
	}
</style>