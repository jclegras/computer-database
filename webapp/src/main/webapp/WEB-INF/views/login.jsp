<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="import/head.jsp" />
<body>
	<c:import url="import/header.jsp" />
	<p:translator mainCode="title.language" lang2="?language=fr" lang1="?language=en" codeLang2="dashboard.french" codeLang1="dashboard.english" />	
	<section id="main">
			<form:form method="post" action="login">
				<div class="container" style="margin-top: 10px;">
					<c:if test="${not empty error}">
						<div class="has-error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div>${msg}</div>
					</c:if>
					<div class="form-group col-xs-4 col-xs-offset-4 row">
						<label for="username"><spring:message code="form.username" /></label>
						<input class="form-control" type="text" name="username" required />
					</div>
					<div class="form-group col-xs-4 col-xs-offset-4 row">
						<label for="password"><spring:message code="form.password" /></label>
						<input class="form-control" type="password" name="password" required />
					</div>
					<div class="form-group col-xs-1 col-xs-offset-4 row">
						<input class="btn btn-primary" name="submit" type="submit" value="Login" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</div>
				</div>
			</form:form>
	</section>
<c:import url="import/footer.jsp" />

</body>
</html>
