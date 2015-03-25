<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
        <h1 id="homeTitle">
            ${total} Computers found
        </h1>

        <div id="actions" class="form-horizontal">
            <div class="pull-left">
                <form id="searchForm" action="#" method="GET" class="form-inline">

                    <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name"/>
                    <input type="submit" id="searchsubmit" value="Filter by name"
                           class="btn btn-primary"/>
                </form>
            </div>
            <div class="pull-right">
                <a class="btn btn-success" id="addComputer" href="<c:url value="/addComputer" />">Add Computer</a>
                <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
            </div>
        </div>
    </div>

    <form id="deleteForm" action="#" method="POST">
        <input type="hidden" name="selection" value="">
    </form>

    <div class="container" style="margin-top: 10px;">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <!-- Variable declarations for passing labels as parameters -->
                <!-- Table header for Computer Name -->
                <th class="editMode" style="width: 60px; height: 22px;">
                    <input type="checkbox" id="selectall"/>
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                <i class="fa fa-trash-o fa-lg"></i>
                            </a>
                            </span>
                </th>
                <th>
                    Computer name
                </th>
                <th>
                    Introduced date
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                    Discontinued date
                </th>
                <!-- Table header for Company -->
                <th>
                    Company
                </th>

            </tr>
            </thead>
            <!-- Browse attribute computers -->
            <tbody id="results">
            <c:forEach items="${computers}" var="computer">
            <tbody>
            <tr>
                <td class="editMode">
                    <input type="checkbox" name="cb" class="cb" value="0"/>
                </td>
                <td>
                    <a href="<c:url value="/editComputer?id=${computer.id}" />"><c:out value="${computer.name}"/></a>
                </td>
                <td>
                	<fmt:parseDate value="${computer.introduced}" var="introducedDate" pattern="yyyy-MM-dd HH:mm" />
                	<fmt:formatDate value="${introducedDate}" type="Date" dateStyle="full" />
                </td>
                <td>
					<fmt:parseDate value="${computer.discontinued}" var="discontinuedDate" pattern="yyyy-MM-dd HH:mm" />                
                	<fmt:formatDate value="${discontinuedDate}" type="Date" dateStyle="full" />
                </td>
                <td>
                    <c:if test="${!empty computer.companyId}">
                        <c:out value="${computer.companyName}"/>
                    </c:if>
                </td>
            </tr>
            </tbody>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<footer class="navbar-fixed-bottom">
    <p:paginator totalPages="${totalPages}" page="${page.page}" pageCount="${maxPages}" pageSize="${page.size}"
                 url="/dashboard" previous="${page.previous}"/>
</footer>

<c:import url="import/footer.jsp" />

</body>
</html>