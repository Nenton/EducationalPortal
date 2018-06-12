<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${role == 1 || role == 3}">
    <form action="" name="createLesson" method="post" class="users-row">
        <p class="col-12">
            <input type="date" name="lessDate" value="${editLesson.date}" pattern="[0-9]{2}\.[0-9]{2}\.[0-9]{4}"
                   placeholder="Дата" class="form-control col-3" required/>
        </p>
        <p class="col-12">
            <select name="subject" class="col-6 form-control">
                <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.id}" ${subject.id == editLesson.subjectId ? 'selected="selected"' : ''}>${subject.name}</option>
                </c:forEach>
            </select>
        </p>
        <p class="col-12">
            <textarea name="theme" placeholder="Название темы" class="form-control col-6"
                      required maxlength="100">${editLesson.theme}</textarea>
        </p>
        <p class="col-12">
            <c:if test="${role == 1}">
                <select name="teacher" class="col-6 form-control">
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}" ${teacher.id == editLesson.teacherId ? 'selected="selected"' : ''}>${teacher.fullName}</option>
                    </c:forEach>
                </select>
            </c:if>

            <c:if test="${role == 3}">
                <select name="teacher" class="col-6 form-control">
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}" ${teacher.id == editLesson.teacherId ? 'selected="selected"' : ''}>${teacher.fullName}</option>
                    </c:forEach>
                </select>
            </c:if>
        </p>
        <p class="col-12">
            <select name="group" class="col-3 form-control">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.groupId}" ${group.groupId == editLesson.groupId ? 'selected="selected"' : ''}>${group.groupName}</option>
                </c:forEach>
            </select>
        </p>

        <p class="col-12">
            <c:if test="${editLesson != null}">
                <input type="submit" name="findLesson" value="Сохранить" class="btn btn-primary col-2"
                       formaction="${pageContext.request.contextPath}/lessons/save" formmethod="POST">
            </c:if>
            <c:if test="${editLesson == null}">
                <input type="submit" name="lessons/create" value="Создать" class="btn btn-success col-2"
                       formaction="${pageContext.request.contextPath}/lessons/add" formmethod="POST">
            </c:if>
            <input type="reset" name="clearLesson" value="Очистить" class="btn btn-secondary col-2">
        </p>
        <input hidden name="editLessonId" value="${editLesson.id}">
    </form>
</c:if>
<c:if test="${message != null}">
    <%@include file="../containers/message.jsp" %>
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
        <c:if test="${role == 1 || role == 3}">
            <th scope="col">Действия</th>
        </c:if>
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
            <c:if test="${role == 1 || role == 3}">
                <td>
                    <form>
                        <input type="submit" name="changeBtn" value="Изменить" class="btn btn-primary btn-sm"
                               formaction="${pageContext.request.contextPath}/lessons/change" formmethod="POST">
                        <input type="submit" name="deleteBtn" value="Удалить" class="btn btn-danger btn-sm"
                               formaction="${pageContext.request.contextPath}/lessons/delete" formmethod="POST">
                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>