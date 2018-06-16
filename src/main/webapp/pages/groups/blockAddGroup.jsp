<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form action="${pageContext.request.contextPath}/groups/create" name="addGroup" method="post" class="users-row">
        <input type="text" name="nameGroup" placeholder="Имя" class="form-control col-3">
        <input type="text" name="descriptionGroup" placeholder="Описание" class="form-control col-3 users-margin">
        <input type="submit" name="createGroup" value="Создать" class="btn btn-success col-3 users-margin">
    </form>
</sec:authorize>
