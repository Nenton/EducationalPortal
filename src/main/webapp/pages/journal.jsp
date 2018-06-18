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
        <main class="content">
            <h3 class="accordion">Список групп</h3><br>

            <jsp:include page="journals/blockGETgroupListForJournal.jsp"/>

        </main>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>