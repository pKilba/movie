
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <c:import url="/jsp/partspages/navbar.jsp"/>
    <link rel="stylesheet" type="text/css" href="../../css/login.css">
    <title>Title</title>
</head>

<body class="main-bg">
<form method="POST" name="loginForm" action="${pageContext.request.contextPath}/ratingMovies?command=login"
      class="flex-box col-md-12">
    <div class="login-container text-c animated flipInX " >
        <div>
            <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
        </div>
        <p class="text-whitesmoke"><fmt:message key="login.signin"/></p>
        <div class="container-content">
            <form class="margin-t">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="login" minlength="8" maxlength="32"
                           name="login" value="${login}"
                           requiredplaceholder="login" required="">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" placeholder="*****" name="password" minlength="8"
                           maxlength="32" required="">
                </div>
                <button type="submit" class="form-button button-l margin-b"><fmt:message
                        key="login.signin.button"/></button>
                <a class="text-darkyellow"
                   href="${pageContext.request.contextPath}/ratingMovies?command=sign-up-page"><small><fmt:message
                        key="login.signup.button"/></small></a>
            </form>
            <h3 class="text-danger error-message">
                <fmt:message key="login.error.${errorMessage}"/>
            </h3>
        </div>
    </div>
</form>
</body>
</html>
