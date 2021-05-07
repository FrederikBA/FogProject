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
            <div class="row helvetica">
                <div class="form-group">
                    <label for="width" class="bold left">Carport bredde</label>
                    <select class="form-select" name="width" id="width">
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                        <option value="720">720 cm</option>
                        <option value="750">750 cm</option>
                    </select>
                </div>
            </div>
            <br>
            <div class="row helvetica">
                <div class="form-group">
                    <label for="length" class="bold left">Carport længde</label>
                    <select class="form-select" name="length" id="length">
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                        <option value="720">720 cm</option>
                        <option value="750">750 cm</option>
                        <option value="780">780 cm</option>
                    </select>
                </div>
            </div>
            <br>
            <div class="row helvetica">
                <div class="col-md"></div>
                <div class="col-md center">
                    <button type="submit" name="create" class="btn btn-primary">Aflæg Forespørgsel</button>
                </div>
                <div class="col-md"></div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>