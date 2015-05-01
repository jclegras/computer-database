<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            <spring:message code="http.error.404.message" />
            <br/>
            <!-- stacktrace -->
        </div>
    </div>
</section>

<c:import url="import/footer.jsp" />

</body>
</html>