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
            <div class="alert alert-info" role="alert">
                Список групп
            </div>
            <div class="alert alert-secondary" role="alert">
                <h class="text-center">
                    <p class="text-primary">Для просмотра предметов нажмите на имя группы</p>
                </h>
            </div>
            <jsp:include page="journals/blockGETgroupListForJournal.jsp"/>

        </main>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>