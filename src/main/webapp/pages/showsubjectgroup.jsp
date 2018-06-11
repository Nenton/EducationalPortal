<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>
<div class="container">
    <%@include file="../containers/header.jsp" %>
    <div class="row">
        <%@include file="../containers/sidebar.jsp" %>
        <div class="col-9">
            <div class="alert alert-info" role="alert">
                Список предметов
            </div>
            <main class="content">
                <c:if test="${message != null}">
                    <%@include file="../containers/message.jsp" %>
                </c:if>
                <jsp:include page="journals/blockGetsubjectgroup.jsp"/>
            </main>
        </div>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>