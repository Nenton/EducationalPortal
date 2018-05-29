<%--
  Created by IntelliJ IDEA.
  User: rashid
  Date: 27.05.2018
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../containers/head.jsp" %>
<title>Регистрация пользователя</title>
<body>

<div class="container">

    <%@include file="../containers/header.jsp" %>

    <div class="row">
        <%@include file="../containers/sidebar.jsp" %>
        <div class="col-9">
            <main class="content">
                <%@include file="../containers/errorMessage.jsp" %>
                <%@include file="../containers/registration.jsp" %>
                <br>
            </main>
        </div>

    </div>

</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>
