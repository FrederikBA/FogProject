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
            <th scope="col">Længde</th>
            <th scope="col">Antal</th>
            <th scope="col">Enhed</th>
            <th scope="col">Beskrivelse</th>
        </tr>
        </thead>
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
                <label for="length">Længde:</label>
                <input class="form-control" type="text" id="length" name="length">
            </div>
        </div>

        <div class="col-md">
            <div class="form-group">
                <label for="amount">Antal:</label>
                <input class="form-control" type="text" id="amount" name="amount">
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
                <br>
                <button style="width:100%;" type="submit" class="btn btn-secondary" name="update"
                        value="update">Opdater Balance
                </button>
            </div>
        </div>
        </div>

    </jsp:body>
</t:genericpage>