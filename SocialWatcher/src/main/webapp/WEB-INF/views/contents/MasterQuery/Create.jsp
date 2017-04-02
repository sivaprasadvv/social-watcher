<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="formsContent">
	<!-- Form container -->
	
	<div class="container-fluid">
    <div class="row-fluid">
   
    <div class="span8">
     <legend>
		<fmt:message key="master.query.list.legend" />
	</legend>		
		<table class="table table-stripped table-hover table-condensed">
		<c:forEach items="${queries}" var="qry" varStatus="index">
		<tr>
		<input type="hidden" id="id${index.count}" name="id${index.count}"
					readOnly="true" value="${qry.id}" data-reset="${qry.id}">
		<td><input type="text" id="query${index.count}"
						name="query${index.count}" readOnly="true"
						value="${qry.queryText}" data-reset="${qry.queryText}"> <span
						class="help-inline"></span>
			<input type="text" id="socialMedia${index.count}"
						name="socialMedia${index.count}" readOnly="true"
						value="${qry.socialMedia.name}" data-reset="${qry.socialMedia.name}"> <span
						class="help-inline"></span>
		<button id="editButton${index.count}" type="submit" class="btn btn-small btn-primary"
							onclick="javascript:fnEdit(${index.count});">
							<i class="icon-edit"></i>
							<fmt:message key="management.operation.edit" />
							<fmt:message key="master.query.text" />
						</button>
						<button id="updateButton${index.count}" type="submit" class="btn btn-small btn-success"
							onclick="javascript:fnUpdate(${index.count});" disabled>
							<i class="icon-edit"></i>
							<fmt:message key="management.operation.save" />
							<fmt:message key="master.query.text" />
						</button>
						<a href="${pageContext.request.contextPath}/masterquery/mlist"
						id="cancelButton" class="btn btn-small"><fmt:message
								key="management.operation.cancel" /></a></td>
		</tr>
		</c:forEach>
		</table>
		</div>
		
		 <div class="span4">
    <legend>
		<fmt:message key="master.query.create.legend" />
	</legend>
	<form id="masterQueryForm" class="form-inline" autocomplete="off"
		method="POST" action="">		
		<table class="table table-condensed">
		<tr>
		<td><label class="control-label" for="queryText"><fmt:message
								key="master.query.text" /><em>*</em></label></td>
		<td><input type="text" id="queryText" name="queryText" value=""
								maxlength="100" placeholder="Query String"> <span
								class="help-inline"></span></td>
		</tr>
		<c:if test="${userContext.role eq 'admin' || userContext.role eq 'partner'}">
		<tr>
		<td><label class="control-label" for=selectedClient><fmt:message
								key="social.watcher.management.client" /><em>*</em></label></td>
		<td><select class="required" name="selectedClient"
								id="selectedClient">
								<c:forEach items="${clients}" var="client">
									<option value="${client.id}">${client.clientName}</option>
								</c:forEach>
								<span class="help-inline"></span>
							</select>
							<br>
							<c:if test="${empty clients}">							
							<fmt:message key="social.watcher.management.operation.create"/> 
							<a href="${pageContext.request.contextPath}/management/clients/create" >
							<fmt:message key="social.watcher.management.client"/>
							</a>
							</c:if>
							</td>
		</tr>
		</c:if>
		<tr>
		<td><label class="control-label" for=api><fmt:message
								key="search.engine.api" /><em>*</em></label></td>		
		<td><select class="multiselect" multiple="multiple" name="socialmedia"
								id="socialmedia">
								<c:forEach items="${socialmedias}" var="socialmedia">
									<option value="${socialmedia.id}">${socialmedia.name}</option>
								</c:forEach>
								<span class="help-inline"></span>
							</select></td>
		</tr>
		<tr>
		<td></td>
		<td><button type="submit" class="btn  btn-small btn-primary">
								<i class="icon-plus"></i>
								<fmt:message key="management.operation.add" />
							</button>
							<button id="addCancelButton" type="button" class="btn  btn-small btn-danger">
								<fmt:message key="management.operation.cancel" />
							</button></td>
		</tr>
		</table>
	</form>
    </div>
		
    </div>
    </div>  
	
</div>
