<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="propertyEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="management.property.edit.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="id"><fmt:message key="management.property.column.id"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="id" name="id" readOnly="true" value="${crudObj.id}" maxlength="100" data-reset="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="propertyName"><fmt:message key="management.property.column.name"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="propertyName" name="propertyName" readOnly="true" value="${crudObj.propertyName}" maxlength="100" placeholder="Property Name"  data-reset="${crudObj.propertyName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="propertyValue"><fmt:message key="management.property.column.value"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="propertyValue" name="propertyValue" value="${crudObj.propertyValue}" maxlength="100" placeholder="Property Value"  data-reset="${crudObj.propertyValue}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="management.operation.save"/> <fmt:message key="management.property"/></button>
			<a href="${pageContext.request.contextPath}/management/properties/update/${crudObj.id}" id="resetButton" class="btn btn-small btn-inverse"><fmt:message key="management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/properties/list" id="cancelButton" class="btn btn-small"><fmt:message key="management.operation.cancel"/></a>
		</div>		
	</form>
</div>