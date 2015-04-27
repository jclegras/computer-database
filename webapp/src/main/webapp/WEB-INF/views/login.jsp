<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:import url="import/head.jsp" />
<body>
	<c:import url="import/header.jsp" />
	<section id="main">
		<div class="container">
			<form:form method="post" action="login">
				<div class="container" style="margin-top: 10px;">
					<table class="table table-striped table-bordered">
						<tr>
							<td>User ID:</td>
							<td><input type='text' name='username' /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
								value="Login" /></td>
						</tr>
					</table>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</div>
			</form:form>
		</div>
	</section>
<c:import url="import/footer.jsp" />

</body>
</html>
