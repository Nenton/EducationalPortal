<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <%@include file="../containers/head.jsp" %>
    <body>
        <%@include file="../containers/header.jsp" %>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')">
            <div class="row">
                <%@include file="../containers/sidebar.jsp" %>
            </div>
        </sec:authorize>
        <sec:authorize access="!hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')">
            <section class="aboutus bg-dark text-white py-5" id="aboutus">
                <div class="container py-5 my-5">
                    <div class="row">
                        <div class="col-md-8 col-sm-6 col-xs-12 ">
                            <div class="pb-3"></div>
                            <h2>Welcome to education portal</h2>
                            <div class="py-2"></div>
                            <p>Boost your career with HomeEducation. Simple and easy!</p>
                            <div class="py-2"></div>
                            <a href="login"><button href="login" name="login" id="login" class="btn btn-outline-light">Log In</button></a>
                            <a href="registration"><button href="registration" name="registration" id="registration" class="btn btn-outline-light">Sign In</button></a>
                            <div class="py-2"></div>
                        </div>
                    </div>
                </div>
            </section>
        </sec:authorize>
        <%@include file="../containers/footer.jsp" %>
    </body>
</html>
