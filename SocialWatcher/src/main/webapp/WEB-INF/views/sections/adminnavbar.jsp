<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li class="active">
	<a href="${pageContext.request.contextPath}/"><i class="icon-home icon-white"></i><fmt:message key="navbar.menu.home"/></a>
</li>						
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.social.watcher"/><b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a href="${pageContext.request.contextPath}/management/users/list"><fmt:message key="navbar.menu.management.user"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/roles/list"><fmt:message key="navbar.menu.management.role"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/clients/list"><fmt:message key="navbar.menu.social.watcher.management.client"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/partner/list"><fmt:message key="navbar.menu.social.watcher.management.partner"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/socialmedia/list"><fmt:message key="navbar.menu.social.watcher.management.sm"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/properties/list"><fmt:message key="navbar.menu.social.watcher.management.property"/></a></li>										
	</ul>
</li>
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.search"/><b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a href="${pageContext.request.contextPath}/masterquery/mcreate"><fmt:message key="navbar.menu.masterquery.create"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/userbyclient/list"><fmt:message key="navbar.menu.social.watcher.management.clientuser"/></a></li>
		<li><a href="${pageContext.request.contextPath}/management/clientbypartner/list"><fmt:message key="navbar.menu.social.watcher.management.partnerclient"/></a></li>
	</ul>
</li>
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.report"/><b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a href="${pageContext.request.contextPath}/report/create"><fmt:message key="navbar.menu.report.create"/></a></li>
	</ul>
</li>
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.menu.audit"/><b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a href="${pageContext.request.contextPath}/audit/toreview/list"><fmt:message key="navbar.menu.audit.to.review"/></a></li>
		<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
	</ul>
</li>
