<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Login V1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/animate.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/hamburgers.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/select2.min.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <link href="<c:url value="/res/css/util.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <!--===============================================================================================-->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script type="text/javascript"><!--
    function checkPasswordMatch() {
        var password = $("#pass1").val();
        var confirmPassword = $("#pass2").val();

        if (password !== confirmPassword)
            $("#divCheckPasswordMatch").html("Passwords do not match!");
        else
            $("#divCheckPasswordMatch").html("Passwords match.");
    }
    //--></script>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">

            <div class="login100-pic js-tilt" data-tilt>
                <img src="res/img/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" name="user" method="post">
					<span class="login100-form-title">
						Registration
					</span>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <label for="login"></label><input class="input100" type="text" name="login" placeholder="Login" id="login" value="${user.login}" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <label for="pass1"></label><input class="input100" type="password" name="password" placeholder="Password" id="pass1" value="${user.password}" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <label for="pass2"></label><input class="input100" type="password" name="pass" placeholder="Repeat password" id="pass2" onkeyup="checkPasswordMatch();"/>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="registrationFormAlert" id="divCheckPasswordMatch">
                </div>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Create account
                    </button>
                </div>
                <div class="text-center p-t-136">
                    <a class="txt2" href="<c:url value="/"/>">
                        Already have an account?
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>

