<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Subject</th>
        <th scope="col">Teacher</th>
        <th scope="col">Student</th>
        <th scope="col">Mark</th>
        <th scope="col">Attendance</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lesson" items="${lessons}">
        <tr>
            <th scope="row">${lesson.id}</th>
            <td>${lesson.subject.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${lesson.student.id}"> ${lesson.student.fullName}</a>
            </td>
            <td>${lesson.mark}</td>
            <td>${lesson.attendance}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>