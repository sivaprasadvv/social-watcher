<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="partnerEditForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.client.edit.legend"/></legend>
		<div class="control-group">
			<%-- <label class="control-label" for="id"><fmt:message key="social.watcher.management.partner.column.id"/><em>*</em></label> --%>
			<div class="controls">
				<input type="hidden" id="id" name="id" value="${crudObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="partnerName"><fmt:message key="social.watcher.management.partner.column.partnerName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="partnerName" name="partnerName" readOnly="true" value="${crudObj.partnerName}" maxlength="100" placeholder="Partner Name"  data-reset="${crudObj.partnerName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="title"><fmt:message key="social.watcher.management.partner.column.title"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="title" name="title" value="${crudObj.title}" maxlength="100" placeholder="Title"  data-reset="${crudObj.title}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description"><fmt:message key="social.watcher.management.partner.column.description"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="description" name="description" value="${crudObj.description}" maxlength="100" placeholder="Description"  data-reset="${crudObj.description}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">  
            <label class="control-label" for="clients">Clients</label>  
            <div class="controls">  
              <select class="multiselect" multiple="multiple" id="clients" name="clients">  
	              <c:forEach items="${crudObj.clients}" var="client">
	              	<option value="${client.id}" selected>${client.clientName}</option>
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
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.save"/> <fmt:message key="social.watcher.management.partner"/></button>
			<a href="${pageContext.request.contextPath}/management/partner/update/${crudObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/partner/list" id="cancelButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></a>
		</div>		
	</form>
</div>