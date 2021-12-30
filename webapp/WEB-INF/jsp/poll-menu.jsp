<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="list" value="${requestScope.activePolls}"/>

<t:template_page tittle="Опросы" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>
        <div class="container-sm">
            <div class="row">
                <div class="col-sm-12 row g-3"><br><br></div>

                <div class="d-grid gap-2 col-4 mx-auto">
                    <a href="${pageContext.request.contextPath}/allPolls.do"
                       class="btn btn-primary btn-dark active" role="button" data-bs-toggle="button" aria-pressed="true"
                       value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/allPolls.do?page=1'">
                        Все опросы
                    </a>
                </div>
                <div class="d-grid gap-2 col-4 mx-auto">
                    <a href="${pageContext.request.contextPath}/activePolls.do"
                       class="btn btn-primary btn-dark active" role="button" data-bs-toggle="button" aria-pressed="true"
                       value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/activePolls.do'">
                        Активные опросы
                    </a>
                </div>
                <div class="d-grid gap-2 col-4 mx-auto">
                    <a href="${pageContext.request.contextPath}/inactivePolls.do"
                       class="btn btn-primary btn-dark active" role="button" data-bs-toggle="button" aria-pressed="true"
                       value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/inactivePolls.do'">
                        Неактивные опросы
                    </a>
                </div>

                <div class="row g-1"></div>
                <div class="row g-1"></div>

                <div class="d-grid gap-2 col-6 mx-auto text-center">
                    <a href="${pageContext.request.contextPath}/creationPoll.do"
                       class="btn btn-primary btn-dark active" role="button" data-bs-toggle="button" aria-pressed="true"
                       value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/creationPoll.do'">
                        Создать опрос
                    </a>
                </div>
            </div>
        </div>

    </jsp:body>

</t:template_page>