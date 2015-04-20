<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            Error 500: An error has occured!
            <br/>
            <!-- stacktrace -->
        </div>
    </div>
</section>

<c:import url="import/footer.jsp" />

</body>
</html>