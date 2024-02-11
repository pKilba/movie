<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="">

<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <title>
        f1
    </title>
</head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>

<div class="container py-3">
    <div class="table-responsive">
        <div class="table-wrapper">

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message
                            key="user.users"/></th>
                    <th><fmt:message
                            key="user.login"/></th>
                    <th><fmt:message
                            key="user.data.time"/></th>
                    <th><fmt:message
                            key="user.role"/></th>
                    <th><fmt:message
                            key="user.active"/></th>
                </tr>
                </thead>
                <tbody class="table-of-users">
                <c:forEach items="${userList}" varStatus="counter" var="user">
                    <tr>
                        <td>${user.getId()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${user.getId()}">
                                <img src="${pageContext.request.contextPath}/images/photo/${user.getProfilePicture()}"
                                     class="avatar rounded-circle mr-4" height="50px" width="50px"
                                     alt="Avatar">
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${user.getId()}">
                                    ${user.getLogin()}
                            </a>
                        </td>

                        <td>
                            <fmt:formatDate value="${user.getCreateTime()}" pattern="yyyy-MM-dd HH:mm"/>

                        </td>
                        <td>${user.getUserRole()}</td>
                        <td id="status-part">
                            <span id="status-dot" class="status
<c:choose>
<c:when test="${user.getUserStatus() == 'ACTIVE'}">text-success</c:when>
<c:otherwise>text-danger</c:otherwise>
</c:choose> mr-2 h2">•</span>
                            <span class="status-user">${user.getUserStatus()}</span>
                            <button type="button" class="btn border btn-action-ban
<c:choose>
<c:when test="${user.getUserStatus() == 'ACTIVE'}">btn-outline-danger ml-4</c:when>
<c:otherwise>btn-outline-success ml-2</c:otherwise>
</c:choose>">
                                <c:choose>
                                    <c:when test="${user.getUserStatus() == 'ACTIVE'}">BAN</c:when>
                                    <c:otherwise>UNBAN</c:otherwise>
                                </c:choose>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <c:if test="${userList.size() != 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${maxPage}">
                            <c:if test="${i == currentPage+4}">
                                <li class="page-item">
                                    <a class="page-link">...</a>
                                </li>
                            </c:if>
                            <c:if test="${(((currentPage-1) == i) || ((i < currentPage+3) && (i > currentPage-3))) || (i > maxPage-2) || (i == 1)}">
                                <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/ratingMovies?command=users-page&p=<c:out value = "${i}"/>&s=${amountOfPage}">
                                        <c:out value="${i}"/>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>


        </div>
    </div>
</div>

<script src="/js/admin-panel.js"></script>

</body>
</html>