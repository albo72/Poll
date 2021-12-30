<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="list" value="${requestScope.polls}"/>
<c:set var="pages" value="${requestScope.listOfPages}"/>
<c:set var="currentPage" value="${requestScope.page}"/>

<t:template_page tittle="Опросы" main_activity="" login_activity="" registration_activity="" polls_activity="active" create_poll_activity="">

    <jsp:body>
        <div class="container-sm h-100">
            <div class="row">
                <div class="col-sm-12 row g-3"><br><br></div>
                <ul>
                    <c:forEach items="${list}" var="poll">
                        <li class="col-sm-12">
                            <a class="nav-link fs-2" href="poll.do?id=${poll.id}">${poll.name}</a>
                            <div>
                                <i class="fs-3">${poll.description}</i>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="fixed-bottom">
                <nav aria-label="...">
                    <ul class="pagination justify-content-center">
                        <c:forEach items="${pages}" var="page">
                            <c:choose>
                                <c:when test="${currentPage eq page}">
                                    <li class="page-item active" aria-current="page">
                                        <a class="page-link" href="${pageContext.request.contextPath}/allPolls.do?page=${page}">${page}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/allPolls.do?page=${page}">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>


    </jsp:body>

</t:template_page>