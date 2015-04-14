<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:import url="import/head.jsp" />
<body>
<c:import url="import/header.jsp" />

<section id="main">
    <div class="container">
    	<div>
    		Language : <a href="?language=en"><spring:message code="dashboard.english" /></a>|<a href="?language=fr">Français</a>
    	</div>
        <h1 id="homeTitle">
            ${page.totalEntities} <spring:message code="dashboard.computersFound" />
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
					<c:if test="${page.properties.contains('computer.name')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Computer name	
								</p:link>			                		           			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Computer name	
								</p:link>	 		                		       			
	                		</c:otherwise>
	                	</c:choose>
                	</c:if>
                	<c:if test="${!page.properties.contains('computer.name')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.name" page="${page.page}">
	                		<span aria-hidden="true">Computer name</span>  		
						</p:link>	                	  		                		 
                	</c:if>              			
                </th>
                <th>
					<c:if test="${page.properties.contains('computer.introduced')}">                	
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.introduced" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Introduced date		
								</p:link>		                		          			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.introduced" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Introduced date		
								</p:link>	                		      			
	                		</c:otherwise>
	                	</c:choose>  
	                </c:if>
	                <c:if test="${!page.properties.contains('computer.introduced')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.introduced" page="${page.page}">
							<span aria-hidden="true">Introduced date</span>     		
						</p:link>		                		                
	                </c:if>           			                   
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                	<c:if test="${page.properties.contains('computer.discontinued')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
			                	<p:link url="dashboard" sort="DESC" size="${page.size}" column="computer.discontinued" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Discontinued date        		
								</p:link>		                		          			
	                		</c:when>
	                		<c:otherwise>
			                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.discontinued" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Discontinued date        		
								</p:link>		                		     			
	                		</c:otherwise>
	                	</c:choose>
	                </c:if>
	                <c:if test="${!page.properties.contains('computer.discontinued')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="computer.discontinued" page="${page.page}">
							<span aria-hidden="true">Discontinued date</span>     		
						</p:link>		                           
	                </c:if>             			        
                </th>
                <!-- Table header for Company -->
                <th>                    
                	<c:if test="${page.properties.contains('company.name')}">
	                	<c:choose>
	                		<c:when test="${page.sort == 'ASC'}">
	                			<p:link url="dashboard" sort="DESC" size="${page.size}" column="company.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>Company
				                </p:link>	                		       			
	                		</c:when>
	                		<c:otherwise>
	                			<p:link url="dashboard" sort="ASC" size="${page.size}" column="company.name" page="${page.page}">
			                		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>Company
				                </p:link>           			
	                		</c:otherwise>
	                	</c:choose>                			
               		</c:if>
               		<c:if test="${!page.properties.contains('company.name')}">
	                	<p:link url="dashboard" sort="ASC" size="${page.size}" column="company.name" page="${page.page}">
	                		<span aria-hidden="true">Company</span>               		
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