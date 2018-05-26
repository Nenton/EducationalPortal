<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role.id == 1}">
    <form action="${pageContext.request.contextPath}/students" name="addStudent" method="post"
          class="users-row">
        <input type="text" name="nameStudent" placeholder="Имя" class="form-control col-3">
        <input type="text" name="loginStudent" placeholder="Логин"
               class="form-control col-3 users-margin">
        <input type="password" name="passwordStudent" placeholder="Пароль"
               class="form-control col users-margin">
        <input type="hidden" name="roleStudent" value="${createRole}">
        <input type="submit" name="createStudent" value="Создать"
               class="btn btn-success col-2 users-margin">
    </form>
</c:if>