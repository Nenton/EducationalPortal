<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role == 1}">
    <form action="${pageContext.request.contextPath}/groups" name="addGroup" method="post" class="users-row">
        <input type="text" name="nameGroup" placeholder="Имя" class="form-control col-3">
        <input type="text" name="descriptionGroup" placeholder="Описание" class="form-control col-6 users-margin">
        <input type="submit" name="createGroup" value="Создать" class="btn btn-success col-2 users-margin">
    </form>
</c:if>
