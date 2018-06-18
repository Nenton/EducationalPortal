<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 03.06.2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form action="${pageContext.request.contextPath}/journal/add" name="addGroup" method="post" class="form-row">
        <p class="col-12">
            <label for="sel2">ФИО Студента</label>
            <select name="student" class="col-5 form-control">
                <c:forEach var="user" items="${group}">
                    <option value="${user.id}" ${user.id == student.Id ? 'selected="selected"' : ''}>${user.fullName}</option>
                </c:forEach>
            </select>
        </p>
        <br>
        <p class="col-12">
            <label for="sel2">Лекции</label>
            <select name="lesson" class="col-5 form-control">
                <c:forEach var="lesson" items="${subject}">
                    <option value="${lesson.id}" ${lesson.id == student.lesson ? 'selected="selected"' : ''}>${lesson.theme}</option>
                </c:forEach>
            </select>
        </p>
        <br>
        <p class="col-12">
            <label for="sel2">Дата лекции</label>
            <input type="date" name="markDate" placeholder="Дата лекции" class="form-control col-5" required
                   maxlength="10">
        </p>
        <br>
        <p class="col-12">
            <label for="sel2">Оценка</label>
            <select name="mark" class="form-control col-5">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </p>
        <p class="col-12">
            <label for="sel2">Посещаемость</label>
            <select name="attendance" class="form-control col-5 form-row" id="sel2">
                <option value="1">1</option>
                <option value="0">0</option>
            </select>
        </p>
        <input type="submit" name="createJournal" value="Создать" class="btn btn-success col-5 users-margin">
        <c:forEach var="user" items="${group}" end="0">
            <input hidden name="namegroup" value="${userforjournal.groupsName}">
        </c:forEach>
        <c:forEach var="lesson" items="${subject}" end="0">
            <input hidden name="namesubject" value="${lesson.namesubject}">
        </c:forEach>
    </form>
</sec:authorize>
