<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>
<%@include file="../containers/header.jsp" %>
<div class="row">
    <%@include file="../containers/sidebar.jsp" %>
    <div class="col-9">
        <c:if test="${message != null}">
            <%@include file="../containers/message.jsp" %>
        </c:if>
        <c:if test="${role == 1 && create != null}">
            <jsp:include page="../pages/users/blockAddTeacher.jsp"/>
        </c:if>
        <c:if test="${role == 1 && update != null}">
            <jsp:include page="../pages/users/blockEditTeacher.jsp"/>
        </c:if>
        <jsp:include page="../pages/users/blockTableTeachers.jsp"/>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>