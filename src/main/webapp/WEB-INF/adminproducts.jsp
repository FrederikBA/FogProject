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
        <div class="col-md">
            <div class="form-group">
                <label for="userId">Materiale Id:</label>
                <input class="form-control" type="text" id="userId" name="userId">
            </div>
        </div>

        <div class="col-md">
            <div class="form-group">
                <label for="balance">Balance:</label>
                <input class="form-control" type="text" id="balance" name="balance">
            </div>
        </div>

        <div class="col-md">
            <div class="form-group">
                <label for="balance">Balance:</label>
                <input class="form-control" type="text" id="balance" name="balance">
            </div>
        </div>

        <div class="col-md">
            <div class="form-group">
                <label for="balance">Balance:</label>
                <input class="form-control" type="text" id="balance" name="balance">
            </div>
        </div>

        <div class="col-md">
            <div class="form-group">
                <label for="balance">Balance:</label>
                <input class="form-control" type="text" id="balance" name="balance">
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

    </jsp:body>
</t:genericpage>