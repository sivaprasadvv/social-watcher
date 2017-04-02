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
					<% if (((com.sitequesttech.social.watcher.web.support.UserContextUtil)request.getAttribute("userContext")).getRole().equals("admin")) { %>
						<li class="active"><a
							href="${pageContext.request.contextPath}/"><i class="icon-home icon-white"></i><fmt:message key="navbar.menu.home"/></a></li>						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.social.watcher"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/management/users/list"><fmt:message key="navbar.menu.management.user"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/roles/list"><fmt:message key="navbar.menu.management.role"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/clients/list"><fmt:message key="navbar.menu.social.watcher.management.client"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/partner/list"><fmt:message key="navbar.menu.social.watcher.management.partner"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/socialmedia/list"><fmt:message key="navbar.menu.social.watcher.management.sm"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/clientuser/list"><fmt:message key="navbar.menu.social.watcher.management.clientuser"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/clientbypartner/list"><fmt:message key="navbar.menu.social.watcher.management.partnerclient"/></a></li>								
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.search"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/masterquery/mcreate"><fmt:message key="navbar.menu.masterquery.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.report"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/report/create"><fmt:message key="navbar.menu.report.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.audit"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/audit/toreview/list"><fmt:message key="navbar.menu.audit.to.review"/></a></li>
								<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
							</ul>
						</li>
						<% } else if (((com.sitequesttech.social.watcher.web.support.UserContextUtil)request.getAttribute("userContext")).getRole().equals("partner")) { %>
						<li class="active"><a
							href="${pageContext.request.contextPath}/"><i class="icon-home icon-white"></i><fmt:message key="navbar.menu.home"/></a></li>						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.social.watcher"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/management/clients/list"><fmt:message key="navbar.menu.social.watcher.management.client"/></a></li>
								<li><a href="${pageContext.request.contextPath}/management/clientuser/list"><fmt:message key="navbar.menu.social.watcher.management.clientuser"/></a></li>								
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.report"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/report/create"><fmt:message key="navbar.menu.report.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.audit"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
							</ul>
						</li>
						<% } else if (((com.sitequesttech.social.watcher.web.support.UserContextUtil)request.getAttribute("userContext")).getRole().equalsIgnoreCase("client")) { %>
						<li class="active"><a
							href="${pageContext.request.contextPath}/"><i class="icon-home icon-white"></i><fmt:message key="navbar.menu.home"/></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.search"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/masterquery/mcreate"><fmt:message key="navbar.menu.masterquery.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.report"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/report/create"><fmt:message key="navbar.menu.report.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.audit"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
							</ul>
						</li>
						<% } else { %>						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.report"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/report/create"><fmt:message key="navbar.menu.report.create"/></a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.audit"/><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
							</ul>
						</li>
						<% } %>
						</ul>	
				</div>
				
			</security:authorize>
			
		</div>
	</div>
</div>