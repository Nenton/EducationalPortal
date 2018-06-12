<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${message != null}">
    <%@include file="../../containers/message.jsp" %>
</c:if>
<h3 class="accordion">Расписание занятий</h3>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr>
        <th scope="col">Дата</th>
        <th scope="col">Предмет</th>
        <th scope="col">Тема</th>
        <th scope="col">Преподаватель</th>
        <th scope="col">Группа</th>
        <c:if test="${role == 1 || role == 3}">
            <th scope="col">Действия</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lesson" items="${lessons}">
        <tr>
            <td>${lesson.date}</td>
            <td>${lesson.subject.name}</td>
            <td>${lesson.theme}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
            </td>
            <td>${lesson.group.groupName}</td>
            <c:if test="${role == 1 || role == 3}">
                <td>
                    <form>
                        <input type="submit" name="changeBtn" value="Изменить" class="btn btn-primary btn-sm"
                               formaction="${pageContext.request.contextPath}/lessons/change" formmethod="POST">
                        <input type="submit" name="deleteBtn" value="Удалить" class="btn btn-danger btn-sm"
                               formaction="${pageContext.request.contextPath}/lessons/delete" formmethod="POST">
                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>