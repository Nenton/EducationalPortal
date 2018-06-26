<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 13.05.2018
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-expand-lg bg-dark navbar-dark col-md-2">
    <div class="navbar-collapse collapse flex-column sticky-top">
        <ul class="navbar-nav d-lg-block float-right">
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Панель
                    управления</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/teachers">Преподаватели</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/students">Студенты</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/lessons">Расписание
                    занятий</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/studentgroup">Группы
                    студентов</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/journal">Журнал
                    успеваемости</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/users">Пользователи</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/groups">Группы
                    пользователей</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/roles">Роли</a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')">
            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/performance">Успеваемость</a>
                </sec:authorize>
        </ul>
        <br/>
        <sec:authorize access="isAuthenticated()">
            <ul  class="navbar-nav d-lg-block">
                <div class="logout">
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <input type="submit" name="exit" title="exit" value="Выход" class="btn btn-warning">
                    </form>
                </div>
            </ul>
        </sec:authorize>
    </div>
</div>
