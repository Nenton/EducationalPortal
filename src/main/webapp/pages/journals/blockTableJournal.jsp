<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 04.06.2018
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table table-bordered studenjournal-table">
    <thead class="table-dark">
    <tr class="users-row">
        <th class="col-2">Студент</th>
        <th class="col-2">Предмет</th>
        <th class="col-2">Тема</th>
        <th class="col-2">Дата</th>
        <th class="col-1">Посещ-сть</th>
        <th class="col-1">Успев-ость</th>
        <th class="col-2">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="userforjournal" items="${studentjournal}">
        <tr class="users-row">
            <th scope="row" class="col-2">${userforjournal.fullname}</th>
            <td class="col-2">${userforjournal.name}</td>
            <td class="col-2">${userforjournal.theme}</td>
            <td class="col-2">${userforjournal.mark_date}</td>
            <td class="col-1">${userforjournal.mark}</td>
            <td class="col-1">${userforjournal.attendance}
            </td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td class="col-2 users-row">
                    <form action="${pageContext.request.contextPath}/journal/delete" method="post"
                          class="users-margin">
                        <input type="submit" name="delete" value="Удалить" class="btn btn-danger">
                        <input hidden name="id" value="${userforjournal.id}">
                        <input hidden name="subject" value="${userforjournal.name}">
                        <input hidden name="group" value="${userforjournal.namegroup}">
                    </form>
                    <form action="${pageContext.request.contextPath}/journal/update" name="update" method="get">
                        <input type="submit" name="update" value="Изменить" class="btn btn-warning">
                        <input hidden name="userId" value="${userforjournal.id}">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

</table>