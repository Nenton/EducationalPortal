<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-light">
    <tr class="users-row">
        <th class="col-2">Id</th>
        <th class="col-3">Имя</th>
        <th class="col-3">Описание</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr class="users-row">
            <td scope="row" class="col-2">${group.groupId}</td>
            <td class="col-3"><a
                    href="${pageContext.request.contextPath}/journal/${group.groupName}"> ${group.groupName}</a></td>
            <td class="col-3">${group.groupDesc}</td>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
