<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9">
    <table class="table table-responsive">
        <thead class="table-dark table-bordered">
        <tr>
            <th class="d-sm-table-cell" scope="col">Id</th>
            <th class="d-sm-table-cell" scope="col">Theme</th>
            <th class="d-sm-table-cell" scope="col">Subject</th>
            <th class="d-sm-table-cell" scope="col">Teacher</th>
            <th class="d-sm-table-cell" scope="col">Group</th>
            <th class="d-sm-table-cell" scope="col">Date</th>
        </tr>
        </thead>
        <tbody class="table-bordered">
        <c:forEach var="lesson" items="${lessons}">
            <tr>
                <th scope="row">${lesson.id}</th>
                <td class="d-sm-table-cell" >${lesson.theme}</td>
                <td class="d-sm-table-cell" >${lesson.subject.name}</td>
                <td class="d-sm-table-cell" >
                    <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
                </td>
                <td class="d-sm-table-cell" >${lesson.group.groupName}</td>
                <td class="d-sm-table-cell" >${lesson.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>