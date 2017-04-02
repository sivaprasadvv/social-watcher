<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="hero-unit">
	<!-- <h2>Hello, ${userContext.loginname}!</h2>
	<p>Social Watcher is a web application that do search on Twitter tweets and Facebook posts. This application uses below technologies </p>
	<ul>
		<li>Spring MVC with Rest like url for controllers.</li>
		<li>Spring Security for user/password authentication.</li>
		<li>Spring Data JPA for persistency.</li>
		<li>Twitter BootStrap for the UI.</li>
		<li>Jquery for Ajax for REST calls.</li>
		<li>JQuery Datatable plugin.</li>
		<li>Apache Tiles for web page templating.</li>
	</ul>
	<p>
		<a class="btn btn-primary btn-large" href="https://github.com/sitequest/SocialWatcher" >See the project source code on GitHub!</a>
	</p> -->
	<div class="row-fluid acc-wizard">
	<c:choose>
		<c:when test="${userContext.role eq 'admin' }">
			<jsp:include page="Home/adminhome.jsp"></jsp:include>
		</c:when>
		<c:when test="${userContext.role eq 'partner' }">
			<jsp:include page="Home/partnerhome.jsp"></jsp:include>
		</c:when>
		<c:when test="${userContext.role eq 'client' }">
			<jsp:include page="Home/clienthome.jsp"></jsp:include>
		</c:when>
		<c:when test="${userContext.role eq 'user' }">
			<jsp:include page="Home/userhome.jsp"></jsp:include>
		</c:when>	
		<c:otherwise>
			<jsp:include page="Home/userhome.jsp"></jsp:include>
		</c:otherwise>						
	</c:choose>
	</div>
</div>
