<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 19.05.2018
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="!isAuthenticated()">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <section>
        <div class="container center">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <h2>Вход в систему</h2>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="/j_spring_security_check"
                                          method="post" <%--role="form" style="display: block;"--%>>
                                        <div class="form-group">
                                            <input type="text" name="j_username" tabindex="1" class="form-control"
                                                   placeholder="Username" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="j_password" tabindex="2" class="form-control"
                                                   placeholder="Password">
                                        </div>
                                        <div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" class="" name="_spring_security_remember_me" id="remember_me">
                                            <label for="remember_me"> Remember Me</label>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                           class="form-control btn btn-primary center" value="Log In">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%@include file="../containers/errorMessage.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </section>
</sec:authorize>