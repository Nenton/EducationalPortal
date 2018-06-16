<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form action="${pageContext.request.contextPath}/teacherCreate" name="addTeacher" method="post"
          class="users-row">
        <input type="text" name="nameTeacher" placeholder="Имя" class="form-control col-3">
        <input type="text" name="loginTeacher" placeholder="Логин"
               class="form-control col-3 users-margin">
        <input type="password" name="passwordTeacher" placeholder="Пароль"
               class="form-control col users-margin">
        <input type="hidden" name="roleTeacher" value="${createRole}">
        <input type="submit" name="addTeacher" value="Создать"
               class="btn btn-success col-2 users-margin">
    </form>
</sec:authorize>