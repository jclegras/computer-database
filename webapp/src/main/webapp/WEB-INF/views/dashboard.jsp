<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />
<p:translator mainCode="title.language" lang2="?language=fr" lang1="?language=en" codeLang2="dashboard.french" codeLang1="dashboard.english" />

<section id="main">
    <div class="container">
        <h1 id="homeTitle">
            ${page.totalEntities} <spring:message code="dashboard.computersFound" />
        </h1>

        <div id="actions" class="form-horizontal">
            <div class="pull-left">
                <form id="searchForm" action="#" method="GET" class="form-inline">
					<spring:message code="form.filterByName" var="filterByNameLabel"/>
					<spring:message code="form.placeholder.searchName" var="searchNamePlaceholder"/>
                    <input type="search" id="searchbox" name="search" class="form-control" placeholder="${searchNamePlaceholder}"/>
                    <input type="submit" id="searchsubmit" value="${filterByNameLabel}"
                           class="btn btn-primary"/>
                </form>
            </div>
            <div class="pull-right">
            	<sec:authorize access="hasRole('ROLE_ADMIN')">
	                <a class="btn btn-success" id="addComputer" href="<c:url value="/addComputer" />"><spring:message code="title.addComputer" /></a>
	                <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="title.editComputer" /></a>
                </sec:authorize>
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
					<c:if test="${page.properties.contains('computer.name')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span><spring:message code="form.computerName" />	
								</p:link>			                		           			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span><spring:message code="form.computerName" />	
								</p:link>	 		                		       			
	                		</c:otherwise>
	                	</c:choose>
                	</c:if>
                	<c:if test="${!page.properties.contains('computer.name')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.name" page="${page.page}">
	                		<span aria-hidden="true"><spring:message code="form.computerName" /></span>  		
						</p:link>	                	  		                		 
                	</c:if>              			
                </th>
                <th>
					<c:if test="${page.properties.contains('computer.introduced')}">                	
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.introduced" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span><spring:message code="form.introducedDate" />		
								</p:link>		                		          			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.introduced" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span><spring:message code="form.introducedDate" />		
								</p:link>	                		      			
	                		</c:otherwise>
	                	</c:choose>  
	                </c:if>
	                <c:if test="${!page.properties.contains('computer.introduced')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.introduced" page="${page.page}">
							<span aria-hidden="true"><spring:message code="form.introducedDate" /></span>     		
						</p:link>		                		                
	                </c:if>           			                   
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                	<c:if test="${page.properties.contains('computer.discontinued')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.discontinued" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span><spring:message code="form.discontinuedDate" />        		
								</p:link>		                		          			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.discontinued" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span><spring:message code="form.discontinuedDate" />        		
								</p:link>		                		     			
	                		</c:otherwise>
	                	</c:choose>
	                </c:if>
	                <c:if test="${!page.properties.contains('computer.discontinued')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.discontinued" page="${page.page}">
							<span aria-hidden="true"><spring:message code="form.discontinuedDate" /></span>     		
						</p:link>		                           
	                </c:if>             			        
                </th>
                <!-- Table header for Company -->
                <th>                    
                	<c:if test="${page.properties.contains('company.name')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
	                			<p:link url="dashboard" sort="DESC" size="${page.size}" column="company.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span><spring:message code="form.company" />
				                </p:link>	                		       			
	                		</c:when>
	                		<c:otherwise>
	                			<p:link url="dashboard" sort="ASC" size="${page.size}" column="company.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span><spring:message code="form.company" />
				                </p:link>           			
	                		</c:otherwise>
	                	</c:choose>                			
               		</c:if>
               		<c:if test="${!page.properties.contains('company.name')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="company.name" page="${page.page}">
	                		<span aria-hidden="true"><spring:message code="form.company" /></span>               		
						</p:link>	                	
               		</c:if>              
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
					${computer.introduced}
                </td>
                <td>
					${computer.discontinued}
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