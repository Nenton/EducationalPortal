<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role.id == 1}">
    <form action="${pageContext.request.contextPath}/roles" name="addRole" method="post" class="row">
        <div class="col-3">
            <input type="text" name="nameRole" title="nameRole" value="Название роли" class="form-control">
        </div>
        <div class="col-1">
            <input type="submit" title="addRole" name="addRole" value="Создать" class="btn btn-success">
        </div>
    </form>
</c:if>
<table class="table table-bordered">
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
            <td>${roles.role}</td>
            <c:if test="${role.id == 1}">
                <td>
                    <form action="${pageContext.request.contextPath}/roles" name="deleteRole" method="post">
                        <input type="submit" title="deleteRole" name="deleteRole" value="Удалить"
                               class="btn btn-primary">
                        <input hidden name="roleId" value="${roles.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>