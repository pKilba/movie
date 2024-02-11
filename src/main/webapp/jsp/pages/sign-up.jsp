<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <c:import url="/jsp/partspages/navbar.jsp"/>
    <link rel="stylesheet" type="text/css" href="../../css/login.css">
    <title>Title</title>
</head>

<body class="main-bg">
<div class="login-container text-c animated flipInX">
    <div>
        <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
    </div>
    <p class="text-whitesmoke"><fmt:message
            key="signup.reg"/></p>
    <div class="container-content">
        <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/ratingMovies?command=sign-up">
            <div class="form-group">
                <input type="text" class="form-control" minlength="8" maxlength="32" name="login"
                       required>
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="name" minlength="2" maxlength="32" name="name"
                       required>
            </div>

            <div class="form-group">
                <input type="email" class="form-control" placeholder="email" minlength="2" maxlength="32" name="email"
                       required>
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="telegram" minlength="2" maxlength="32"
                       name="telegram"
                       required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="*****" name="password" minlength="8"
                       maxlength="32" required>
            </div>
            <button type="submit" class="form-button button-l margin-b"><fmt:message
                    key="signup.btn.submit"/></button>
            <a class="text-darkyellow"
               href="${pageContext.request.contextPath}/ratingMovies?command=login-page"><small><fmt:message
                    key="signup.login"/></small></a>
        </form>
        <h3 class="text-danger error-message">
            <fmt:message key="signup.error.${errorMessage}"/>
        </h3>
    </div>
</div>
</body>
</html>
