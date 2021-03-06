<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/studentEdit/${user.id}" name="editUser" method="post"
      class="users-row">
    <input type="text" name="nameStudent" placeholder="Имя" value="${user.fullName}"
           class="form-control col-3 users-margin">
    <input type="text" name="loginStudent" placeholder="Логин" value="${user.login}"
           class="form-control col-2 users-margin">
    <input type="password" name="passwordStudent" value="${user.passwordHash}"
           placeholder="Пароль"
           class="form-control col users-margin">
    <select name="roleStudent" class="form-control col-2 users-margin">
        <c:forEach var="role" items="${roles}">
            <option value="${role.id}"
                    <c:if test="${role.id == user.role.id}">selected</c:if>>${role.name}</option>
        </c:forEach>
    </select>
    <input type="submit" name="editUser" value="Сохранить"
           class="btn btn-success col-2 users-margin">
</form>
