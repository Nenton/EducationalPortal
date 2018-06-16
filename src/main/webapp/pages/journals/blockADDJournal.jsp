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
        <input type="text" name="studentId" placeholder="Номер студента" class="form-control col-2 users-row">
        <br/>
        <input type="text" name="lessonId" placeholder="Номер лекции" class="form-control col-2 users-row">
        <br/>
        <input type="date" name="markDate" placeholder="Дата лекции" class="form-control col-2 users-row">
        <br/>
        <div class="form-group">
            <label for="sel">Оценка</label>
            <select name="mark" class="form-control" id="sel">
                <option>5</option>
                <option>4</option>
                <option>3</option>
                <option>2</option>
            </select>
        </div>
        <div class="form-group">
            <label for="sel2">Посещаемость</label>
            <select name="attendance" class="form-control" id="sel2">
                <option>1</option>
                <option>0</option>
            </select>
        </div>
        <input type="submit" name="createJournal" value="Создать" class="btn btn-success col-2 users-margin">
    </form>
</sec:authorize>
