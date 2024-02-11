<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <c:import url="/jsp/partspages/head.jsp"/>
    <%@ taglib prefix="sc" uri="custom-tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link rel="stylesheet" type="text/css" href="../../css/reviews.css">
    <fmt:setBundle basename="locale"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
</head>
<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<section>
    <div class="container">
        <div class="col">
            <c:forEach items="${commentUserList}" var="entry">
                <div class="col-sm-5 col-md-6 col-8 pb-4">
                    <a style="text-decoration: none"
                       href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${entry.value.getId()}">
                        <div class="comment mt-4 text-justify float-left">
                            <img src="${pageContext.request.contextPath}/images/photo/${entry.value.getProfilePicture()}"
                                 alt=""
                                 class="rounded-circle" width="40"
                                 height="40">
                            <h4>${entry.value.getName()}</h4> <span><fmt:formatDate value="${entry.key.getCreateTimeComment()}" pattern="yyyy-MM-dd HH:mm" /></span>
                            <br>
                            <p>${entry.key.getMessage()}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>

        <div class="comment mt-4 ml-2">
            <c:if test="${commentUserList.size() != 0}">
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
                                      href="${pageContext.request.contextPath}/ratingMovies?command=reviews-page&movieId=${id}&id=${sessionScope.get("userId")}&p=<c:out value = "${i}"/>&s=${amountOfPage}">
                                        <c:out value="${i}"/>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>

        <sc:access role="USER">
        <div style="margin-top: 500px;">
            <div class="comment mt-5">
                <div class="col-lg-10 col-md-8  offset-md-1 offset-sm-3 col-12 mt-8">
                    <form id="algin-form" method="POST"
                          action="${pageContext.request.contextPath}/ratingMovies?command=leaveComment&movieId=${id}&id=${sessionScope.get("userId")}">
                        <div class="form-group">
                                <textarea name="leaveComment" id="" cols="120" rows="5" class="form-control"
                                          style="background-color: black;" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-outline-light"><fmt:message key="comment.leave"/></button>
                    </form>
                </div>
            </div>
                <div>
        </sc:access>

        <sc:access role="ADMIN">
            <div class="comment mt-5">
                <div class="col-lg-10 col-md-8  offset-md-1 offset-sm-3 col-12 mt-8">
                    <form id="algin-form" method="POST"
                          action="${pageContext.request.contextPath}/ratingMovies?command=leaveComment&movieId=${id}&id=${sessionScope.get("userId")}">
                        <div class="form-group">
                                <textarea name="leaveComment" cols="120" rows="5" class="form-control"
                                          style="background-color: black;" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-outline-light"><fmt:message key="comment.leave"/></button>
                    </form>
                </div>
            </div>
        </sc:access>
    </div>
</section>
</body>
