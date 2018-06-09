<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/studentgroup/add" method="post" class="users-row">
    <input type="text" name="studentId" title="studentId" placeholder="Номер студента"
           class="form-control col-4 users-margin">
    <input type="tesxt" name="groupId" title="groupId" placeholder="Номер группы"
           class="form-control col-4 users-margin">
    <input type="submit" title="createUser" name="createUser" value="Добавить"
           class="btn btn-success col-3 users-margin">
</form>
