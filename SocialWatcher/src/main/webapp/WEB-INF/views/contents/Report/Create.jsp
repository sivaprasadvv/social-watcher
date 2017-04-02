<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div id="formsContent">
	<!-- Form container -->
	<form id="createReportForm" class="form-horizontal" autocomplete="off" method="POST" action="">
	<legend><fmt:message key="report.create.legend"/></legend>
	
	<div class="control-group">
    <label class="control-label" for="queryString"><fmt:message key="report.search.words"/><em>*</em></label>
    <c:forEach items="${queries}" var="lqueries" varStatus="index">    
    <div class="span2">
   	<c:forEach items="${lqueries}" var="query" varStatus="index">
        <label class="checkbox">
            <input type="checkbox" value="${query}" id="query${index.count}" name="query"> ${query}
        </label> 
        </c:forEach>
    </div>    
    </c:forEach>
    </div>
    
    <div class="control-group">
      <label class="control-label" for="daterange"><fmt:message key="report.search.period"/><em>*</em></label>
      <div class="controls">
        <div class="input-prepend">
          <span class="add-on"><i class="icon-calendar"></i></span><input type="text" name="daterange" id="daterange" />
        </div>
      </div>
    </div>
    
	<div class="form-actions">
		<button type="submit" class="btn btn-small"><i class="icon-search"></i><fmt:message key="management.operation.search"/></button>
		<button id="cancelButton" type="button" class="btn btn-small"><fmt:message key="management.operation.cancel"/></button>
	</div>		
	</form>
</div>
