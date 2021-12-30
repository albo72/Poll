<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="user" value="${sessionScope.user}"/>

<t:template_page tittle="" main_activity="" login_activity="" registration_activity="" polls_activity="" create_poll_activity="">

    <jsp:body>
        <div class="container-sm">
            <div class="row">
                <form class="row g-3" action="/editPersonalData.do" method="post">
                    <div class="col-sm-12"><br><br></div>
                    <div class="col-sm-12">
                        <h1>Личный кабинет</h1>
                    </div>
                    <div class="col-md-12 fs-3">
                            <%--@declare id="login"--%><label for="login" class="form-label">Логин:</label>
                        <input type="text" class="form-control" name="login" value="${user.login}" required>
                    </div>
                    <br><br>
                    <div class="col-md-12 fs-3">
                            <%--@declare id="password"--%><label for="password" class="form-label">Пароль:</label>
                        <input type="password" class="form-control" name="password" value="${user.password}" required>
                    </div>
                    <br><br>
                    <div class="col-md-12 fs-3">
                            <%--@declare id="firstname"--%><label for="firstName" class="form-label">Имя:</label>
                        <input type="text" class="form-control" name="first_name" value="${user.firstName}" required>
                    </div>
                    <br><br>
                    <div class="col-md-12 fs-3">
                            <%--@declare id="lastname"--%><label for="lastName" class="form-label">Фамилия:</label>
                        <input type="text" class="form-control" name="last_name" value="${user.lastName}" required>
                    </div>
                    <br><br>
                    <div class="col-md-12 fs-3">
                            <%--@declare id="email"--%><label for="email" class="form-label">E-mail:</label>
                        <input type="text" class="form-control" name="email" value="${user.email}" required>
                    </div>
                    <div class="row g-1"></div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Изменить</button>
                    </div>
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/personalData.do"
                           class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/personalData.do'">
                            Назад
                        </a>
                    </div>
                </form>


            </div>
        </div>
    </jsp:body>

</t:template_page>
