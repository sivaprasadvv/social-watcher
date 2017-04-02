<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="propertyForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="management.property.create.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="propertyName"><fmt:message key="management.property.column.name"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="propertyName" name="propertyName" value="" maxlength="100" placeholder="Property Name">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="propertyValue"><fmt:message key="management.property.column.value"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="propertyValue" name="propertyValue" value="" maxlength="100" placeholder="Property Value">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.create"/> <fmt:message key="management.property"/></button>
			<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></button>
		</div>		
	</form>
</div>