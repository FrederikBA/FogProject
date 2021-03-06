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
        <div class="row">

            <h1 class="center">Kundeoversigt</h1>

            <div class="col-md center">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Bruger ID</th>
                        <th scope="col">Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${requestScope.userList}">
                        <c:if test="${user.role.equals('customer')}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.email}</td>
                            </tr>
                        </c:if>
                    </c:forEach>

                    </tbody>
                </table>
            </div>

        </div>


    </jsp:body>
</t:genericpage>


