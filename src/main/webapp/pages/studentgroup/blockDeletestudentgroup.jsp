<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role == 1}">
    <form action="${pageContext.request.contextPath}/studentgroup/delete"
          method="post"
          class="users-row">
        <input type="text" name="idstudent" placeholder="Номер студента"
               class="form-control col-3 users-margin">
        <input type="submit" name="createStudent" value="Удалить"
               class="btn btn-success col-2 users-margin">
    </form>
</c:if>