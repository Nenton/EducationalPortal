<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr class="users-row">
        <th class="col-1">Id</th>
        <th class="col-2">Login</th>
        <th class="col-3">Name</th>
        <th class="col-2">Role</th>
        <th class="col users-row">
            <form action="${pageContext.request.contextPath}/users" name="create" method="get">
                <input type="submit" name="create" value="Создать" class="btn btn-success">
            </form>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${usersList}">
        <tr class="users-row">
            <th scope="row" class="col-1">${user.id}</th>
            <td class="col-2">${user.login}</td>
            <td class="col-3">
                <a href="${pageContext.request.contextPath}/users/${user.id}"> ${user.fullName}</a>
            </td>
            <td class="col-2">${user.role.name}<br></td>
            <c:if test="${role == 1}">
                <td class="col users-row">
                    <form action="${pageContext.request.contextPath}/users" name="update" method="get">
                        <input type="submit" name="update" value="Изменить" class="btn btn-warning">
                        <input hidden name="userId" value="${user.id}">
                    </form>
                    <form action="${pageContext.request.contextPath}/users" name="delete" method="post"
                          class="users-margin">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-danger">
                        <input hidden name="userId" value="${user.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
