<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template_page tittle="Создание опроса" main_activity="" login_activity="" registration_activity="" polls_activity="" create_poll_activity="active">
    <jsp:body>
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <form class="form-inline" action="/pollCreationForm.do" method="post">

                    <div class="col-md-12 text-center">
                        <h1>Количество вопросов</h1>
                    </div>

                    <div class="col-md-12">
                        <label class="my-1 mr-2 fs-3" for="inlineFormCustomSelectPref">
                            Выберите необходимое количество вопросов:
                        </label>
                    </div>

                    <div class="col-md-12 d-flex">
                        <select class="form-control" id="inlineFormCustomSelectPref" name="countOfQuestions">
                            <option selected disabled>Выберите...</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                        </select>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary my-1">Подтвердить</button>
                    </div>


                </form>
            </div>
        </div>
    </jsp:body>
</t:template_page>