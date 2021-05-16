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

    <form method="post" action="${pageContext.request.contextPath}/fc/adminproducts">
        <br>
        <h3 class="center">Træ & Tagplader</h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Enhed</th>
                <th scope="col">Pris pr</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="material" items="${sessionScope.wood}">
                <tr>
                    <td>${material.materialId}</td>
                    <td>${material.description}</td>
                    <td>${material.unit}</td>
                    <td>${material.pricePerUnit}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <br>
        <h3 class="center">Beslag & Skruer</h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Enhed</th>
                <th scope="col">Pris pr</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="material" items="${sessionScope.accesories}">
                <tr>
                    <td>${material.materialId}</td>
                    <td>${material.description}</td>
                    <td>${material.unit}</td>
                    <td>${material.pricePerUnit}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <div class="row">

            <div class="col-md">
                <div class="form-group">
                    <label for="materialId">Materiale Id:</label>
                    <input class="form-control" type="text" id="materialId" name="materialId">
                </div>
            </div>

            <div class="col-md">
                <div class="form-group">
                    <label for="description">Beskrivelse:</label>
                    <input class="form-control" type="text" id="description" name="description">
                </div>
            </div>

            <div class="col-md">
                <div class="form-group">
                    <label for="unit">Enhed:</label>
                    <input class="form-control" type="text" id="unit" name="unit">
                </div>
            </div>

            <div class="col-md">
                <div class="form-group">
                    <label for="price">Pris:</label>
                    <input class="form-control" type="text" id="price" name="price">
                </div>
            </div>

            <div class="col-md">
                <div class="form-group">
                    <label for="type">Type:</label>
                    <input class="form-control" type="text" id="type" name="type">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md">
                <br>
                <button style="width:100%;" type="submit" class="btn btn-secondary" name="addMaterial"
                        value="update">Tilføj
                </button>
            </div>

            <div class="col-md">
                <br>
                <button style="width:100%;" type="submit" class="btn btn-secondary" name="update"
                        value="update">Opdater Materiale
                </button>
            </div>

            <div class="col-md">
                <br>
                <button style="width:100%;" type="submit" class="btn btn-secondary" name="deleteMaterial"
                        value="update">Slet
                </button>
            </div>
        </div>
    </form>
    </jsp:body>
</t:genericpage>