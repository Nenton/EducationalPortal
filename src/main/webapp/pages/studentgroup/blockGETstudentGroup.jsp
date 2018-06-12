<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered users-table">
    <thead class="table-light">
    <tr class="users-row">
        <th class="col-2">Id</th>
        <th class="col-3">Имя</th>
        <th class="col-3">Описание</th>
        <th class="col-3">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr class="users-row">
            <td scope="row" class="col-2">${group.groupId}</td>
            <td class="col-3"> ${group.groupName}</td>
            <td class="col-3">${group.groupDesc}</td>
            <td class="col-3">
                <form action="${pageContext.request.contextPath}/studentgroup/getstudentgroup" method="get"
                      class="users-row">
                    <input type="submit" value="Список студентов" class="btn btn-info">
                    <input hidden name="id" value="${group.groupId}">

                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>