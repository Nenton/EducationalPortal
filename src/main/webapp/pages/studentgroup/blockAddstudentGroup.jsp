<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="${pageContext.request.contextPath}/addstudentgroup" method="post" class="users-row">
    <p class="col-12">
        <label>Студент</label>
        <select name="studentnotgroup" class="col-6 form-control">
            <c:forEach var="user" items="${studentnotgroup}">
                <option value="${user.id}" ${user.id == student.Id ? 'selected="selected"' : ''}>${user.fullName}</option>
            </c:forEach>
        </select>
    </p>
    <p class="col-12">
        <label>Группа</label>
        <input type="text" placeholder="group" disabled value="${group.groupName}">
    </p>
    <input type="submit" name="createJournal" value="Создать" class="btn btn-success col-5 users-margin">
    <input hidden name="namegroup" value="${group.groupId}">
</form>
