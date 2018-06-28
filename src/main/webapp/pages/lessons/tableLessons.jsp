<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${message != null}">
    <%@include file="../../containers/message.jsp" %>
</c:if>
<h3 class="accordion">Расписание занятий</h3>
<table class="table table-bordered users-table">
    <thead class="table-dark">
    <tr>
        <th scope="col">Дата</th>
        <th scope="col">Предмет</th>
        <th scope="col">Тема</th>
        <th scope="col">Преподаватель</th>
        <th scope="col">Группа</th>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <th scope="col">Действия</th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lesson" items="${lessons}">
        <tr>
            <td>${lesson.date}</td>
            <td>${lesson.subject.name}</td>
            <td>${lesson.theme}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
            </td>
            <td>${lesson.group.groupName}</td>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                <td>
                    <form>
                        <button type="submit" name="changeBtn" title="Изменить" class="btn btn-primary btn-sm"
                                formaction="${pageContext.request.contextPath}/lessons/change" formmethod="POST">
                            <i class="fa fa-pencil-square-o fa-lg"></i>
                        </button>
                        <button type="submit" name="deleteBtn" title="Удалить" class="btn btn-danger btn-sm"
                                formaction="${pageContext.request.contextPath}/lessons/delete" formmethod="POST">
                            <i class="fa fa-times fa-lg"></i>
                        </button>

                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>