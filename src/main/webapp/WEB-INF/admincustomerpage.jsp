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

                <h1 align="center">Velkommen til ADMIN CUSTOMER</h1>

                <div class="col-md center">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Bruger ID</th>
                            <th scope="col">Email</th>
                            <th scope="col">Tidspunkt</th>
                            <th scope="col">Se ordrer</th>
                            <th scope="col">Slet ordre</th>

                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>AudiTT@mail.dk</td>
                            <td>24:00</td>
                            <td>Se indhold</td>
                            <td>Fjern</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>AudiTT@mail.dk</td>
                            <td>12:00</td>
                            <td>Se indhold</td>
                            <td>Fjern</td>
                        </tr>

                        </tbody>
                    </table>

                </div>


            </div>

            <div class="row">
                <div class="col-md"></div>
                <div class="col-md center">
                </div>
                <div class="col-md"></div>
            </div>
        </form>

    </jsp:body>
</t:genericpage>


