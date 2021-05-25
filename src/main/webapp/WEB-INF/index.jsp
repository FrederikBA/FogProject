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
            <c:if test="${!user.role.equals('employee')}">
                <div class="row">
                    <div class="col-md"></div>
                    <div class="col-md-8 center">
                        <h1>Velkommen til Fog</h1>
                        <p>Byg din egen trægarage efter dine ønsker – så kan bilen opholde sig inde i en robust
                            og
                            samtidigt elegant bygning.<br>
                            Men garager er ikke bare til biler, men også til din drøm af en motorcykel, alle ungernes
                            cykler
                            eller
                            værksted. <br>
                            Køb nu, men få leveret senere når det passer dig. </p>
                    </div>
                    <div class="col-md"></div>
                </div>

                <br>

                <div class="row">
                    <div class="col-md"></div>
                    <div class="col-md center">
                        <button type="submit" name="begin" class="btn btn-primary">Skræddersy din Carport</button>
                    </div>
                    <div class="col-md"></div>
                </div>
            </c:if>
            <c:if test="${user.role.equals('employee')}">
                <div class="center">
                    <h2>Hej ${sessionScope.user.email}</h2>
                </div>
            </c:if>
        </form>

    </jsp:body>
</t:genericpage>

