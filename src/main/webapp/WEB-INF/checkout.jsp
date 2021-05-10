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
        <form method="post" action="${pageContext.request.contextPath}/fc/checkout">
            <div class="row">

            </div>
            <div class="col-md center">


                <h1>Din ordre er registreret.</h1>

                <br>

                <h3>Din valgte Bredde: ${sessionScope.width}</h3>
                <h3>Din valgte Længde: ${sessionScope.length}</h3>

                <br>

                <h2>Din pris:</h2>
                <h3>${sessionScope.totalPrice}</h3>
            </div>
            <br>
            <div class="row">

                <div class="col-md"></div>
                <div class="col-md-6 center">

                    <h2>Din tegning:</h2>
                    <img class="responsive drawing" src="${pageContext.request.contextPath}/img/carport.PNG">

                </div>
                <div class="col-md"></div>

            </div>


            <div class="row">
                <div class="col-md"></div>
                <div class="col-md center">
                    <button name="return" type="submit" class="btn btn-primary checkoutbtn">Tilbage</button>
                    <button name="confirm" type="submit" class="btn btn-primary checkoutbtn">Bestil carport</button>
                </div>
                <div class="col-md"></div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>