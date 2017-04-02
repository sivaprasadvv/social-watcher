<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="formsContent">
	<!-- Form container -->
	<legend>
		<fmt:message key="client.by.partner.list.legend" />
	</legend>
	<c:choose>
	<c:when test="${ not empty partners }">
	<div class="tabbable">
		<!-- Only required for left/right tabs -->
		<div id="navigation">
			<ul class="nav nav-tabs">
				<c:forEach items="${partners}" var="partner" varStatus="x">
					<li class=""><a href="#" id="${partner.id}" data-toggle="tab">${partner.partnerName}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="tab-content">
			<div class="tab-pane active" style="width: auto; display: none;"
				id="clientsdiv">				
			</div>			
		</div>
	</div>
	</c:when>
	<c:otherwise>
	<div class="container">
		Sorry, No partners found
	</div>
	</c:otherwise>
	</c:choose>
</div>
