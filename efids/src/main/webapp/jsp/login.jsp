<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login Page</title>
<!-- CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- AJAX -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- MODAL -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
<!-- VALIDATE -->
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.2/jquery.validate.min.js"></script>
<!-- ICON -->
<link rel="stylesheet" href="/fontawesome-free-6.3.0-web/css/all.css" >
</head>
<body>
	<div class="container d-flex justify-content-center align-items-center" style="min-height:100vh;">
		<div class="text-center">
			<img src="/image/aot_logo.jpg" alt="logo" style="max-width: 300px; margin-bottom: 30px;">
			<form action="/user/login" method="post">
				<div class="form-group row">
					<label for="username" class="col-sm-5 col-form-label text-right"><i class="fa-solid fa-user"></i> Username</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-sm-5 col-form-label text-right"><i class="fa-solid fa-key"></i> Password</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-3"></div>
					<div class="col-sm-9">
						<button type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Login</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>
