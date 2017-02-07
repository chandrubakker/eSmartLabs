<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="Chandrashekhar">
		<title>
			<sitemesh-decorator:title default="Public Theme" />
		</title>
		
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/css/bootstrap.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/css/bootstrap.min.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/bootstrap/font-awesome/css/font-awesome.css' />" />
		<link rel="stylesheet" href="<c:url  value='/resources/ui/custom/css/style.css' />" />
	</head>
	<body>
		<div class="container">
			<sitemesh-decorator:body />
		</div>
		<script type="text/javascript" src="<c:url  value='/resources/ui/bootstrap/js/bootstrap.min.js' />"></script>
	</body>
</html>