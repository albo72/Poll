<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>



<t:template_page tittle="Регистрация" main_activity="" login_activity="" registration_activity="active" polls_activity="" create_poll_activity="">
  <jsp:body>
    </div>
    <div class="container h-100">
      <div class="row h-100 justify-content-center align-items-center">
        <form class="row g-3" action="/register.do" method="post">

          <div class="col-md-12 text-center">
            <h1>Регистрация</h1>
          </div>

          <div class="col-md-12">
              <%--@declare id="login"--%><label for="login" class="form-label">Логин*</label>
            <input type="text" class="form-control" name="login" required>
          </div>

          <div class="col-md-12">
              <%--@declare id="password"--%><label for="password" class="form-label">Пароль*</label>
            <input type="password" class="form-control" name="password" required>
          </div>

          <div class="col-md-12">
              <%--@declare id="firstname"--%><label for="firstname" class="form-label">Имя</label>
            <input type="text" class="form-control" name="first_name">
          </div>

          <div class="col-md-12">
              <%--@declare id="lastname"--%><label for="lastname" class="form-label">Фамилия</label>
            <input type="text" class="form-control" name="last_name">
          </div>

          <div class="col-md-12">
              <%--@declare id="email"--%><label for="email" class="form-label">E-mail*</label>
            <input type="text" class="form-control" name="email" placeholder="example@mail.ru" required>
          </div>

          <div class="col-md-12">
            *-Обязательное поле
          </div>


          <div class="col-12">
            <button type="submit" class="btn btn-primary">Регистрация</button>
          </div>
        </form>
      </div>
    </div>
  </jsp:body>
</t:template_page>