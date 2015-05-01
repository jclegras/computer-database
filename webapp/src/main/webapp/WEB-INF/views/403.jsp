<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">

        <div class="alert alert-danger">
            <spring:message code="http.error.403.message" />
            <br/>
            <!-- stacktrace -->
        </div>
        <div>
            <a class="btn btn-default" href="<c:url value="/dashboard"/> "><spring:message code="http.error.general.back" /></a>
        </div>
    </div>
</section>

<c:import url="import/footer.jsp" />

</body>
</html>