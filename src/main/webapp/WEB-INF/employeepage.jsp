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
            <h1>Du er logged ind som: ${sessionScope.email} </h1>
            <br>
            <a href="${pageContext.request.contextPath}/fc/index">
                <button style="width:200px;" type="submit" class="btn btn-primary">Retur til forsiden</button>
            </a>
        </div>
    </jsp:body>
</t:genericpage>
