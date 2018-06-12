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
            <jsp:include page="../pages/groups/blockAddGroup.jsp"/>
            <jsp:include page="../pages/groups/blockTableGroups.jsp"/>
        </div>
    </div>
    <%@include file="../containers/footer.jsp" %>
    </body>
</html>
