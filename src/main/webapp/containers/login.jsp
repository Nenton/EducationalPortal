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
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <div class="container center">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-login">
                    <div class="panel-heading">
                        <%--<p><%=request.getAttribute("message")%></p>--%>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form id="login-form" action="${pageContext.request.contextPath}/login" method="post" <%--role="form" style="display: block;"--%>>
                                    <div class="form-group">
                                        <input type="text" name="login" tabindex="1" class="form-control" placeholder="Username" value="">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" tabindex="2" class="form-control" placeholder="Password">
                                    </div>
                                    <%--<div class="form-group text-center">
                                        <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                        <label for="remember"> Remember Me</label>
                                    </div>--%>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-primary center" value="Log In">
                                                <input type="submit" name="reg" class="btn btn-secondary" value="Регистрация" formaction="registration" formmethod="GET"/>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>