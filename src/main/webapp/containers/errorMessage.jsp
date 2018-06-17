<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message != null}">
    <div id="login_message" class="alert alert-danger">
        <p>${message}</p>
    </div>
</c:if>