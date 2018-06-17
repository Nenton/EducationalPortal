<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr class="users-row">
        <th class="col-3">Id</th>
        <th class="col-3">Имя</th>
        <th class="col-3">Описание</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr class="users-row">
            <td scope="row" class="col-3">${group.groupId}</td>
            <td class="col-3"><a href="${pageContext.request.contextPath}/groups/${group.groupId}"> ${group.groupName}</a></td>
            <td class="col-3">${group.groupDesc}</td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td class="col-3 users-row">
                    <form action="${pageContext.request.contextPath}/groups/delete" name="delete" class="users-margin" method="post">
                        <input hidden name="groupId" value="${group.groupId}">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-primary">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

