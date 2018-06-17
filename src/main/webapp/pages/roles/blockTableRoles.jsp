<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="roles" items="${roles}">
        <tr>
            <th scope="row">${roles.id}</th>
            <td>${roles.name}</td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td>
                    <form action="${pageContext.request.contextPath}/roleDelete" method="post">
                        <input type="submit" value="Удалить" class="btn btn-primary">
                        <input hidden name="roleId" value="${roles.id}">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>