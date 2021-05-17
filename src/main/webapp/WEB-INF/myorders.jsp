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
        <form method="post">
            <h2>Ordreoversigt for kunde: ${sessionScope.user.email}</h2>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Ordre ID</th>
                    <th scope="col">Tidspunkt</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Indhold</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${sessionScope.orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.timestamp}</td>
                        <td>${order.price}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" type="submit" name="content"
                                    value="${order.orderId}">
                                Se indhold
                            </button>
                        </td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </jsp:body>
</t:genericpage>

