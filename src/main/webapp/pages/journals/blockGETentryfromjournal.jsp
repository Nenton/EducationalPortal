<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 15.06.2018
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="${pageContext.request.contextPath}/journal/update" name="update" method="post"
      class="users-row" required maxlength="100">
    <p class="col-12">
        <label for="1"> Студент </label>
        <input type="text" name="StudentName" placeholder="Студент" value="${userforjournal.fullname}"
               class="form-control col-5" id="1" required>
    </p>
    <p class="col-12">
        <label for="2">Предмет</label>
        <input type="text" name="SubjectName" placeholder="Предмет" value="${userforjournal.name}"
               class="form-control col-5" id="2" required>
    </p>
    <p class="col-12">
        <label>Тема</label>
        <input type="text" name="SubjectTheme" placeholder="Тема" value="${userforjournal.theme}"
               class="form-control col-5" required>
    </p>

    <p class="col-12">
        <label>Дата</label>
        <input type="text" name="datemark" placeholder="Тема" value="${userforjournal.mark_date}"
               class="form-control col-5" required>
    </p>
    <p class="col-12">
        <label> Успеваемость </label>
        <input type="text" name="mark" placeholder="Дата" value="${userforjournal.mark}"
               class="form-control col-5" required>
    </p>
    <p class="col-12">
        <label> Посещаемость</label>
        <input type="text" name="attendance" placeholder="Успеваемость" value="${userforjournal.attendance}"
               class="form-control col-5   require">
    </p>
    <p class="col-12">
        <input type="submit" name="editUser" value="Сохранить"
               class="btn btn-success col-2">
        <input hidden name="journalid" value="${userforjournal.idJournal}">
        <input hidden name="studentid" value="${userforjournal.id}">
        <input hidden name="lessonid" value="${userforjournal.idLesson}">
        <input hidden name="subjectname" value="${userforjournal.name}">
        <input hidden name="groupname" value="${userforjournal.namegroup}">
    </p>
</form>

