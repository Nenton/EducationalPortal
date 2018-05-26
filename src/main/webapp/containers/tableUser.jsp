<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role.id == 1}">
    <form action="${pageContext.request.contextPath}${path}" name="addUser" method="post" class="row">
        <input type="text" name="nameUser" title="nameUser" value="Имя пользователя" class="form-control col-2">
        <input type="text" name="loginUser" title="loginUser" value="Логин пользователя" class="form-control col-2">
        <input type="text" name="passwordUser" title="passwordUser" value="Пароль пользователя"
               class="form-control col-2">
        <c:if test="${createRole == null}">
            <label>
                <select name="role" class="form-control">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.role}</option>
                    </c:forEach>
                </select>
            </label>
        </c:if>
        <c:if test="${createRole != null}">
            <input type="hidden" title="roleIdUser" name="roleIdUser" value="${createRole}">
        </c:if>
        <input type="submit" title="createUser" name="createUser" value="Создать" class="btn btn-success">
    </form>
</c:if>
<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Login</th>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <th scope="row">${user.id}</th>
            <td>${user.login}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}"> ${user.fullName}</a>
            </td>
            <td>${user.role.role}<br></td>
            <c:if test="${role.id == 1}">
                <td>
                    <form action="${pageContext.request.contextPath}${path}" name="deleteUser" method="post">
                        <input type="submit" title="deleteBtn" name="deleteBtn" value="Удалить" class="btn btn-primary">
                        <input hidden name="userId" value="${user.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>