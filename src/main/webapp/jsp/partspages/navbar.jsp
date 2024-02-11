<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="custom-tags" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<header class="p-3 bg-dark text-white">
  <div style="height: 50px" class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <sc:access role="GUEST">
        <ul class="nav col col-lg-auto me-lg-auto mr-auto mb-2 justify-content-center mb-md-0">
          <li><a href="${pageContext.request.contextPath}/ratingMovies?command=movies-page&p=1&s=5"
                 class="nav-link px-2 text-white"><fmt:message key="nav.films.page"/></a>
        </ul>
        <div class="text-end">
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=login-page"
             class="btn btn-outline-light me-2"><fmt:message
                  key="nav.login"/></a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=sign-up-page"
             class="btn btn-warning"><fmt:message key="nav.sign.up.lower"/></a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=ru"
             class="btn btn-outline-light me-2 langToggle" data-onstyle="light">RU</a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=en"
             class="btn btn-outline-light me-2 langToggle" data-onstyle="light">EN</a>
        </div>
        </sc:access>

        <sc:access role="USER">
            <ul class="nav col col-lg-auto me-lg-auto mr-auto mb-2 justify-content-center mb-md-0">

                <li><a href="${pageContext.request.contextPath}/ratingMovies?command=movies-page&p=1&s=5"
                       class="nav-link px-2 text-white"><fmt:message key="nav.films.page"/></a>
                </li>

            </ul>

            <div class="dropdown text-end mr-3">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/images/photo/${sessionScope.photo}" alt="mdo"
                         width="40" height="40" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ratingMovies?command=account-settings-page&id=${sessionScope.get("userId")}"><fmt:message key="nav.setting"/></a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${sessionScope.get("userId")}"><fmt:message key="nav.profile"/></a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/ratingMovies?command=logout"><fmt:message
                            key="nav.signout"/>
                           </a></li>
                </ul>
            </div>
        </sc:access>


        <sc:access role="ADMIN">
            <ul class="nav col col-lg-auto me-lg-auto mr-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/ratingMovies?command=movies-page&p=1&s=5"
                       class="nav-link px-2 text-white"><fmt:message key="nav.films.page"/></a>
                </li>
                <li><a href="${pageContext.request.contextPath}/ratingMovies?command=users-page&p=1&s=5" class="nav-link px-2 text-white">
                    <fmt:message key="nav.users.page"/></a>
                <li><a href="${pageContext.request.contextPath}/ratingMovies?command=createMovies-page&p=1&s=5" class="nav-link px-2 text-white">
                    <fmt:message key="nav.added.movie.page"/></a>
                </li>
            </ul>
            <div class="dropdown text-end mr-3">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/images/photo/${sessionScope.photo}" alt="mdo"
                         width="40" height="40" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ratingMovies?command=account-settings-page&id=${sessionScope.get("userId")}"><fmt:message key="nav.setting"/></a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${sessionScope.get("userId")}"><fmt:message key="nav.profile"/></a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/ratingMovies?command=logout"><fmt:message
                            key="nav.signout"/></a></li>
                </ul>
            </div>
            <div class="dropdown text-end">
                <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=ru"
                   class="btn btn-outline-light me-2 langToggle" data-onstyle="light">RU</a>
                <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=en"
                   class="btn btn-outline-light me-2 langToggle" data-onstyle="light">EN</a>
            </div>
        </sc:access>


    </div>
  </div>
</header>
