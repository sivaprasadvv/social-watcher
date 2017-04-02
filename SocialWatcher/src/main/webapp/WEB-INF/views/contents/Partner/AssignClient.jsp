<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	<form id="assignClientForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="social.watcher.management.client.edit.legend"/></legend>
		<div class="control-group">
			<%-- <label class="control-label" for="id"><fmt:message key="social.watcher.management.partner.column.id"/><em>*</em></label> --%>
			<div class="controls">
				<input type="hidden" id="id" name="id" readOnly="true" value="${partnerObj.id}" maxlength="100" placeholder="Id"  data-reset="${partnerObj.id}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="partnerName"><fmt:message key="social.watcher.management.partner.column.partnerName"/><em>*</em></label>
			<div class="controls">
				<input type="text" id="partnerName" name="partnerName" readOnly="true" value="${partnerObj.partnerName}" maxlength="100" placeholder="Partner Name"  data-reset="${partnerObj.partnerName}">
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">  
            <label class="control-label" for="clients"><fmt:message key="social.watcher.management.clients"/><em>*</em></label>  
            <div class="controls">  
              <select class="multiselect" multiple="multiple" id="clients" name="clients">  
	              <c:forEach items="${clients}" var="client">
	              	<option value="${client.id}">${client.clientName}</option>
	              </c:forEach>                                  
              </select> 
              <span class="help-inline"></span> 
            </div>  
          </div> 			
		<div class="form-actions">
			<button id="saveButton" type="submit" class="btn btn-small btn-primary"><fmt:message key="social.watcher.management.operation.save"/> <fmt:message key="social.watcher.management.partner"/></button>
			<a href="${pageContext.request.contextPath}/management/partner/update/${partnerObj.id}" id="resetButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.reset"/></a>
			<a href="${pageContext.request.contextPath}/management/partner/list" id="cancelButton" class="btn btn-small"><fmt:message key="social.watcher.management.operation.cancel"/></a>
		</div>		
	</form>
</div>