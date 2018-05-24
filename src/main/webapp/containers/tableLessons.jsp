<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${role.id == 1 || role.id == 4}">
    <form action="${pageContext.request.contextPath}/lessons" name="createLesson" method="post" class="row">
        <label>
            <select name="subject" class="form-control">
                <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
        </label>
        <c:if test="${role.id == 1}">
            <label>
                <select name="teacher" class="form-control">
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}">${teacher.fullName}</option>
                    </c:forEach>
                </select>
            </label>
        </c:if>
        <label>
            <select name="student" class="form-control">
                <c:forEach var="student" items="${students}">
                    <option value="${student.id}">${student.fullName}</option>
                </c:forEach>
            </select>
        </label>
        <label>
            <select name="mark" class="form-control">
                <c:forEach var="mark" items="${marks}">
                    <option value="${mark}">${mark}</option>
                </c:forEach>
            </select>
        </label>

        <input type="checkbox" name="attendance" title="attendance" value="Attendance" class="form-check">
        <input type="submit" title="createLesson" name="createLesson" value="Создать" class="btn btn-success">
    </form>
</c:if>
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
            <c:if test="${role.id == 1 || role.id == 4}">
                <td>
                    <form action="${pageContext.request.contextPath}/lessons" name="deleteLesson" method="post">
                        <input type="submit" title="deleteBtn" name="deleteBtn" value="Удалить" class="btn btn-primary">
                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>