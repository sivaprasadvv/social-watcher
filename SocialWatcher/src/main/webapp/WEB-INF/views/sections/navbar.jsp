<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="${pageContext.request.contextPath}/"><fmt:message key="header.webapp.name"/>&nbsp;<span class="label label-info"><fmt:message key="header.webapp.version"/></span></a>
			<security:authorize access="isAuthenticated()">
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i> ${userContext.loginname} <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/resources/j_spring_security_logout"><fmt:message key="navbar.menu.logout"/></a></li>
					</ul>
				</div>
			</security:authorize>
			
			
			<security:authorize access="isAuthenticated()">
			
				<div class="nav-collapse pull-right">
					<ul class="nav">
						<c:choose>
							<c:when test="${userContext.role eq 'admin' }">
								<jsp:include page="adminnavbar.jsp"></jsp:include>
							</c:when>
							<c:when test="${userContext.role eq 'partner' }">
								<jsp:include page="partnernavbar.jsp"></jsp:include>
							</c:when>
							<c:when test="${userContext.role eq 'client' }">
								<jsp:include page="clientnavbar.jsp"></jsp:include>
							</c:when>
							<c:when test="${userContext.role eq 'user' }">
								<jsp:include page="usernavbar.jsp"></jsp:include>
							</c:when>	
							<c:otherwise>
								<jsp:include page="usernavbar.jsp"></jsp:include>
							</c:otherwise>						
						</c:choose>
					</ul>	
				</div>
				
			</security:authorize>
			
		</div>
	</div>
</div>