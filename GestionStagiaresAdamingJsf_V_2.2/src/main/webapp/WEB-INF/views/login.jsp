<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">

<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/NiceAdmin/img/favicon.png">

<title>Inti Formation | Session Juin 2016</title>

<!-- Bootstrap CSS -->
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/bootstrap.min.css"
	rel="stylesheet">
<!-- bootstrap theme -->
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/bootstrap-theme.css"
	rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/elegant-icons-style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- Custom styles -->
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/NiceAdmin/css/style-responsive.css"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
</head>

<body class="login-img3-body">

	<div class="container">
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	         <div class="alert alert-warning" style="color:red;text-align:center;border-radius:3px!important">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Warning!</strong> Le mot de passe entré , ou bien l'Identifiant est Incorrect !!
  </div>
        </c:if>
		<form class="login-form" action="j_spring_security_check"
			method="post">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i>
					</span> <input type="text" class="form-control" placeholder="Username"
						autofocus name="j_username">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" name="j_password" class="form-control"
						placeholder="Password">
				</div>
				<label class="checkbox"> <input type="checkbox"
					value="remember-me"> Remember me <span class="pull-right">
						<a href="#"> Forgot Password?</a>
				</span>
				</label>
				<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
				<button class="btn btn-info btn-lg btn-block" data-toggle="modal" data-target="#myModal" type="submit">Signup</button>
			</div>
		</form>
	</div>
     

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Inscription Nouveau Etudiant</h4>
      </div>
      <div class="modal-body">
        <form class="login-form" action="j_spring_security_check"
			method="post">
			<div class="login-wrap">
				<p class="login-img">
					<i><img style="width:200px;" src="<%= request.getContextPath()%>/resources/NiceAdmin/img/logoiniti.png"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i>
					</span> <input type="text" class="form-control" placeholder="Username"
						autofocus name="j_username">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" name="j_password" class="form-control"
						placeholder="Password">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_box-checked"></i></span>
					<select type="password" name="j_password" class="form-control"
						>
						<option>Selectionnez un Role</option>
						<option>Etudiant</option>
						<option>Formateur</option>
						<option>Administrateur</option>
						</select>
				</div>
				<button class="btn btn-info btn-lg btn-block"  type="submit">Signin</button>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
  
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/NiceAdmin/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/NiceAdmin/js/bootstrap.js"></script>
</body>
</html>


