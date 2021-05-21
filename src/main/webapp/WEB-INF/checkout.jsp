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
            <div class="center">


                <h1>Din ordre er registreret.</h1>

                <br>

                <h3>Din valgte Bredde: ${sessionScope.width}</h3>
                <h3>Din valgte Længde: ${sessionScope.length}</h3>

                <br>

                <h2>Pris i alt:</h2>
                <h3>${sessionScope.totalPrice},-</h3>
            </div>
            <br>
            <div class="center">
                <h3>Carporten set oppefra:</h3>
                <button class="btn btn-secondary btn-full" type="button" data-toggle="collapse"
                        data-target="#collapseExample"
                        aria-expanded="false" aria-controls="collapseExample">
                    Se tegning
                </button>
                <div class="collapse" id="collapseExample">
                    <br>
                    <div class="card card-body">
                            ${requestScope.drawing}
                    </div>
                </div>
            </div>

            <div class="center pt-5">
                <h3>Carporten set fra siden:</h3>
                <button class="btn btn-secondary btn-full" type="button" data-toggle="collapse"
                        data-target="#collapseExampleTwo"
                        aria-expanded="false" aria-controls="collapseExampleTwo">
                    Se tegning
                </button>
                <div class="collapse" id="collapseExampleTwo">
                    <br>
                    <div class="card card-body">
                            ${requestScope.sideDrawing}
                    </div>
                </div>
            </div>
            <br>
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