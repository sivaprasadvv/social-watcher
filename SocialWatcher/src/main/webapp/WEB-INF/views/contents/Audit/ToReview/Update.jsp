<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<div id="formsContent">
	<!-- Form container -->
	<form id="auditToReviewUpdateForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="audit.edit.legend"/></legend>
		<div class="control-group">
			<%-- <label class="control-label" for="id"><fmt:message key="audit.column.id"/><em>*</em></label> --%>
			<div class="controls">
				<input type="hidden" id="id" name="id" readOnly="true" value="${crudObj.id}" data-reset="${crudObj.id}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="audit.column.description"/><em>*</em></label>
			<div class="controls">
				${crudObj.description}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="url"><fmt:message key="audit.column.url"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="url" name="url" value="${crudObj.url}" readOnly="true" maxlength="100" placeholder="URL"  data-reset="${crudObj.url}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="socialMediaLogo"><fmt:message key="audit.column.source"/><em>*</em></label>
			<div class="controls">
				${crudObj.socialMediaLogo}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="comment"><fmt:message key="audit.column.comment"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="comment" name="comment" <c:if test="${not empty crudObj.comment }">readOnly="true"</c:if> placeholder="Comment" value="${crudObj.comment}" data-reset="${crudObj.comment}">
				<span class="help-inline"></span>
				<c:if test="${ not empty crudObj.comment }"><span class="help-inline"><fmt:message key="audit.edit.comment.already.added"/></span></c:if>
			</div>
		</div>
		
		<div class="hidden">
			<input type="hidden" id="reviewedDate" name="reviewedDate" value="<%=new java.util.Date().getTime()%>">
			<input type="hidden" id="reviewedBy" name="reviewedBy" value="${userContext.username}">
		</div>
		
		<div class="form-actions">
			<c:if test="${ empty crudObj.comment }">
			<button id="postiveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="management.operation.save"/></button>			
			<a href="${pageContext.request.contextPath}/audit/update/${crudObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="management.operation.reset"/></a>
			</c:if>
			<a href="${pageContext.request.contextPath}/audit/toreview/list" id="cancelButton" class="btn btn-small"><fmt:message key="management.operation.cancel"/></a>
		</div>		
	</form>
</div>