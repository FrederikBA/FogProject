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
        <form method="post" action="${pageContext.request.contextPath}/fc/adminorderpage">
            <div class>
                <h2>Stykliste tilhørende ordrenummer: ${requestScope.orderId}</h2>
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
                    <c:forEach var="bomLine" items="${requestScope.billOfMaterials}">
                        <tr>
                            <td>${bomLine.name}</td>
                            <td>${bomLine.quantity}</td>
                            <td>${bomLine.length}</td>
                            <td>${bomLine.description}</td>
                            <td>${bomLine.price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <div class="center">
                <a href="${pageContext.request.contextPath}/fc/adminorderpage">
                    <button style="width:150px;" type="button" name="return" class="btn btn-primary">Tilbage</button>
                </a>
            </div>
            <br>
        </form>
    </jsp:body>
</t:genericpage>

