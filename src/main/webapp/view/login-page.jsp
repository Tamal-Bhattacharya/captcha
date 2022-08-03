<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
	<link rel="stylesheet" href="${path}/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-plus-sign"></span> User</strong>
					</div>
					<div class="panel-body">
						<form:form method="POST" action="/login" class="form-signin form-horizontal" commandName="user">
							<c:if test="${message ne null}">
								<div class="alert alert-danger">
									${message}
								</div>
							</c:if>
							<div class="form-group">
								<div class="col-md-12">
									<div class="input-group input-group-md">
										<form:input class="form-control" path="userName" placeholder="Enter User Name" required="true"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="input-group input-group-md">
										<form:input class="form-control"  path="password" placeholder="Enter Password" required="true"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<img src="data:image/png;base64,${captchaEncode}"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<form:input class="form-control" path="captcha" placeholder="Enter Captcha" required="true"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12" >
									<button type="submit" class="btn btn-default btn-xs">
										<span class="glyphicon glyphicon-floppy-disk"></span> Submit
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>