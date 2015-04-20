<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ attribute name="mainCode" required="true" %>
<%@ attribute name="lang1" required="true" %>
<%@ attribute name="lang2" required="true" %>
<%@ attribute name="codeLang1" required="true" %>
<%@ attribute name="codeLang2" required="true" %>

<div>
	<spring:message code="${mainCode}" /> : <a href="${lang1}"><spring:message code="${codeLang1}" /></a>|<a href="${lang2}"><spring:message code="${codeLang2}" /></a>
</div>  