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
        <form method="post" action="${pageContext.request.contextPath}/fc/myorders">
            <div class="row">
                <div class="center">
                    <br>
                    <h3>Ordre status: ${requestScope.status}</h3>
                    <h3>Pris i alt: ${requestScope.totalPrice},-</h3>
                    <br>
                    <c:if test="${!requestScope.status.equals('Bekræftet')}">
                        <h3>Når din ordre bliver bekræftet får du adgang til tegning samt stykliste på denne side.</h3>
                    </c:if>
                </div>
                <c:if test="${requestScope.status.equals('Bekræftet')}">
                <div class="tegning">
                    <div class="row center">
                        <h2>Din tegning:</h2>
                        <div class="col-md"></div>
                        <div class="col-md-9">
                                ${requestScope.drawing}
                        </div>
                        <div class="col-md"></div>
                    </div>

                    <div class="stykliste">
                        <br>
                        <h1 class="center">Stykliste</h1>
                        <div class="center">
                            <h3>Træ & Tagplader</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Beskrivelse</th>
                                    <th scope="col">Antal</th>
                                    <th scope="col">Længde</th>
                                    <th scope="col">Enhed</th>
                                    <th scope="col">Beskrivelse</th>
                                    <th scope="col">Pris</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="bomLine" items="${requestScope.billOfMaterials}">
                                    <c:if test="${bomLine.length > 0}">
                                        <tr>
                                            <td>${bomLine.name}</td>
                                            <td>${bomLine.quantity}</td>
                                            <td>${bomLine.length}</td>
                                            <td>${bomLine.unit}</td>
                                            <td>${bomLine.description}</td>
                                            <td>${bomLine.price}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br>
                        <div class="center">
                            <h3>Beslag & Skruer</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Beskrivelse</th>
                                    <th scope="col">Antal</th>
                                    <th scope="col">Enhed</th>
                                    <th scope="col">Beskrivelse</th>
                                    <th scope="col">Pris</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="bomLine" items="${requestScope.billOfMaterials}">
                                <c:if test="${bomLine.length < 1}">
                                <tr>
                                    <td>${bomLine.name}</td>
                                    <td>${bomLine.quantity}</td>
                                    <td>${bomLine.unit}</td>
                                    <td>${bomLine.description}</td>
                                    <td>${bomLine.price}</td>
                                </tr>
                                </c:if>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    </c:if>
                </div>
        </form>
    </jsp:body>
</t:genericpage>

