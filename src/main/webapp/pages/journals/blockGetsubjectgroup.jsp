<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 07.06.2018
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered">


    <thead>
    <tr>
        <th>Имя</th>
        <th>Действиe</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="subject" items="${subject}">
        <tr>
            <td scope="row"
                class=>${subject.name}</td>

            <td>
                <form action="${pageContext.request.contextPath}/journal/showjournal" method="post"
                      class="users-row">
                    <input type="submit" value="Посмореть журнал" class="btn btn-info">
                    <input hidden name="namegroup" value="${subject.namegroup}">
                    <input hidden name="namesubject" value="${subject.name}">
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
    <!--Table body-->

</table>


