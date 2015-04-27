<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/dashboard" />"> Application - Computer Database </a>
    </div>
</header>

<div>
<%-- 	<sec:authorize access="ROLE_USER"> --%>
		<form action="logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" class="btn btn-danger" value="logout" />
		</form>
<%-- 	</sec:authorize> --%>
</div>