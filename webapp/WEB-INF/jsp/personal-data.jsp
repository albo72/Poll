<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="user" value="${sessionScope.user}"/>

<t:template_page tittle="" main_activity="" login_activity="" registration_activity="" polls_activity="" create_poll_activity="">

    <jsp:body>
        <div class="container-sm">
            <div class="row">
                <div class="col-sm-12"><br><br></div>
                <div class="col-sm-12">
                    <h1>Личный кабинет</h1>
                </div>
                <div class="col-sm-12">
                    <i class="fs-3">Логин:</i>
                </div>
                <div class="col-sm-12 fs-4">
                    ${user.login}
                </div>
                <br><br>
                <div class="col-sm-12">
                    <i class="fs-3">Имя:</i>
                </div>
                <div class="col-sm-12 fs-4">
                        ${user.firstName}
                </div>
                <br><br>
                <div class="col-sm-12">
                    <i class="fs-3">Фамилия:</i>
                </div>
                <div class="col-sm-12 fs-4">
                        ${user.lastName}
                </div>
                <br><br>
                <div class="col-sm-12">
                    <i class="fs-3">E-mail:</i>
                </div>
                <div class="col-sm-12 fs-4">
                        ${user.email}
                </div>
                <div class="row g-1"></div>
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/personalDataEditing.do"
                       class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                       value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/personalDataEditing.do'">
                        Редактировать
                    </a>
                </div>
            </div>
        </div>
    </jsp:body>

</t:template_page>