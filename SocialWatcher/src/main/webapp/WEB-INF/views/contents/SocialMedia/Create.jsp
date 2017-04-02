<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<div id="formsContent">
	<!-- Form container -->
	<form id="searchEngineForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.se.create.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="name"><fmt:message key="social.watcher.management.se.column.name"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="name" name="name" value="" maxlength="100" placeholder="Social Media Name">
				<span class="help-inline"></span>
			</div>
		</div>			
		<div class="control-group">
			<label class="control-label" for="isEnabled">Is enabled?</label>
			<div class="controls">
				<input type="checkbox" id="isEnabled" name="isEnabled"  checked>
				<span class="help-inline"></span>
			</div>
		</div>	
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.create"/> <fmt:message key="social.watcher.management.se"/></button>
			<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></button>
		</div>		
	</form>
</div>