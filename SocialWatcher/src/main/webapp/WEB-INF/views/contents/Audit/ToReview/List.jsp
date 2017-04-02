<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-stripped table-condensed"
	id="audittoreviewtable">
	<thead>
		<tr>
			<th><fmt:message key="audit.column.id"/></th>
			<th><fmt:message key="audit.column.profileImage"/></th>	
			<th><fmt:message key="audit.column.source"/></th>		
			<th><fmt:message key="audit.column.title"/></th>
			<th><fmt:message key="audit.column.description"/></th>			
			<th><fmt:message key="audit.column.url"/></th>			
			<th><fmt:message key="audit.column.sourceCreatedDate"/></th>
			<th><fmt:message key="audit.column.place"/></th>
			<th><fmt:message key="audit.column.review"/></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<jsp:include page="../../../sections/modal.jsp" />
