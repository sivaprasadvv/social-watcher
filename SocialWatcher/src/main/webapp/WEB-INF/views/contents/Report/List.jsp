<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-stripped table-condensed"
	id="reporttable">
	<thead>
		<tr>
			<th><fmt:message key="audit.column.id"/></th>
			<th><fmt:message key="audit.column.title"/></th>
			<th><fmt:message key="audit.column.description"/></th>
			<th><fmt:message key="audit.column.url"/></th>
			<th><fmt:message key="audit.column.source"/></th>
			<th><fmt:message key="audit.column.sourceId"/></th>
			<th><fmt:message key="audit.column.sourceCreatedDate"/></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<jsp:include page="../../sections/modal.jsp" />
