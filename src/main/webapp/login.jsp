<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<html>
<head>
<style type="text/css">
.note {
	text-align: center;
	height: 80px;
	background: -webkit-linear-gradient(left, #0072ff, #8811c5);
	color: #fff;
	font-weight: bold;
	line-height: 80px;
}

.form-content {
	padding: 5%;
	border: 1px solid #ced4da;
	margin-bottom: 2%;
}

.form-control {
	border-radius: 1.5rem;
}

.btnSubmit {
	border: none;
	border-radius: 1.5rem;
	padding: 1%;
	width: 20%;
	cursor: pointer;
	background: #0062cc;
	color: #fff;
}
</style>
</head>
<body>
	<form action="/login" id="loginFormId" name="loginForm" method="post">
		<div class="container register-form">
			<div class="form">
				<div class="note">
					<p>Login Page</p>
				</div>

				<div class="form-content">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control" name="emaiId"
									placeholder="Your Email *" value="" required="required"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control" name="password"
									placeholder="Your Password *" value="" required="required"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-6">
							<button type="submit" class="btnSubmit">Login</button>
							<button type="submit" onclick="document.getElementById('loginFormId').action='/registrationPage'" formnovalidate="formnovalidate" class="btnSubmit">Registration</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>