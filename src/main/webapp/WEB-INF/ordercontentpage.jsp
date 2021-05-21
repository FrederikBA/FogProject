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
                            </tr>
                            </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </div>
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

