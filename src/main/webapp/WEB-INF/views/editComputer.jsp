<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: <c:out value="${computer.id}"/>
                </div>
                <h1>Edit Computer</h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form action="editComputer" method="POST">
                    <input type="hidden" name="id" value="${computer.id}"/>
                    <fieldset>
                        <div class="form-group">
                            <label for="name">Computer name</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Computer name"
                                   value="<c:out value="${computer.name}"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date</label>
                            <fmt:parseDate value="${computer.introduced}" var="introducedDate" pattern="yyyy-MM-dd HH:mm" />
                            <input type="date" class="form-control" id="introduced" name="introduced"
                                   placeholder="Introduced date"
                                   value="<fmt:formatDate value="${introducedDate}" pattern="yyyy-MM-dd" />"/>
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date</label>
                            <fmt:parseDate value="${computer.discontinued}" var="discontinuedDate" pattern="yyyy-MM-dd HH:mm" />
                            <input type="date" class="form-control" id="discontinued" name="discontinued"
                                   placeholder="Discontinued date"
                                   value="<fmt:formatDate value="${discontinuedDate}" pattern="yyyy-MM-dd" />"/>
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label>
                            <select class="form-control" id="companyId" name="companyId">
                                <c:forEach var="c" items="${companies}">
                                    <c:choose>
                                        <c:when test="${(!empty computer.companyId != null) && (computer.companyId == c.id)}">
                                            <option value="${c.id}" selected>${c.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${c.id}">${c.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Edit" class="btn btn-primary">
                        or
                        <a href="<c:url value="/dashboard" />" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>