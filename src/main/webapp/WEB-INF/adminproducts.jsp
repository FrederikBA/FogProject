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
        </div>

        <div class="col"></div>


        <h1 align="center">Velkommen til ADMIN PRODUCTS</h1>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Beskrivelse(navn)</th>
                <th scope="col">Enhed</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Pris pr</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="material" items="${sessionScope.materials}">
                <tr>
                    <td>${material.materialId}</td>
                    <td>${material.name}</td>
                    <td>${material.unit}</td>
                    <td>${material.description}</td>
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
                    <label for="name">Navn:</label>
                    <input class="form-control" type="text" id="name" name="name">
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
                    <label for="description">Beskrivelse:</label>
                    <input class="form-control" type="text" id="description" name="description">
                </div>
            </div>

            <div class="col-md">
                <div class="form-group">
                    <label for="price">Pris:</label>
                    <input class="form-control" type="text" id="price" name="price">
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

    </jsp:body>
</t:genericpage>