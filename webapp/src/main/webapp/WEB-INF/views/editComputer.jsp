<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />
<p:translator mainCode="title.language" lang2="?language=fr&id=${computer.id}" lang1="?language=en&id=${computer.id}" codeLang2="dashboard.french" codeLang1="dashboard.english" />

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: <c:out value="${computer.id}"/>
                </div>
                <h1><spring:message code="title.editComputer" /></h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form:form commandName="computer" action="editComputer" method="POST">
                	<form:hidden path="id" />
                    <fieldset>
                        <div class="form-group">
                            <form:label path="name" for="name"><spring:message code="form.computerName" /></form:label>
							<spring:message code="form.placeholder.computerName" var="computerNamePlaceholder"/>                            
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" 
                            	placeholder="${computerNamePlaceholder}" />
                            <c:if test="${empty errors}">
                                <span class="error-message"></span>
                            </c:if>
                            <form:errors path="name" cssClass="has-error" />
                        </div>
                        <div class="form-group">
                            <form:label path="introduced" for="introduced"><spring:message code="form.introducedDate" /></form:label>
							<spring:message code="form.placeholder.introducedDate" var="introducedPlaceholder"/>                              
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                            	placeholder="${introducedPlaceholder}" />
                            <c:if test="${empty errors}">
                                <span class="error-message"></span>
                            </c:if>
							<form:errors path="introduced" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="discontinued" for="discontinued"><spring:message code="form.discontinuedDate" /></form:label>
							<spring:message code="form.placeholder.discontinuedDate" var="discontinuedPlaceholder"/>                            
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="${discontinuedPlaceholder}" />
                            <c:if test="${empty errors}">
                                <span class="error-message"></span>
                            </c:if>
							<form:errors path="discontinued" cssClass="has-error" />                                   
                        </div>
                        <div class="form-group">
                            <form:label path="companyId" for="companyId"><spring:message code="form.company" /></form:label>
                            <form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
                            	<spring:message code="form.selectCompany" var="selectCompanyLabel"/>
                            	<form:option value="" label="${selectCompanyLabel}"/>
                            	<form:options items="${companies}" itemValue="id" itemLabel="name" />
                            </form:select>
							<form:errors path="companyId" cssClass="has-error" />                            
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <spring:message code="form.edit" var="editLabel" />
                        <input type="submit" value="${editLabel}" class="btn btn-primary">
                        <spring:message code="form.or" />
                        <a class="btn btn-default" href="${prev}"><spring:message code="form.cancel" /></a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<c:import url="import/footer.jsp" />
<script src="<c:url value="/resources/js/validation.js" />"></script>
<script type="text/javascript">
	var datePattern = "<spring:message code='validation.date.pattern' javaScriptEscape='true' />";
	var expectedDateFormat = "<spring:message code='validation.format.short' javaScriptEscape='true' />";
	var mandatoryMessage = "<spring:message code='validation.notNull' javaScriptEscape='true' />";		
</script>
</body>
</html>