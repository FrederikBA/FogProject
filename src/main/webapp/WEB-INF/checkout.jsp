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
            <h3>Din valgte bredde: ${sessionScope.width}</h3>
            <h3>Din valgte højde: ${sessionScope.length}</h3>
        </div>

        <br>

        <div class="row">
            <div class="col-md"></div>
            <div class="col-md center">
                <button type="submit" class="btn btn-primary">Bestil</button>
            </div>
            <div class="col-md"></div>
        </div>

        <br>

        <div class="row">
            <!--public BomLine(int materialId, int quantity, int length, String description, double price) -->
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Antal</th>
                    <th>Længde</th>
                    <th>Beskrivelse</th>
                    <th>Pris</th>
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
    </jsp:body>
</t:genericpage>