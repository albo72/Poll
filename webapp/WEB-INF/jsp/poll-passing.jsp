<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="poll" value="${requestScope.poll}"/>
<c:set var="user_id" value="${sessionScope.user.id}"/>

<t:template_page tittle="Опрос:${poll.name}" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>

        <div class="container-sm">
            <div class="row">
                <form class="row g-3" action="/createAnswers.do?id=${poll.id}" method="post">
                    <div class="col-sm-12"><br><br></div>
                    <div class="col-sm-12">
                        <h1>${poll.name}</h1>
                    </div>
                    <div class="col-sm-12">
                        <i class="fs-3">Описание: ${poll.description}</i>
                        <br>
                    </div>
                    <c:forEach items="${poll.listOfQuestions}" var="question">
                        <div class="col-md-9 fs-4">
                                <%--@declare id="question"--%><label for="question" class="form-label">${question.questionName}</label>
                                    <input type="text" class="form-control" name="answers[]" required>
                        </div>
                        <div class="col-md-3"></div>
                    </c:forEach>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Пройти</button>
                    </div>
                </form>


            </div>
        </div>

    </jsp:body>

</t:template_page>