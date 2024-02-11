<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <head>
        <c:import url="/jsp/partspages/head.jsp"/>
        <title>
            f1
        </title>
    </head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>

<div class="row flex-column" style="padding-left: 10px">

    <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/ratingMovies?command=createMovie"
          class="flex-box col-md-6">
        <h1><fmt:message key="create.film"/></h1>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.link.poster"/> </span>
            <input type="text" class="form-control" name="img" minlength="4" maxlength="128"
                   required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.link.background"/></span>
            <input type="text" class="form-control" name="imgBack" minlength="4" maxlength="128"
                   required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.name"/></span>
            <input type="text" class="form-control" name="name" minlength="4" maxlength="32"
                   required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.about"/></span>
            <input type="text" class="form-control" name="about" minlength="8" maxlength="256"
                   required>
        </div>
        <div>
            <span class="form-label"><fmt:message key="create.film.date.release"/></span>
            <input type="date" id="start" name="data"
                   value="2018-07-22"
                   min="1941-01-01" max="2025-12-31" required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.dislike.amount"/> </span>
            <input type="text" name="rating"  class="form-control" required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.producer.name"/> </span>
            <input type="text" name="producer" minlength="2" maxlength="32" class="form-control" required>
        </div>
        <div class="mb-3">
            <span class="form-label"><fmt:message key="create.film.duration"/>  </span>
            <input type="number" name="duration" minlength="1" maxlength="3" class="form-control" required>
        </div>
        <div>
            <span class="form-label"><fmt:message key="create.film.genre"/></span>
            <select name="genre">
                <option value="1"><fmt:message key="genre.comedy"/></option>
                <option value="2"><fmt:message key="genre.thriller"/></option>
                <option value="3"><fmt:message key="genre.action"/></option>
                <option value="4"><fmt:message key="genre.horror"/></option>
            </select>
        </div>
        <h3 class="text-danger error-message text-centr">
            <fmt:message key="settings.error.${errorMessage}" />
        </h3>
        <h3  style="color: green">
            <fmt:message key="settings.success.${successMessage}" />
        </h3>
        <button type="submit" class="btn btn-primary"><fmt:message key="create.film.button"/></button>
    </form>
</div>

</body>
</html>
