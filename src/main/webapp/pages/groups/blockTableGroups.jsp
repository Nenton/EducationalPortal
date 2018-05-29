<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr class="users-row">
        <th class="col-2">Id</th>
        <th class="col-3">Имя</th>
        <th class="col-4">Описание</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr class="users-row">
            <td scope="row" class="col-2">${group.groupId}</td>
            <td class="col-3"><a href="${pageContext.request.contextPath}/groups/${group.groupId}"> ${group.groupName}</a></td>
            <td class="col-4">${group.groupDesc}</td>
            <c:if test="${role == 1}">
                <td class="col-6 users-row">
                    <form action="${pageContext.request.contextPath}/groups" name="update" method="get">
                        <input type="submit" name="update" value="Изменить" class="btn btn-warning">
                        <input hidden name="groupId" value="${group.groupId}">
                    </form>
                    <form action="${pageContext.request.contextPath}/groups" name="delete" class="users-margin" method="post">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-primary">
                        <input hidden name="groupId" value="${group.groupId}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>

