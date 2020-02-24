<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <title>Log in</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="<c:url value="/res/img/img-01.png"/>" alt="IMG">
            </div>
            <form:form class="login100-form validate-form" name="user" method="POST">
					<span class="login100-form-title">
						 Hello, who's this?
					</span>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="login" placeholder="Login" id="login" value="${user.login}" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password" id="password" value="${user.password}" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="container-login100-form-btn">

                    <button class="login100-form-btn" type="submit" value="$checkUser">
                        Log in
                    </button>
                </div>

                <div class="text-center p-t-136">
                    <a class="txt2" href="<c:url value="/registration"/>">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
