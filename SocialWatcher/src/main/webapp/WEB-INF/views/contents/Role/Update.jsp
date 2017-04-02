<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="roleEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="management.role.edit.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="id"><fmt:message key="management.role.column.id"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="id" name="id" readOnly="true" value="${crudObj.id}" maxlength="100" data-reset="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="roleName"><fmt:message key="management.role.column.roleName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="roleName" name="roleName" readOnly="true" value="${crudObj.roleName}" maxlength="100" placeholder="Role Name"  data-reset="${crudObj.roleName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="management.role.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="${crudObj.title}" maxlength="100" placeholder="Title"  data-reset="${crudObj.title}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="management.role.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" value="${crudObj.description}" maxlength="100" placeholder="Description"  data-reset="${crudObj.description}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="management.operation.save"/> <fmt:message key="management.role"/></button>
			<a href="${pageContext.request.contextPath}/management/role/update/${crudObj.id}" id="resetButton" class="btn btn-small btn-inverse"><fmt:message key="management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/roles/list" id="cancelButton" class="btn btn-small"><fmt:message key="management.operation.cancel"/></a>
		</div>		
	</form>
</div>