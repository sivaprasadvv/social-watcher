<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="formsContent">
	<!-- Form container -->
	<legend>
		<fmt:message key="user.by.client.list.legend" />
	</legend>
	<c:choose>
	<c:when test="${ not empty clients }">
	<div class="tabbable">
		<!-- Only required for left/right tabs -->
		<div id="navigation">
			<ul class="nav nav-tabs">
				<c:forEach items="${clients}" var="client" varStatus="x">
					<li class=""><a href="#" id="${client.id}" data-toggle="tab">${client.clientName}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="tab-content">
			<div class="tab-pane active" style="width: auto; display: none;"
				id="usersdiv">				
			</div>			
		</div>
	</div>
	</c:when>
	<c:otherwise>
	<div class="container">
		Sorry, No Clients found
	</div>
	</c:otherwise>
	</c:choose>
</div>
