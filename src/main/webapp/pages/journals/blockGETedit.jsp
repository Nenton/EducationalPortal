<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 15.06.2018
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<form action="${pageContext.request.contextPath}/journal/${user.id}" name="editUser" method="post"
      class="users-row">
    <label>Студент</label>
    <input type="text" name="StudentName" placeholder="Студент" value="${userforjournal.fullname}"
           class="form-control col-12 users-margin">
    <label>Предмет</label>
    <input type="text" name="SubjectName" placeholder="Предмет" value="${userforjournal.name}"
           class="form-control col-12 users-margin">
    <input type="text" name="SubjectTheme" placeholder="Предмет" value="${userforjournal.theme}"
           class="form-control col-12 users-margin">
    <input type="text" name="Date" placeholder="Дата" value="${userforjournal.mark_date}"
           class="form-control col-12 users-margin">
    <input type="text" name="Mark" placeholder="Посещаемость" value="${userforjournal.mark}"
           class="form-control col-12 users-margin">
    <input type="text" name="attendance" placeholder="Успеваемость" value="${userforjournal.attendance}"
           class="form-control col-12 users-margin">

    <input type="submit" name="editUser" value="Сохранить"
           class="btn btn-success col-2 users-margin">
</form>