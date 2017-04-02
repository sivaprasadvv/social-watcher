<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-stripped table-condensed"
	id="clienttable">
	<thead>
		<tr>
			<th><fmt:message key="social.watcher.management.client.column.id"/></th>
			<th><fmt:message key="social.watcher.management.client.column.clientName"/></th>
			<th><fmt:message key="social.watcher.management.client.column.title"/></th>
			<th><fmt:message key="social.watcher.management.client.column.description"/></th>
			<th><fmt:message key="social.watcher.management.client.column.isEnabled"/></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<jsp:include page="../../sections/modal.jsp" />
