<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="../../css/movies.css">
    <c:import url="/jsp/partspages/head.jsp"/>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale"/>
    <title>Title</title>
</head>
<body style="background: white" >

<c:import url="/jsp/partspages/navbar.jsp"/>



<div class="container py-3">
    <div class="table-responsive">
        <div class="table-wrapper">
            <c:forEach items="${movieList}" varStatus="counter" var="movie">

                <a style="text-decoration: none"
                   href="
                   ${pageContext.request.contextPath}/ratingMovies?command=movie-page&id=${movie.getId()}">
                    <div class="movie_card">
                        <div class="info_section">
                            <div class="movie_header">
                                <img class="locandina"
                                     src="${pageContext.request.contextPath}${movie.getPoster()}"/>
                                <h1> ${movie.getName()}</h1>
                                <h4>${movie.getReleaseDate()},${movie.getProducer()} </h4>
                                <span class="minutes">${movie.getDuration()} <fmt:message key="movie.duration"/></span>
                                <p class="type">${movie.getGenre()}</p>
                            </div>
                            <div class="movie_desc">
                                <p class="text">
                                        ${movie.getAbout()}
                                </p>
                            </div>
                        </div>

                        <div class="blur_back
" style="background-image: url('${pageContext.request.contextPath}${movie.getBackground()}');">
                        </div>
                    </div>
                </a>
            </c:forEach>


            <c:if test="${movieList.size() != 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${maxPage}">
                            <c:if test="${i == currentPage+4}">
                                <li class="page-item">
                                    <a class="page-link">...</a>
                                </li>
                            </c:if>
                            <c:if test="${(((currentPage-1) == i) || ((i < currentPage+3) && (i > currentPage-3)))
                            || (i > maxPage-2) || (i == 1)}">
                                <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/ratingMovies?command=movies-page&p=<c:out value = "${i}"/>&s=${amountOfPage}">
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

</body>
</html>