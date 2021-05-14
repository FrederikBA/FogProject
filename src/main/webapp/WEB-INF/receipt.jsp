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


                <div class="col-md center">

                    <h1>KVITTERING</h1>
                    <h2>Carport:</h2>
                    <h4>Din valgte Bredde: ${sessionScope.width}</h4>
                    <h4>Din valgte Længde: ${sessionScope.length}</h4>
                    <h3>Tag: Fladt<br></h3>
                    <h3>Skur: Ingen<br></h3>

                    <br>

                    <img src="${pageContext.request.contextPath}/img/carport.PNG">

                    <br>

                    <h3>Pris: ${sessionScope.totalPrice}</h3>

                    <br>
                    <h1> Stykliste</h1>

                    <div class="row">

                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Beskrivelse</th>
                                <th scope="col">Antal</th>
                                <th scope="col">Længde</th>
                                <th scope="col">Beskrivelse</th>
                                <th scope="col">Pris</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="bomLine" items="${sessionScope.billOfMaterials}">
                                <c:if test="${bomLine.length > 1}">
                                    <tr>
                                        <td>${bomLine.name}</td>
                                        <td>${bomLine.quantity}</td>
                                        <td>${bomLine.length}</td>
                                        <td>${bomLine.description}</td>
                                        <td>${bomLine.price}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="col-md"></div>
                </div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>


