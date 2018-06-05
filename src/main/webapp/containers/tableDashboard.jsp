<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </tr>
    </c:forEach>
    </tbody>
</table>