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

                <div class="col"></div>
                <div class="col center">

                    <button type="submit" class="btn btn-primary">Skræddersy din Carport</button>

                </div>
                <div class="col"></div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>