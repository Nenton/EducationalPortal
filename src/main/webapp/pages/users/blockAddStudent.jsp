<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form action="${pageContext.request.contextPath}/studentCreate" name="addStudent" method="post"
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
</sec:authorize>