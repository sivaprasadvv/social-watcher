<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="userEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="management.user.edit.legend"/></legend>
		<div class="control-group">
			<%-- <label class="control-label" for="id"><fmt:message key="management.user.column.id"/><em>*</em></label> --%>
			<div class="controls">
				<input type="hidden" id="id" name="id" readOnly="true" value="${crudObj.id}" maxlength="100" data-reset="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="userName"><fmt:message key="management.user.column.userName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="userName" name="userName" readOnly="true" value="${crudObj.userName}" maxlength="100" placeholder="User Name"  data-reset="${crudObj.userName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="management.user.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="${crudObj.title}" maxlength="100" placeholder="Title" data-reset="${crudObj.title}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="management.user.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" maxlength="50" value="${crudObj.description}"  placeholder="Description" data-reset="${crudObj.description}">
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
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="management.operation.save"/> <fmt:message key="management.user"/></button>
			<a href="${pageContext.request.contextPath}/management/users/update/${crudObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/users/list" id="cancelButton" class="btn btn-small"><fmt:message key="management.operation.cancel"/></a>
		</div>		
	</form>
</div>