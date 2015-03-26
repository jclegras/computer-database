<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
        <h1 id="homeTitle">
            ${page.totalEntities} Computers found
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
                	<c:choose>
                		<c:when test="${page.sort == 'ASC'}">
                			<c:url value="dashboard" var="computerNameASC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="DESC" />
                				<c:param name="col" value="computer.name" />
                			</c:url>       			
                		</c:when>
                		<c:otherwise>
                			<c:url value="dashboard" var="computerNameDESC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="ASC" />
                				<c:param name="col" value="computer.name" />
                			</c:url>                		              		
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                		<c:when test="${page.properties.contains('computer.name')}">
		                	<c:choose>
		                		<c:when test="${page.sort == 'ASC'}">
		                			<c:out value="${ computerNameASC }"></c:out>
				                	<a href="${computerNameASC}">
				                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Computer name
				                	</a>           			
		                		</c:when>
		                		<c:when test="${page.sort == 'DESC'}">
		                		<c:out value="${ computerNameDESC }"></c:out>
				                	<a href="${computerNameDESC}">
				                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Computer name
				                	</a>           			
		                		</c:when>
		                	</c:choose>                			
                		</c:when>
                		<c:otherwise>
                			<c:out value="${ computerNameASC }"></c:out>
		                	<a href="${computerNameASC}">
		                		<span aria-hidden="true">Computer name</span>
		                	</a>                  		
                		</c:otherwise>
                	</c:choose>                	
                </th>
                <th>
                	<c:choose>
                		<c:when test="${page.sort == 'ASC'}">
                			<c:url value="dashboard" var="computerIntroducedASC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="DESC" />
                				<c:param name="col" value="computer.introduced" />
                			</c:url>       			
                		</c:when>
                		<c:otherwise>
                			<c:url value="dashboard" var="computerIntroducedDESC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="ASC" />
                				<c:param name="col" value="computer.introduced" />
                			</c:url>                		              		
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                		<c:when test="${page.properties.contains('computer.introduced')}">
		                	<c:choose>
		                		<c:when test="${page.sort == 'ASC'}">
		                			<c:out value="${ computerIntroducedASC }"></c:out>
				                	<a href="${computerIntroducedASC}">
				                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Introduced date
				                	</a>           			
		                		</c:when>
		                		<c:when test="${page.sort == 'DESC'}">
		                			<c:out value="${ computerIntroducedDESC }"></c:out>
				                	<a href="${computerIntroducedDESC}">
				                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Introduced date
				                	</a>           			
		                		</c:when>
		                	</c:choose>                			
                		</c:when>
                		<c:otherwise>
		                			<c:out value="${ computerIntroducedASC }"></c:out>
		                	<a href="${computerIntroducedASC}">
		                		<span aria-hidden="true">Introduced date</span>
		                	</a>                  		
                		</c:otherwise>
                	</c:choose>                   
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                	<c:choose>
                		<c:when test="${page.sort == 'ASC'}">
                			<c:url value="dashboard" var="computerDiscontinuedASC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="DESC" />
                				<c:param name="col" value="computer.discontinued" />
                			</c:url>       			
                		</c:when>
                		<c:otherwise>
                			<c:url value="dashboard" var="computerDiscontinuedDESC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="ASC" />
                				<c:param name="col" value="computer.discontinued" />
                			</c:url>                		              		
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                		<c:when test="${page.properties.contains('computer.discontinued')}">
		                	<c:choose>
		                		<c:when test="${page.sort == 'ASC'}">
		                			<c:out value="${ computerDiscontinuedASC }"></c:out>
				                	<a href="${computerDiscontinuedASC}">
				                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Discontinued date
				                	</a>           			
		                		</c:when>
		                		<c:when test="${page.sort == 'DESC'}">
		                			<c:out value="${ computerDiscontinuedDESC }"></c:out>
				                	<a href="${computerDiscontinuedDESC}">
				                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Discontinued date
				                	</a>           			
		                		</c:when>
		                	</c:choose>                			
                		</c:when>
                		<c:otherwise>
		                			<c:out value="${ computerDiscontinuedASC }"></c:out>
		                	<a href="${computerDiscontinuedASC}">
		                		<span aria-hidden="true">Discontinued date</span>
		                	</a>           		
                		</c:otherwise>
                	</c:choose>        
                </th>
                <!-- Table header for Company -->
                <th>                    
                	<c:choose>
                		<c:when test="${page.sort == 'ASC'}">
                			<c:url value="dashboard" var="computerCompanyASC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="DESC" />
                				<c:param name="col" value="company.name" />
                			</c:url>       			
                		</c:when>
                		<c:otherwise>
                			<c:url value="dashboard" var="computerCompanyDESC">
                				<c:param name="page" value="${page.page}" />
                				<c:param name="size" value="${page.size}" />
                				<c:param name="sort" value="ASC" />
                				<c:param name="col" value="company.name" />
                			</c:url>                		              		
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                		<c:when test="${page.properties.contains('company.name')}">
		                	<c:choose>
		                		<c:when test="${page.sort == 'ASC'}">
		                			<c:out value="${ computerCompanyASC }"></c:out>
				                	<a href="${computerCompanyASC}">
				                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Company
				                	</a>           			
		                		</c:when>
		                		<c:when test="${page.sort == 'DESC'}">
		                			<c:out value="${ computerCompanyDESC }"></c:out>
				                	<a href="${computerCompanyDESC}">
				                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Company
				                	</a>           			
		                		</c:when>
		                	</c:choose>                			
                		</c:when>
                		<c:otherwise>
		                			<c:out value="${ computerCompanyASC }"></c:out>
		                	<a href="${computerCompanyASC}">
		                		<span aria-hidden="true">Company</span>
		                	</a>           		
                		</c:otherwise>
                	</c:choose>                  
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
    <p:paginator totalPages="${page.totalPages}" page="${page.page}" pageCount="${page.displayablePages}" pageSize="${page.size}"
                 url="/dashboard" previous="${page.previous}"/>
</footer>

<c:import url="import/footer.jsp" />

</body>
</html>