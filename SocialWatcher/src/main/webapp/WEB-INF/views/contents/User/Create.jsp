<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<div id="formsContent">
	<!-- Form container -->
	<form id="userForm" class="form-horizontal" autocomplete="off" method="POST" action="">	
    <legend><fmt:message key="management.user.create.legend"/></legend>
		<div class="control-group">
			<label class="control-label" for="userName"><fmt:message key="management.user.column.userName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="userName" name="userName" value="" maxlength="100" placeholder="User Name">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="management.user.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="" maxlength="100" placeholder="Title">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="management.user.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" maxlength="50" value=""  placeholder="Description">
				<span class="help-inline"></span>
			</div>
		</div> 		   
		<div class="control-group">
			<label class="control-label" for="name"><fmt:message key="management.login.column.name"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="name" name="name" placeholder="Email" value="" maxlength="100">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password"><fmt:message key="management.login.column.password"/><em>*</em></label>
			<div class="controls">
				<input type="password" id="password" name="password" maxlength="50" value=""  placeholder="Password">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password_confirm"><fmt:message key="management.login.column.passwordConfirmation"/><em>*</em></label>
			<div class="controls">
				<input type="password" id="password_confirm" name="password_confirm" maxlength="50" value=""  placeholder="Confirm Password">
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
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="management.operation.create"/> <fmt:message key="management.user"/></button>
			<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="management.operation.cancel"/></button>
		</div>		
	</form>
</div>