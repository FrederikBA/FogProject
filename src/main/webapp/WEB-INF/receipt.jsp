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
        <form method="post" action="${pageContext.request.contextPath}/fc/carport">
<div>
</div>
        <div class="row">


            <div class="col-md">
                <h1>KVITTERING</h1>
                <h3>Carport:</h3>
                580 cm Længde <br>
                240 cm bredde<br>
                tag: fladt<br>
                skur: ingen<br>
                <br><br><br>

                TEGNING
                <img src="img/carport.PNG">
                <br><br><br>

                Pris: 25.000Kr
                <br><br><br>

                Stykliste:

            </div>
            <div class="col-md center">
            <div class="col-md"></div>



            </div>

        </div>



        </form>

    </jsp:body>
</t:genericpage>


