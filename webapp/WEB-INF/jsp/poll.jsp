<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="poll" value="${requestScope.poll}"/>
<c:set var="user_id" value="${sessionScope.user.id}"/>
<c:set var="user_is_admin" value="${sessionScope.user.isAdmin}"/>
<c:set var="count" value="${requestScope.count}"/>


<t:template_page tittle="Опрос:${poll.name}" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>

        <div class="container-sm">
            <div class="row">
                <div class="col-sm-12"><br><br></div>
                <div class="col-sm-12">
                    <h1>${poll.name}</h1>
                </div>
                <div class="col-sm-12">
                    <i class="fs-3">Описание: ${poll.description}</i>
                </div>
                <br><br>
                <c:forEach items="${count}" var="count">
                    <div class="col-sm-12 fs-3">Вопрос №${count}</div>
                    <div class="col-sm-12 fs-4">${poll.listOfQuestions.get(count-1).questionName}</div>
                    <br>
                </c:forEach>
                <br><br>
                <c:if test="${empty user_id}">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/anonymousPoll.do?id=${poll.id}"
                           class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/anonymousPoll.do?id=${poll.id}'">
                            Пройти анонимно
                        </a>
                    </div>
                    <div class="row g-1"></div>
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/signIn.do"
                           class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/signIn.do'">
                            Авторизироваться
                        </a>
                    </div>
                </c:if>
                <c:if test="${not empty user_id}">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/pollPassing.do?id=${poll.id}"
                           class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/pollPassing.do?id=${poll.id}'">
                            Пройти
                        </a>
                    </div>
                    <div class="row g-1"></div>
                </c:if>
                <c:if test="${user_is_admin == true}">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/questionsForListOfAnswers.do?id=${poll.id}"
                           class="btn bg-gradient btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/usersForAnswers.do?id=${poll.id}'">
                            Посмотреть ответы
                        </a>
                    </div>
                    <div class="row g-1"></div>
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/deletePoll.do?id=${poll.id}}"
                           class="btn bg-gradient btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true"
                           value="Input Button" onclick="location.href = '${pageContext.request.contextPath}/deletePoll.do?id=${poll.id}'">
                            Удалить опрос
                        </a>
                    </div>
                </c:if>
            </div>
        </div>

    </jsp:body>

</t:template_page>