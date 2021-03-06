<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Cactus Login</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="../cactus/vendor/css/bootstrap.min.css" />
<link rel="stylesheet" href="../cactus/assets/css/app.css" />
<script src="../cactus/vendor/js/jquery-2.0.3.min.js"></script>
<script src="../cactus/vendor/js/jquery.validate.min.js"></script>
</head>
<body onload='document.loginForm.username.focus();' class="background">
<div>
<section class="main">
    <form class="form-2" name='loginForm' action="<c:url value='/login' />" method='POST' role="form">
        <h1><span class="log-in">Log in</span> or <span class="sign-up">sign up</span></h1>
        <p class="float">
            <label for="login"><i class="icon-user"></i>Username</label>
            <input type="text" name="username" placeholder="Username or email" id="username" class="form-control" required />
        </p>
        <p class="float">
            <label for="password"><i class="icon-lock"></i>Password</label>
            <input type="password" name="password" placeholder="Password"  id="password" class="form-control showpassword"  required />
        </p>
        <p class="clearfix">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" class="btn btn-danger" value="Login">
        </p>
    </form>​​
</section>
<c:if test="${not empty error}">
	<div class="alert alert-danger">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="alert alert-danger">${msg}</div>
</c:if>
</form>
	</div>
<script>
$(".form-2").validate();
</script>
</body>
</html>