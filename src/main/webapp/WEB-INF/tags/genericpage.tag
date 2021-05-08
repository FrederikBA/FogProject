<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta name="theme-color" content="#7952b3">
</head>
<body>
<!--
This header is inspired by this bootstrap
example: https://getbootstrap.com/docs/5.0/examples/pricing/
-->
<header>
    <div class="container helvetica">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <a href="${pageContext.request.contextPath}/fc/index">
                        <li class="nav-item active">Forside</li>
                    </a>
                    <c:if test="${sessionScope.user.role.equals('employee')}">
                        <a href="${pageContext.request.contextPath}/fc/admincustomerpage">
                            <li class="nav-item active">Kunder</li>
                        </a>
                        <a href="${pageContext.request.contextPath}/fc/adminorderpage">
                            <li class="nav-item active">Ordrer</li>
                        </a>
                        <a href="${pageContext.request.contextPath}/fc/adminproducts">
                            <li class="nav-item active">Produkter</li>
                        </a>
                    </c:if>
                </ul>
                <div class="ms-auto">
                    <c:if test="${sessionScope.user != null }">
                        <p class="px-2 username right mx-2">${sessionScope.user.email}</p>
                    </c:if>
                    <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
                    <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
                    <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

                    <c:if test="${isNotLoginPage && isNotRegisterPage}">
                        <c:if test="${sessionScope.user != null }">
                            <a type="button" class="btn btn-outline-light mx-2 right"
                               href="${pageContext.request.contextPath}/fc/logoutcommand">Logout</a>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <a type="button" class="btn btn-outline-light mx-2 right"
                               href="${pageContext.request.contextPath}/fc/loginpage">Login</a>
                            <a type="button" class="btn btn-outline-light mx-2 right"
                               href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
</header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
    <hr>
    <br>
    <jsp:invoke fragment="footer"/>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
