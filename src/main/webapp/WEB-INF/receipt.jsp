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
        <form method="post" action="${pageContext.request.contextPath}/fc/checkout">
            <div class="center">
                <h1>KVITTERING</h1>
                <br>
                <h4>Din valgte Bredde: ${sessionScope.width}</h4>
                <h4>Din valgte Længde: ${sessionScope.length}</h4>
                <br>
                <h3>Tag: Fladt</h3>
                <h3>Skur: Ingen</h3>
                <br>
                <h3>Pris i alt: ${sessionScope.totalPrice},-</h3>
                <br>
                <h4>Din ordre vil kunne opfølges under "Mine ordrer".<br><br> Når din ordre er blevet bekræftet, vil du få adgang til den tilhørende stykliste.</h4>
            </div>
        </form>
    </jsp:body>
</t:genericpage>


