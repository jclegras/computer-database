<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/dashboard" />"> Application - Computer Database </a>
    </div>
	<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
		<form action="logout" method="post">
			<sec:csrfInput />
			<input type="submit" class="btn btn-danger" value="Logout" />
		</form>
	</sec:authorize>    
</header>

<div>
</div>