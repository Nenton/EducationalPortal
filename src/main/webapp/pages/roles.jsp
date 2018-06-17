<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>
<%@include file="../containers/header.jsp" %>
<div class="row">
    <%@include file="../containers/sidebar.jsp" %>
    <div class="col-9">
        <main class="content">
            <c:if test="${message != null}">
                <%@include file="../containers/message.jsp" %>
            </c:if>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <%@include file="../pages/roles/blockAddRole.jsp" %>
            </sec:authorize>

            <%@include file="../pages/roles/blockTableRoles.jsp" %>
        </main><!-- .content -->
    </div><!-- .container-->
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>
