<%--
  Created by IntelliJ IDEA.
  User: rashid
  Date: 27.05.2018
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="!isAuthenticated()" >
    <section>
        <div class="container center">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading text-center">
                            <h2>Регистрация</h2>
                        </div>
                        <div class="panel-body">
                            <div class="col-md-12">
                                <form action="${pageContext.request.contextPath}/registration" method="post">
                                    <div class="form-group">
                                        <input type="text" placeholder="ФИО студента" name="fullName" title="Фамилия, имя, отчество студента"
                                               class="form-control"/><br>
                                        <input type="text" placeholder="Логин" name="login" title="Логин пользователя" class="form-control"/><br>
                                        <input type="password" placeholder="Пароль" name="password" title="Введите пароль"
                                               class="form-control"/><br>
                                        <input type="password" placeholder="Подтверждение пароля" name="confirmpassword" title="Подтвердите пароль"
                                               class="form-control"/><br>
                                        <input type="submit" name="btn_reg" title="Зарегистрировать пользователя" value="Зарегистрировать"
                                               class="btn btn-primary"/>
                                        <input type="reset" name="btn_clear" title="Очистить форму" value="Очистить" class="btn btn-secondary"/><br>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <%@include file="../containers/errorMessage.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </section>
</sec:authorize>