<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/dashboard" />"> Application - Computer Database </a>
    </div>
</header>

<div class="row">
	<div class="col-md-2 col-md-offset-10">
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<form action="logout" method="post">
				<sec:csrfInput />
				<spring:message code="form.security.logout" var="logoutLabel" />
				<input type="submit" class="btn btn-danger" value="${logoutLabel}" />
			</form>
		</sec:authorize>
	</div>
</div>			