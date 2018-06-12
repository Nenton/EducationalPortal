<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 19.05.2018
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${login == null}">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="userName" title="user" class="form-control"/><br>
        <input type="password" name="userPassword" title="password" class="form-control"/><br>
        <input type="submit" name="login" title="Login" value="Войти" class="btn btn-primary"/>
        <input type="submit" name="reg" class="btn btn-secondary" value="Регистрация" formaction="registration"
               formmethod="GET"/>
    </form>
</c:if>