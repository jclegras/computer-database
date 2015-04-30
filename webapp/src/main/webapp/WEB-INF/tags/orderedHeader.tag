<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<%@ attribute name="page" required="true" type="com.excilys.persistence.util.Page" %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="label" required="true" %>


<c:if test="${page.properties.contains(property)}">
    <c:choose>
        <c:when test="${page.sort == 'ASC'}">
            <p:link url="${url}" sort="DESC" size="${page.size}" column="${property}"
                    page="${page.page}">
                <span class="glyphicon glyphicon-arrow-down"
                      aria-hidden="true"></span>${label}
            </p:link>
        </c:when>
        <c:otherwise>
            <p:link url="${url}" sort="ASC" size="${page.size}" column="${property}"
                    page="${page.page}">
                <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>${label}
            </p:link>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${!page.properties.contains(property)}">
    <p:link url="${url}" sort="ASC" size="${page.size}" column="${property}"
            page="${page.page}">
        <span aria-hidden="true">${label}</span>
    </p:link>
</c:if>