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
                    <h4>Din valgte bredde: ${requestScope.width}</h4>
                    <h4>Din valgte længde: ${requestScope.length}</h4>
                    <br>
                    <h4>Pris i alt: ${requestScope.totalPrice},-</h4>
                    <br>
                    <c:if test="${!requestScope.status.equals('Bekræftet')}">
                        <h4>Når din ordre bliver bekræftet får du adgang til styklisten på denne side.</h4>
                    </c:if>
                </div>


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
                <c:if test="${requestScope.status.equals('Bekræftet')}">
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
                </c:if>
            </div>
            <br>
            <div class="center">
                <a href="${pageContext.request.contextPath}/fc/myorders">
                    <button style="width:150px;" type="button" name="return" class="btn btn-primary">Tilbage</button>
                </a>
            </div>
            <br>
        </form>
    </jsp:body>
</t:genericpage>

