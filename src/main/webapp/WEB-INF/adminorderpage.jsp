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
            <div class="row">

                <h1 align="center">Ordreoversigt</h1>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Bruger ID</th>
                        <th scope="col">Ordre ID</th>
                        <th scope="col">Tidspunkt</th>
                        <th scope="col">Pris</th>
                        <th scope="col">Indhold</th>
                        <th scope="col">Status</th>
                        <th scope="col">Opdater Status</th>
                        <th scope="col">Fjern</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${sessionScope.orderlist}">
                        <tr>
                            <td>${order.userId}</td>
                            <td>${order.orderId}</td>
                            <td>${order.timestamp}</td>
                            <td>${order.price}</td>

                            <td>
                                <a href="${pageContext.request.contextPath}/fc/ordercontentpage">
                                    <button style="width:115px;" class="btn btn-primary btn-sm" type="submit" name="content"
                                            value="${order.orderId}">
                                        Se indhold
                                    </button>
                                </a>
                            </td>
                            <td>${order.status}</td>
                            <td>
                                <button style="width:115px;" class="btn btn-primary btn-sm" type="submit" name="update"
                                        value="${order.orderId}">
                                    Bekræft
                                </button>
                            </td>
                            <td>
                                <button style="width:115px;" class="btn btn-danger btn-sm" type="submit" name="delete"
                                        value="${order.orderId}">
                                    Fjern
                                </button>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>

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


