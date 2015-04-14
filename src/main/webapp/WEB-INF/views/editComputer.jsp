<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <form:form commandName="computer" action="editComputer" method="POST">
                	<form:hidden path="id" />
                    <fieldset>
                        <div class="form-group">
                            <form:label path="name" for="name">Computer name</form:label>
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" placeholder="Computer name" />
                            <form:errors path="name" cssClass="has-error" />
                        </div>
                        <div class="form-group">
                            <form:label path="introduced" for="introduced">Introduced date</form:label>
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                                   placeholder="Introduced date" />
							<form:errors path="introduced" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="discontinued" for="discontinued">Discontinued date</form:label>
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="Discontinued date" />
							<form:errors path="discontinued" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="companyId" for="companyId">Company</form:label>
                            <form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
                            	<form:option value="" label="Select company"/>
                            	<form:options items="${companies}" itemValue="id" itemLabel="name" />
                            </form:select>
							<form:errors path="companyId" cssClass="has-error" />                            
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Edit" class="btn btn-primary">
                        or
                        <a class="btn btn-default" href="<c:url value="/dashboard" />">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
</body>
</html>