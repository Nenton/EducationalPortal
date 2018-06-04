<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${role == 1 || role == 3}">
    <form action="${pageContext.request.contextPath}/lessonAdd" name="createLesson" method="post" class="users-row">
        <input type="text" name="theme" placeholder="Название темы" class="form-control col-2">
        <select name="subject" class="col-2 users-margin form-control">
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.id}">${subject.name}</option>
            </c:forEach>
        </select>
        <c:if test="${role == 1}">
            <select name="teacher" class="col-2 users-margin form-control">
                <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.id}">${teacher.fullName}</option>
                </c:forEach>
            </select>
        </c:if>
        <select name="group" class="col-2 users-margin form-control">
            <c:forEach var="group" items="${groups}">
                <option value="${group.groupId}">${group.groupName}</option>
            </c:forEach>
        </select>
        <input type="submit" name="createLesson" value="Создать" class="btn btn-success users-margin col-2">
    </form>
</c:if>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Theme</th>
        <th scope="col">Subject</th>
        <th scope="col">Teacher</th>
        <th scope="col">Group</th>
        <th scope="col">Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lesson" items="${lessons}">
        <tr>
            <th scope="row">${lesson.id}</th>
            <td>${lesson.theme}</td>
            <td>${lesson.subject.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
            </td>
            <td>${lesson.group.groupName}</td>
            <td>${lesson.date}</td>
            <c:if test="${role == 1 || role == 3}">
                <td>
                    <form action="${pageContext.request.contextPath}/lessonDelete" name="deleteLesson" method="post">
                        <input type="submit" name="deleteBtn" value="Удалить" class="btn btn-primary">
                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>