<%--
  Created by IntelliJ IDEA.
  User: rashid
  Date: 27.05.2018
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${login == null}">
    <div class="container center">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-login">
                    <div class="panel-heading">
                            <%--<p><%=request.getAttribute("message")%></p>--%>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form action="${pageContext.request.contextPath}/registration" method="post">
                                    <input type="text" placeholder="ФИО студента" name="fullName" title="Фамилия, имя, отчество студента"
                                           class="form-control"/><br>
                                    <input type="text" placeholder="Логин" name="login" title="Логин пользователя" class="form-control"/><br>
                                    <select name="uroles" class="form-control">
                                        <c:forEach var="role" items="${roles}">
                                            <option value="${role.id}">${role.name}</option>
                                        </c:forEach>
                                    </select><br>
                                    <input type="password" placeholder="Пароль" name="password" title="Введите пароль"
                                           class="form-control"/><br>
                                    <input type="password" placeholder="Подтверждение пароля" name="confirmpassword" title="Подтвердите пароль"
                                           class="form-control"/><br>
                                    <input type="submit" name="btn_reg" title="Зарегистрировать пользователя" value="Зарегистрировать"
                                           class="btn btn-primary"/>
                                    <input type="reset" name="btn_clear" title="Очистить форму" value="Очистить" class="btn btn-secondary"/><br>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>