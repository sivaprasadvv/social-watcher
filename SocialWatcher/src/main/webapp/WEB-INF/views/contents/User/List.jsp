<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-stripped table-condensed"
	id="usertable">
	<thead>
		<tr>
			<th><fmt:message key="management.user.column.id"/></th>
			<th><fmt:message key="management.user.column.userName"/></th>
			<th><fmt:message key="management.user.column.title"/></th>
			<th><fmt:message key="management.user.column.description"/></th>
			<th><fmt:message key="management.user.column.isEnabled"/></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<jsp:include page="../../sections/modal.jsp" />
