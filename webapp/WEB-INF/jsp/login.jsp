<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="incorrect" value="${requestScope.incorrect}"/>

<t:template_page tittle="Вход" main_activity="" login_activity="active" registration_activity="" polls_activity="" create_poll_activity="">
    <jsp:body>
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">

                <form class="row g-3" action="/login.do" method="post">

                    <div class="col-md-12 text-center">
                        <h1>Вход</h1>
                    </div>

                    <div class="col-md-12">
                            <%--@declare id="login"--%><label for="login" class="form-label">Логин</label>
                        <input type="text" class="form-control" name="login" required>
                    </div>

                    <div class="col-md-12">
                            <%--@declare id="password"--%><label for="password" class="form-label">Пароль</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="col-md-12">
                        ${incorrect}
                    </div>
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary">Вход</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:template_page>


