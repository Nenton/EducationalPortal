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
                <%@include file="lessons/blockEdit.jsp" %>
                <%@include file="lessons/tableLessons.jsp" %>
            </main><!-- .content -->
        </div><!-- .container-->

    </div><!-- .middle-->
<%@include file="../containers/footer.jsp" %>
</body>
</html>