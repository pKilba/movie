<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang=">

<head>
    <c:import url="/jsp/partspages/head.jsp" />
    <title>
    </title>

   <link rel="stylesheet" href="/css/account-settings.css">
</head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<div class="container py-3 light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
        <fmt:message key="settings.account.settings" />
    </h4>

    <div class="card overflow-hidden">
        <div class="row no-gutters row-bordered row-border-light">

            <div class="col-md-3 pt-0" id="choiceSection">
                <div class="list-group list-group-flush account-settings-links">
                    <a class="list-group-item list-group-item-action active"
                       onclick="changeBtnSubmitGeneral()"
                       data-toggle="list" href="#account-general">
                        <fmt:message key="settings.general" />
                    </a>
                    <a class="list-group-item list-group-item-action" onclick="changeBtnSubmitPas()" data-toggle="list"
                       href="#account-change-password">
                        <fmt:message key="settings.change.password" />
                    </a>
                </div>
            </div>

            <div class="col-md-9">
                <div class="tab-content">
                    <div class="tab-pane fade active show" id="account-general">

                        <div class="card-body media align-items-center">
                            <img src="${pageContext.request.contextPath}
                            /images/photo/${sessionScope.photo}" alt="mdo"
                                 width="40" height="60" class="rounded-circle">
                            <div class="media-body ml-4">
                                <input id="ajaxfile" type="file" class="d-none"
                                       accept=".png, .jpg, .jpeg .gif"
                                       size="10" onchange="uploadFile()"/>
                                <button type="submit" class="btnUpload btn btn-primary">
                                    <fmt:message key="profile.change.photo" />
                                </button>
                                <script src="/js/upload-photo.js"></script>
                                <div class="text-light small mt-1">Allowed JPG, GIF or PNG.</div>
                            </div>
                        </div>
                        <hr class="border-light m-0">
                        <div class="card-body">
                            <form id="changeGeneralForm" name="changeGeneralDataUserForm" method="POST"
                                  action="${pageContext.request.contextPath}/ratingMovies?command=change-general-info&id=${sessionScope.get("userId")}"
                                  class="">
                                <div class="form-group">
                                    <label class="form-label">
                                        <fmt:message key="settings.change.name" />
                                    </label>
                                    <input type="text" name="name" minlength="4" maxlength="32" class="form-control"
                                           value="${user.getName()}" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        <fmt:message key="settings.change.email"/>
                                    </label>
                                    <input type="email"  name="email" minlength="4"
                                           maxlength="32" class="form-control" value="${user.getEmail()}"
                                           required>

                                    <label class="form-label">
                                        <fmt:message key="settings.change.telegram" />
                                    </label>
                                    <input type="text"  name="telegram" minlength="2"
                                           maxlength="16" class="form-control" value="${user.getTelegramAccount()}"
                                           required>
                                </div>

                            </form>
                            <h3 style="color: green">
                                <fmt:message key="settings.success.${successMessage}" />
                            </h3>
                            <h3 class="text-danger error-message text-centr">
                                <fmt:message key="settings.error.${errorMessage}" />
                            </h3>
                        </div>
                    </div>



                    <div class="tab-pane fade" id="account-change-password">
                        <div class="card-body pb-2">
                            <form id="changePasForm" name="changePasswordUserForm" method="POST"
                                  action="${pageContext.request.contextPath}/ratingMovies?command=change-pas&id=${sessionScope.get("userId")}">
                                <div class="form-group">
                                    <label class="form-label">
                                        <fmt:message key="settings.current.password" />
                                    </label>
                                    <input type="password" id="currentPass" name="currPas" minlength="8" maxlength="32"
                                           class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        <fmt:message key="settings.new.password" />
                                    </label>
                                    <input type="password" name="newPas1" minlength="8" maxlength="32"
                                           class="form-control password" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        <fmt:message key="settings.repeat.new.password" />

                                    </label>
                                    <input type="password" name = "newPas2" class="form-control password" minlength="8" maxlength="32"
                                           required>
                                </div>
                                <h3 class="text-danger error-message text-centr">
                                    <fmt:message key="settings.error.${errorMessage}" />
                                </h3>

                                <h3  style="color: green">
                                    <fmt:message key="settings.success.${successMessage}" />
                                </h3>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="text-right mt-3">
        <button type="submit" form="changeGeneralForm" class="btn btn-primary" id="btnSubmit">
            <fmt:message key="settings.save.changes" />
        </button>
        <a class="btn btn-default border border-secondary"
           href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${sessionScope.get("userId")}">
            <fmt:message key="settings.cancel" />
        </a>
    </div>

</div>
<script src="/js/account-settings.js"></script>
</body>

</html>