<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/roleAdd" name="addRole" method="post" class="users-row">
    <input type="text" name="nameRole" placeholder="Название роли" class="form-control col-5">
    <input type="submit" value="Создать" class="btn btn-success  users-margin col-2">
</form>