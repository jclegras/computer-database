<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />
<p:translator mainCode="title.language" lang2="?language=fr" lang1="?language=en" codeLang2="dashboard.french" codeLang1="dashboard.english" />

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1><spring:message code="title.addComputer" /></h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form:form commandName="newComputer" action="addComputer" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <form:label path="name" for="name"><spring:message code="form.computerName" /></form:label>
							<c:set var="computerNamePlaceholder"><spring:message code="form.placeholder.computerName"/></c:set>                            
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" 
                            	placeholder="${computerNamePlaceholder}" />
                            <form:errors path="name" cssClass="has-error" />
                        </div>
                        <div class="form-group">
                            <form:label path="introduced" for="introduced"><spring:message code="form.introducedDate" /></form:label>
							<c:set var="introducedPlaceholder"><spring:message code="form.placeholder.introducedDate"/></c:set>                              
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                            	placeholder="${introducedPlaceholder}" />
							<form:errors path="introduced" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="discontinued" for="discontinued"><spring:message code="form.discontinuedDate" /></form:label>
							<c:set var="discontinuedPlaceholder"><spring:message code="form.placeholder.discontinuedDate"/></c:set>                            
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="${discontinuedPlaceholder}" />
							<form:errors path="discontinued" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="companyId" for="companyId"><spring:message code="form.company" /></form:label>
                            <form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
                            	<c:set var="selectCompanyLabel"><spring:message code="form.selectCompany"/></c:set>
                            	<form:option value="" label="${selectCompanyLabel}"/>
                            	<form:options items="${companies}" itemValue="id" itemLabel="name" />
                            </form:select>
							<form:errors path="companyId" cssClass="has-error" />                            
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Add" class="btn btn-primary">
                        <spring:message code="form.or" />
                        <a class="btn btn-default" href="<c:url value="/dashboard" />"><spring:message code="form.cancel" /></a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
</body>
</html>