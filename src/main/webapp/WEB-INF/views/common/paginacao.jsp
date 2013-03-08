<p>${f:h(paginas.number + 1)}/${f:h(paginas.totalPages)}</p>
<div class="pagination">
	<ul>
		<li><c:if test="${!paginas.isFirstPage()}">
				<a href='<c:url value="?page=0" />'>&laquo; <spring:message code="label.inicio" /></a>
			</c:if></li>
		<li><c:if test="${paginas.hasPreviousPage()}">
				<a href='<c:url value="?page=${f:h(paginas.number - 1)}" />'>&lt;
					<spring:message code="label.proxima" /></a>
			</c:if></li>
		<li><c:if test="${paginas.hasNextPage()}">
				<a href='<c:url value="?page=${f:h(paginas.number + 1)}" />'><spring:message code="label.inicio" />
					&gt;</a>
			</c:if></li>
		<li><c:if test="${!paginas.isLastPage()}">
				<a href='<c:url value="?page=${f:h(paginas.totalPages - 1)}" />'><spring:message code="label.proxima" />
					&raquo;</a>
			</c:if></li>
	</ul>
</div>