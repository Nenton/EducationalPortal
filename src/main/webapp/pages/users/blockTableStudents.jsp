<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr class="users-row">
        <%--<th class="col-1">Id</th>--%>
        <th class="col-3">Login</th>
        <th class="col-3">Name</th>
        <th class="col-2">Role</th>
        <th class="col users-row">
            <form action="${pageContext.request.contextPath}/studentCreate" name="create" method="get">
                <input type="submit" name="create" value="Создать" class="btn btn-success">
            </form>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}">
        <tr class="users-row">
                <%--<th scope="row" class="col-1">${student.id}</th>--%>
            <td class="col-3">${student.login}</td>
            <td class="col-3">
                <a href="${pageContext.request.contextPath}/users/${student.id}"> ${student.fullName}</a>
            </td>
            <td class="col-2">${student.role.name}<br></td>
            <c:if test="${role == 1}">
                <td class="col users-row">
                    <form action="${pageContext.request.contextPath}/studentEdit/${student.id}" name="update"
                          method="get">
                        <input type="submit" name="update" value="Изменить" class="btn btn-warning">
                    </form>
                    <form action="${pageContext.request.contextPath}/studentDelete/${student.id}" name="delete"
                          class="users-margin"
                          method="post">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-danger">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
