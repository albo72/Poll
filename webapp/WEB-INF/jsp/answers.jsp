<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="answers" value="${requestScope.answers}"/>

<t:template_page tittle="Опросы" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>
        <div class="container-sm">
            <div class="row">
                <div class="col-sm-12 row g-3"><br><br></div>
                <c:forEach items="${answers}" var="entry">
                    <div class="col-sm-12 fs-3">${entry.key.questionName}</div>
                    <c:forEach items="${entry.value}" var="value">
                        <div class="row g-1"></div>
                        <div class="col-sm-6 fs-3">${value.answer}</div>
                        <div class="col-sm-6 fs-3">Дата ответа:${value.date}</div>
                        <div class="row g-1"></div>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </jsp:body>

</t:template_page>