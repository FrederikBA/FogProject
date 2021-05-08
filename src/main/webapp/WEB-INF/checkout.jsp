<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">

        </div>
        <div class="col-md center">


            <h1>Din ordre er registreret.</h1>
            <br> <br><br> <br>

            <h3>Din valgte Bredde: ${sessionScope.width}</h3>
            <h3>Din valgte Længde: ${sessionScope.length}</h3>
            <br> <br><br> <br>

            <h2>Din pris</h2>
            <h3>${sessionScope.totalPrice}</h3>
            <br> <br><br> <br>

            <h2>Din tegning:</h2>
            <img src="${pageContext.request.contextPath}/img/carport.PNG">

        </div>
        <br>

        <div class="row">
            <div class="col-md"></div>
            <div class="col-md center">
                <a href="${pageContext.request.contextPath}/fc/carport">
                    <button type="submit" class="btn btn-primary">Tilbage</button>
                </a>
                <a href="${pageContext.request.contextPath}/fc/receipt">
                    <button type="submit" class="btn btn-primary">Bestil carport</button>
                </a>

            </div>
            <div class="col-md"></div>
        </div>
    </jsp:body>
</t:genericpage>