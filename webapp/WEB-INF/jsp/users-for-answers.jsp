<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="poll_id" value="${requestScope.id}"/>
<c:set var="users" value="${requestScope.users}"/>


<t:template_page tittle="Ответы" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>

        <div class="container-sm">
            <div class="row">
                <div class="col-sm-12"><br><br></div>
                <div class="col-sm-12">
                    <h1>ID опроса:${poll_id}</h1>
                </div>
                <br><br>
                <div class="col-sm-12">
                    <i class="fs-3">Выберите нужного пользователя, чтобы посмотреть его ответы: </i>
                </div>
                <br><br>
                <c:forEach items="${users}" var="user">
                    <div class="col-sm-6 fs-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/showAnswers.do?user_id=${user.id}">id:${user.id}; login:${user.login}</a>
                    </div>
                    <br>
                </c:forEach>
                <br><br>
            </div>
        </div>

    </jsp:body>

</t:template_page>