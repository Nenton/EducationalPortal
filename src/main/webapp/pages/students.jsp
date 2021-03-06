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
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:if test="${create != null}">
                <jsp:include page="../pages/users/blockAddStudent.jsp"/>
            </c:if>
            <c:if test="${update != null}">
                <jsp:include page="../pages/users/blockEditStudent.jsp"/>
            </c:if>
        </sec:authorize>
        <jsp:include page="../pages/users/blockTableStudents.jsp"/>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>