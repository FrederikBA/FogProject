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

        <form method="post" action="${pageContext.request.contextPath}/fc/carport">
        <div class="row">

            <h1 align="center">Velkommen til Fog</h1>
            <p align="center">Byg din egen trægarage efter dine ønsker – så kan bilen opholde sig inde i en robust og
                samtidigt elegant bygning.<BR>
                Men garager er ikke bare til biler, men også til din drøm af en motorcykel, alle ungernes cykler eller
                værksted. <BR>
                Køb nu, men få leveret senere når det passer dig.

<br>
<br>
<br>
<br>
            <div align="center" class="container">
            <h1>VI LEVERER HIGH QUALITY CARPORTE TIL DIN AUDI TT SOM VIST PÅ BILLEDET</h1>
                <img src="img/dreamcar.jpg" alt="Dream car" style="width:100%;">
                <div class="top-left">VI LEVERER HIGH QUALITY CARPORTE TIL DIN AUDI TT SOM VIST PÅ BILLEDET</div>
        </div>


        <div class="row">
            <div class="col-md"></div>
            <div class="col-md center">
                <button type="submit" class="btn btn-primary">Skræddersy din Carport</button>
            </div>
            <div class="col-md"></div>
        </div>
        </form>

    </jsp:body>
</t:genericpage>


