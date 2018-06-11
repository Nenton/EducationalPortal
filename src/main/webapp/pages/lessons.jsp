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
                    <%@include file="../containers/tableLessons.jsp" %>
                </main><!-- .content -->
            </div><!-- .container-->
        </div>
        <%@include file="../containers/footer.jsp" %>
    </body>
</html>