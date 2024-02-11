<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html>

<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <title>
    </title>

</head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<div class="container py-3">
    <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img src="${pageContext.request.contextPath}/images/photo/${user.getProfilePicture()}"
                             alt="notPhoto"
                             class="rounded-circle" width="200">
                        <div class="mt-3">
                            <h4>${user.getLogin()}</h4>
                            <p class="mb-1 h5 <c:choose>
<c:when test="${user.getUserStatus() == 'ACTIVE'}">text-success</c:when>
<c:otherwise>text-danger</c:otherwise>
</c:choose>">${user.getUserStatus()}</p>
                            <p class="text-muted font-size-sm h5">${user.getUserRole()}</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">
                                <fmt:message key="profile.name"/>
                            </h6>
                        </div>
                        <div class="col-sm-9 text-secondary">${user.getName()}</div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">
                                <fmt:message key="profile.email"/>
                            </h6>
                        </div>
                        <div class="col-sm-9 text-secondary">${user.getEmail()}</div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">
                                <fmt:message key="profile.telegram"/>
                            </h6>
                        </div>
                        <div class="col-sm-9 text-secondary">${user.getTelegramAccount()}</div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">
                                <fmt:message key="profile.dataReg"/>
                            </h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <div class="col-sm-9 text-secondary">
                                <fmt:formatDate value="${user.getDate()}" pattern="yyyy-MM-dd HH:mm"/>
                            </div>

                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>