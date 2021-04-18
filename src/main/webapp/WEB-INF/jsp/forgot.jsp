<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgotten Password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="shortcut icon" href="assets/images/logo.ico">
<link href="assets/css/login.css" rel="stylesheet" type="text/css">
</head>
<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="assets/images/logo.jpg" alt="logo">
					</div>
					<h4 class="card-title" style="text-align: center;">Smart Event
						System</h4>
					<br>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Reset Password</h4>
							<form:form method="POST" action="reset" modelAttribute="forgot">
								<div class="form-group">
									<label for="UserName"><form:label path="UserName">User Name</form:label></label>
									<form:input id="name" path="UserName" class="form-control"
										type="text" value="" required="true" autofocus="true" />
									<div class="invalid-feedback">Username is invalid</div>
								</div>
								<div class="form-group">
									<label for="Email"><form:label path="UserName">Email Address</form:label></label>
									<form:input id="Email" path="Email" class="form-control"
										type="email" value=" " required="true" autofocus="true" />
									<div class="invalid-feedback">Email is invalid</div>
								</div>
								<div class="form-group">
									<label for="Password"><form:label path="Password">Password</form:label>
									</label>
									<form:input id="password" path="Password" class="form-control"
										type="password" name="password" value="" required="true"
										data-eye="true" />
									<div class="invalid-feedback">Password is required</div>
								</div>
								<div class="form-group">
									<label for="ConfirmPassword"><form:label path="ConfirmPassword">Confirm Password</form:label>
									</label>
									<form:input id="ConfirmPassword" path="ConfirmPassword" class="form-control"
										type="password" name="ConfirmPassword" value="" required="true"
										data-eye="true" />
									<div class="invalid-feedback">Confirm Password is required</div>
								</div>
									<div class="form-group" style="height: 10px;">
									<label for="login">
										<a href="/" class="float-right">
											Login
										</a>
									</label>
								</div>
								<div class="form-group m-0">
									<form:button class="btn btn-primary btn-block">
                                  ResetPassword
                                  </form:button>
								</div>

							</form:form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>
	<script src="assets/js/jquery-3.6.0.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="assets/js/login.js"></script>
</body>
</html>
