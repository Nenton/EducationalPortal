<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/userCreate" name="addUser" method="post" class="users-row">
    <input type="text" name="nameUser" title="nameUser" placeholder="Имя"
           class="form-control col-3">
    <input type="text" name="loginUser" title="loginUser" placeholder="Логин"
           class="form-control col-2 users-margin">
    <input type="password" name="passwordUser" title="passwordUser"
           placeholder="Пароль"
           class="form-control col users-margin">
    <select name="role" class="form-control col-2 users-margin">
        <c:forEach var="role" items="${roles}">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select>
    <input type="submit" title="createUser" name="createUser" value="Создать"
           class="btn btn-success col-2 users-margin">
</form>
