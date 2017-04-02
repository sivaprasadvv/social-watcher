<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li class="active">
	<a	href="${pageContext.request.contextPath}/"><i class="icon-home icon-white"></i><fmt:message key="navbar.menu.home"/></a>
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
		<li><a href="${pageContext.request.contextPath}/audit/byreview/list"><fmt:message key="navbar.menu.audit.by.review"/></a></li>
	</ul>
</li>