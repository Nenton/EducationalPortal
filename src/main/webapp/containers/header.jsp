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
        <div class="row">
            <nav class="navbar navbar-expand-lg nav-tabs">

                <!-- Navbar brand -->
                <a class="navbar-brand">Система учета успеваемости</a>

                <div class="navbar-collapse" id="headMenu">
                    <!-- Links -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/info">Information</a>
                        </li>
                    </ul>
                    <!-- Links -->
                </div>
            </nav>
        </div>
    </div>
</header>
