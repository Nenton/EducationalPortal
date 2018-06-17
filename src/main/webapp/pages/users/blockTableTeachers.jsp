<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr class="users-row">
        <%--<th class="col-1">Id</th>--%>
        <th class="col-3">Login</th>
        <th class="col-3">Name</th>
        <th class="col-2">Role</th>
        <th class="col users-row">
            <form action="${pageContext.request.contextPath}/teacherCreate" name="create" method="get">
                <input type="submit" name="create" value="Создать" class="btn btn-success">
            </form>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr class="users-row">
                <%--<th scope="row" class="col-1">${teacher.id}</th>--%>
            <td class="col-3">${teacher.login}</td>
            <td class="col-3">
                <a href="${pageContext.request.contextPath}/users/${teacher.id}"> ${teacher.fullName}</a>
            </td>
            <td class="col-2">${teacher.role.name}<br></td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td class="col users-row">
                    <form action="${pageContext.request.contextPath}/teacherEdit/${teacher.id}" name="update"
                          method="get">
                        <input type="submit" name="update" value="Изменить" class="btn btn-warning">
                    </form>
                    <form action="${pageContext.request.contextPath}/teacherDelete/${teacher.id}" name="delete"
                          class="users-margin"
                          method="post">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-danger">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>
