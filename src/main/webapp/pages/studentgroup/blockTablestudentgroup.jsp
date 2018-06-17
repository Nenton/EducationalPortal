<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table table-bordered studentgroup-table">
    <thead class="table-dark">
    <tr class="users-row">
        <th class="col-1">Id</th>
        <th class="col-2">fullname</th>
        <th class="col-2">idgroup</th>
        <th class="col-2">name</th>
        <th class="col-2">descr</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${studentgrop}">
        <tr class="users-row">
            <th scope="row" class="col-1">${user.id}</th>
            <td class="col-2">${user.fullName}</td>
            <td class="col-2">${user.groupIdGS}</td>
            <td class="col-2">${user.groupsName}</td>
            <td class="col-2">${user.groupsDesc}<br></td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td class="col users-row">
                    <form action="${pageContext.request.contextPath}/studentgroup/delete" method="post"
                          class="users-margin">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-danger">
                        <input hidden name="id" value="${user.id}">
                        <input hidden name="idgroup" value="${user.groupIdGS}">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>
