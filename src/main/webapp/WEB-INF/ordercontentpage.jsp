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
                <div class="col-md"></div>
                <div class="col-md-8 center">
                    <h1>Velkommen til ORDER CONTeNT PAGE</h1>

                </div>
                <div class="col-md"></div>
            </div>

            <br>

            <div class="row">
                <div class="col-md"></div>
                <div class="col-md center">
                </div>
                <div class="col-md"></div>
            </div>
        </form>

    </jsp:body>
</t:genericpage>

