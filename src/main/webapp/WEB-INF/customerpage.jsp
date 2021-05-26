<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Fog - Carport
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="center">
            <br>
            <h2>Velkommen!</h2>
            <h2>Du er logget ind som: ${sessionScope.email}.</h2>
            <br>
            <a href="${pageContext.request.contextPath}/fc/index">
                <button style="width:200px;" type="submit" class="btn btn-primary">Gå til forsiden</button>
            </a>
        </div>
    </jsp:body>

</t:genericpage>

