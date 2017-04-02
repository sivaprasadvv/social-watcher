<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="roleForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="management.role.create.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="roleName"><fmt:message key="management.role.column.roleName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="roleName" name="roleName" value="" maxlength="100" placeholder="Role Name">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="management.role.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="" maxlength="100" placeholder="Title">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="management.role.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" value="" maxlength="100" placeholder="Description">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.create"/> <fmt:message key="management.role"/></button>
			<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></button>
		</div>		
	</form>
</div>