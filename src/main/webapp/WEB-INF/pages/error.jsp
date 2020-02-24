<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <title>Error</title>
    <link href="<c:url value="/res/img/v6.png"/>" rel="icon" type="image/png"/>
    <link href="<c:url value="/res/css/error_style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="site">
    <div class="sketch">
        <div class="bee-sketch red"></div>
    </div>

    <h1>Wow!
      ${reason}
    </h1>


</div>

</body>
</html>

