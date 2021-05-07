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

        <h1 align="center">Velkommen til Fog</h1>
        <p align="center">Byg din egen trægarage efter dine ønsker – så kan bilen opholde sig inde i en robust og samtidigt elegant bygning.<BR>
            Men garager er ikke bare til biler, men også til din drøm af en motorcykel, alle ungernes cykler eller værksted. <BR>
            Køb nu, men få leveret senere når det passer dig.
        </p>
        <div class="row">

            <div class="col"></div>
            <div class="col center">

                <button type="button" class="btn btn-primary">Bestil Carport</button>

            </div>
            <div class="col"></div>
        </div>

    </jsp:body>
</t:genericpage>