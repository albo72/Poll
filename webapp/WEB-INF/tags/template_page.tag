<%@tag description="Tag generates template page" pageEncoding="UTF-8" %>>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="tittle" required="true" %>
<%@attribute name="main_activity" required="true" %>
<%@attribute name="login_activity" required="true" %>
<%@attribute name="registration_activity" required="true" %>
<%@attribute name="polls_activity" required="true" %>
<%@attribute name="create_poll_activity" required="true" %>


<c:set var="user_id" value="${sessionScope.user.id}"/>
<c:set var="user_name" value="${sessionScope.user.firstName}"/>
<c:set var="is_admin" value="${sessionScope.user.isAdmin}"/>



<html>
<head>
    <title>${tittle}</title>
    <meta  charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">

</head>
<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark text-white bg-dark">
    <div class="container">
        <div class="row">
            <div class="col-10">
                <div class="navbar-header">
                    <span href="#" class="navbar-brand">ALBO</span>
                </div>
            </div>
        </div>

        <div class="col-2 text-right">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link ${main_activity}" href="${pageContext.request.contextPath}/">Главная</a>
                </li>
                <c:if test="${is_admin == true}">
                    <li class="nav-item">
                        <a class="nav-link ${polls_activity}"   href="pollMenu.do">Опросы</a>
                    </li>
                </c:if>
                <c:if test="${is_admin == false or empty user_id}">
                    <li class="nav-item">
                        <a class="nav-link ${polls_activity}"   href="activePolls.do">Опросы</a>
                    </li>
                </c:if>
                <c:if test="${empty user_id}">
                    <li class="nav-item">
                        <a class="nav-link ${login_activity}" href="signIn.do">Вход</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${registration_activity}" href="registration.do?id=">Регистрация</a>
                    </li>
                </c:if>
                <c:if test="${not empty user_id}">

                    <c:if test="${empty user_name}">
                        <li class="nav-item">
                            <a class="nav-link" href="personalData.do">ID:${user_id}</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty user_name}">
                        <li class="nav-item">
                            <a class="nav-link" href="personalData.do">${user_name}</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="logout.do">Выйти</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<jsp:doBody/>

</body>

</html>
