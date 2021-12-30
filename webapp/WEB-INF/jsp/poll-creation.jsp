
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="count_of_questions" value="${requestScope.countOfQuestions}"/>

<t:template_page tittle="" main_activity="" login_activity="" registration_activity="" polls_activity="" create_poll_activity="active">
    <jsp:body>
        <div class="container-md">
            <div class="row">
                <form class="row g-3" action="/createPoll.do" method="post" accept-charset="UTF-8">
                    <div class="col-sm-12"><br></div>
                    <div class="col-md-12 text-center">
                        <h1>Создание опроса</h1>
                    </div>

                    <div class="col-md-12 fs-4">
                        <%--@declare id="name"--%><label for="name" class="form-label">Название опроса:</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>

                    <div class="col-md-12 fs-4">
                        <%--@declare id="description"--%><label for="description" class="form-label">Описание:</label>
                        <input type="text" class="form-control" name="description" required>
                    </div>
                    <div class="col-md-12 fs-4">
                        <%--@declare id="date-end"--%><label for="date-end" class="form-label">Дата окончания:</label>
                        <input type="datetime-local" class="form-control" name="dateEnd" required>
                    </div>
                    <c:forEach items="${count_of_questions}" var="count">
                        <div class="col-md-12 fs-4">
                                <%--@declare id="question"--%><label for="question" class="form-label">
                                    Вопрос №${count}:
                                </label>
                            <input type="text" class="form-control" name="questions[]" required>
                        </div>
                    </c:forEach>
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary">Создать</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:template_page>