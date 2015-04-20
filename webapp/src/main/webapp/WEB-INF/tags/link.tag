<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="page" required="true" %>
<%@ attribute name="size" required="true" %>
<%@ attribute name="sort" required="true" %>
<%@ attribute name="column" required="true" %>


<a href="<c:url value="${url}?page=${page}&size=${size}&sort=${sort}&col=${column}" />">
	<jsp:doBody />
</a>   