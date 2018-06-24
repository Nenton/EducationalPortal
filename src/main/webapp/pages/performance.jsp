<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>
<%@include file="../containers/header.jsp" %>
<div class="row">
    <%@include file="../containers/sidebar.jsp" %>
    <div class="col-md-9">
        <form class="form-inline" action="${pageContext.request.contextPath}/performance" method="get">
            <div class="form-group">
                <label for="exampleSelect1">Название предмета </label>
                <select class="form-control" id="exampleSelect1" name="subjectId">
                    <c:forEach var="subject" items="${requestScope.get('subjects')}">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <!--Table of performance-->
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Предмет</th>
                <th scope="col">id занятия</th>
                <th scope="col">Тема занятия</th>
                <th scope="col">Дата занятия</th>
                <th scope="col">Оценка</th>
                <th scope="col">Дата оценки</th>
                <th scope="col">Посещение</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="perform" items="${requestScope.get('performance')}">
                <tr>
                    <th scope="row">${perform.subjectName}</th>
                    <td>${perform.lessonId}</td>
                    <td>${perform.lessonTheme}</td>
                    <td>${perform.lessonDate}</td>
                    <td>${perform.mark}</td>
                    <td>${perform.markDate}</td>
                    <td>${perform.attendance}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!--End table of sperformance-->


    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>
