<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="clientEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.client.edit.legend"/></legend>
		<div class="control-group">
			<%-- <label class="control-label" for="id"><fmt:message key="social.watcher.management.client.column.id"/><em>*</em></label> --%>
			<div class="controls">
				<input type="hidden" id="id" name="id" readOnly="true" value="${crudObj.id}" maxlength="100" data-reset="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="clientName"><fmt:message key="social.watcher.management.client.column.clientName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="clientName" name="clientName" readOnly="true" value="${crudObj.clientName}" maxlength="100" placeholder="Client Name"  data-reset="${crudObj.clientName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="social.watcher.management.client.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="${crudObj.title}" maxlength="100" placeholder="Title"  data-reset="${crudObj.title}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="social.watcher.management.client.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" value="${crudObj.description}" maxlength="200" placeholder="Description"  data-reset="${crudObj.description}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">  
            <label class="control-label" for="users">Users</label>  
            <div class="controls">  
              <select class="multiselect" multiple="multiple" id="users" name="users">  
	              <c:forEach items="${crudObj.users}" var="user">
	              	<option value="${user.id}" selected>${user.userName}</option>
	              </c:forEach>                                  
              </select> 
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
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.save"/> <fmt:message key="social.watcher.management.client"/></button>
			<a href="${pageContext.request.contextPath}/management/clients/update/${crudObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/clients/list" id="cancelButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></a>
		</div>		
	</form>
</div>