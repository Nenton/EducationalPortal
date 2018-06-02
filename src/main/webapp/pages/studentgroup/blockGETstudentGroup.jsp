<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role == 1}">
    <form action="${pageContext.request.contextPath}/studentgroup/getstudentgroup"
          method="post"
          class="studentgroup-row">
        <input type="int" name="id" placeholder="Введите id группы" class="form-control col-3">
        <input type="submit" name="createStudent" value="Поиск"
               class="btn btn-success col-2 users-margin">
    </form>
</c:if>