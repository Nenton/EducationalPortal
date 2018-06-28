<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9">
    <div class="row">
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')">
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/teachers" class="nav-link">
                        <img src="img/teachers.png" alt="Преподаватели"/>
                        <div class="card-body">
                            <h5 class="card-title">Преподаватели</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/students" class="nav-link">
                        <img src="img/students.png" alt="Студенты"/>
                        <div class="card-body">
                            <h5 class="card-title">Студенты</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/lessons" class="nav-link">
                        <img src="img/lessons.png" alt="Расписание занятий"/>
                        <div class="card-body">
                            <h5 class="card-title">Расписание занятий</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/studentgroup" class="nav-link">
                        <img src="img/studentgroups.png" alt="Группы студентов"/>
                        <div class="card-body">
                            <h5 class="card-title">Группы студентов</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/journal" class="nav-link">
                        <img src="img/journal.png" alt="Журнал"/>
                        <div class="card-body">
                            <h5 class="card-title">Журнал успеваемости</h5>
                        </div>
                    </a>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/users" class="nav-link">
                        <img src="img/users.png" alt="Пользователи"/>
                        <div class="card-body">
                            <h5 class="card-title">Пользователи</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/groups" class="nav-link">
                        <img src="img/groups.png" alt="Группы"/>
                        <div class="card-body">
                            <h5 class="card-title">Группы</h5>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-3 mt-3">
                <div class="card text-center">
                    <a href="${pageContext.request.contextPath}/roles" class="nav-link">
                        <img src="img/roles.png" alt="Роли"/>
                        <div class="card-body">
                            <h5 class="card-title">Роли</h5>
                        </div>
                    </a>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>