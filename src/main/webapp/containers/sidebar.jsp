<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 13.05.2018
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-3">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <br>
    <c:if test="${role != null}">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a><br />
        <a href="${pageContext.request.contextPath}/teachers">Teachers</a><br />
        <a href="${pageContext.request.contextPath}/students">Students</a><br />
        <a href="${pageContext.request.contextPath}/lessons">Lessons</a><br />
        <a href="${pageContext.request.contextPath}/studentgroup">Studentgroup</a><br />
    </c:if>
    <c:if test="${role == 1}">
        <a href="${pageContext.request.contextPath}/users">Users</a><br />
        <a href="${pageContext.request.contextPath}/groups">Groups</a><br />
        <a href="${pageContext.request.contextPath}/roles">Roles</a><br />
    </c:if>

    <a href="${pageContext.request.contextPath}/info">Information</a><br />

    <br><br>
    <c:if test="${login != null}">
        <div class="logout">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="submit" name="exit" title="exit" value="Выход" class="btn btn-warning">
            </form>
        </div>
    </c:if>
    <%@include file="../containers/login.jsp" %>
</div>
