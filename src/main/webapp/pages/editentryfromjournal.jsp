<%--
  Created by IntelliJ IDEA.
  User: Azat
  Date: 15.06.2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
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
        <jsp:include page="../containers/errorMessage.jsp"/>
        <jsp:include page="../pages/journals/blockGETentryfromjournal.jsp"/>
    </div>
</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>

