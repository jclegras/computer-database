<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1>Add Computer</h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form action="addComputer" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <label for="name">Computer name</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Computer name">
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date</label>
                            <input type="date" class="form-control" id="introduced" name="introduced"
                                   placeholder="Introduced date">
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date</label>
                            <input type="date" class="form-control" id="discontinued" name="discontinued"
                                   placeholder="Discontinued date">
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label>
                            <select class="form-control" id="companyId" name="companyId">
                                <c:forEach var="c" items="${companies}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Add" class="btn btn-primary">
                        or
                        <a class="btn btn-default" href="<c:url value="/dashboard" />">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>