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

        <div class="row"></div>


        <div class="col-md center">

        <h1>KVITTERING</h1>
        <h2>Carport:</h2>
        <h6>Din valgte Bredde: ${sessionScope.width}</h6>
        <h6>Din valgte Længde: ${sessionScope.length}</h6>
        tag: fladt<br>
        skur: ingen<br>
        <br><br><br>

        TEGNING
        <img src="img/carport.PNG">
        <br><br><br>

        Pris: 25.000Kr
        <br><br><br>
        <h1> Stykliste</h1>

        <div class="row">

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Beskrivelse(navn)</th>

                    <th scope="col">Enhed</th>
                    <th scope="col">Beskrivelse</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bomLine" items="${sessionScope.bom.bomLines}">
                    <tr>
                        <td>${bomLine.materialId}</td>
                        <td>${bomLine.quantity}</td>
                        <td>${bomLine.length}</td>
                        <td>${bomLine.description}</td>
                        <td>${bomLine.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md"></div>


    </jsp:body>
</t:genericpage>


