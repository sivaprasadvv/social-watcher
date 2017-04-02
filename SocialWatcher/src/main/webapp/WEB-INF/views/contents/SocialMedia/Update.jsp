<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="searchEngineEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.se.edit.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="id"><fmt:message key="social.watcher.management.se.column.id"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="id" name="id" readOnly="true" value="${crudObj.id}" maxlength="100" data-reset="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name"><fmt:message key="social.watcher.management.se.column.name"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="name" name="name" readOnly="true" value="${crudObj.name}" maxlength="100" placeholder="Social Media Name"  data-reset="${crudObj.name}">
				<span class="help-inline"></span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label" for="isEnabled">Is enabled?</label>
			<div class="controls">
				<input type="checkbox" id="isEnabled" name="isEnabled" <c:if test="${crudObj.isEnabled=='true'}">checked</c:if>>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.save"/> <fmt:message key="social.watcher.management.se"/></button>
			<a href="${pageContext.request.contextPath}/management/socialmedia/update/${crudObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/socialmedia/list" id="cancelButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></a>
		</div>		
	</form>
</div>