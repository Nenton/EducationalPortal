<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>

<div class="container">

    <%@include file="../containers/header.jsp" %>

    <div class="row">
        <%@include file="../containers/sidebar.jsp" %>
        <div class="col-9">
            <main class="content">
                <c:if test="${message != null}">
                    <%@include file="../containers/message.jsp" %>
                </c:if>
                <c:if test="${role == 1}">
                    <%@include file="../pages/roles/blockAddRole.jsp" %>
                </c:if>

                <%@include file="../pages/roles/blockTableRoles.jsp" %>
            </main><!-- .content -->
        </div><!-- .container-->

    </div><!-- .middle-->

</div><!-- .wrapper -->
<%@include file="../containers/footer.jsp" %>
</body>
</html>
