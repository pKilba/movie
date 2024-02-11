<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/jsp/partspages/head.jsp"/>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale"/>
</head>
<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<div  class="bcknd-image" style="background-image: url(${pageContext.request.contextPath}${movie.getBackground()})">
    <div class="container" style="height: 650px;">
        <div class="cellphone-container">
            <div class="movie">
                <div style="background-image: url(${pageContext.request.contextPath}${movie.getPoster()})" class="movie-img"></div>
                <div class="text-movie-cont">
                    <div class="col1">
                        <h1 style="color: white">${movie.getName()}</h1>
                        <ul class="movie-gen">
                            <li>${movie.getDuration()}<fmt:message key="movie.duration"/> /</li>
                            <li style="padding-bottom: 10px">${movie.getGenre()}</li>
                        </ul>
                    </div>
                    <div class="mr-grid summary-row">
                        <div class="col2">
                            <h5>${movie.getProducer()}</h5>
                        </div>
                        <div class="col2">
                            <ul class="movie-likes">
                                <li><i><fmt:message key="movie.rating"/></i>${movie.getRating()}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="video-wrapper" style="text-align: center; width: 350px; height: 150px;">
                        <video width="100%" height="100%" controls style="object-fit: cover;">
                            <source src="${movie.getLink_movie()}" type="video/mp4">
                            Ваш браузер не поддерживает воспроизведение видео.
                        </video>
                    </div>

                    <div class="mr-grid">
                        <div class="col1">
                            <p class="movie-description">${movie.getAbout()} </p>
                        </div>
                    </div>
                    <div class="col1">
                        <a href="${pageContext.request.contextPath}/ratingMovies?command=reviews-page&p=1&s=10&movieId=${movie.getId()}"><fmt:message key="movie.comments"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
