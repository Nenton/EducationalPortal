<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 13.05.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="tabs">
    <div class="container">
        <div class="row text-white">
            <!-- Navbar brand -->
            <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/dashboard"><img
                    src="img/logo.png"/></a>
            <h1 class="mt-5">Система учета успеваемости</h1>
        </div>
        <div class="row text-white">
            <nav class="navbar navbar-expand-lg nav-tabs justify-content-end">
                <div class="navbar-collapse" id="headMenu">
                    <!-- Links -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Главная</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/info">О проекте</a>
                        </li>
                    </ul>
                    <!-- Links -->
                </div>
            </nav>
        </div>
    </div>
</header>
