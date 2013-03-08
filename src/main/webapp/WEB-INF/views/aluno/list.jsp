<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="body">
    <legend><spring:message code="label.aluno.lista"/></legend>
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th><spring:message code="label.id"/></th>
                <th><spring:message code="label.aluno"/></th>
                <th><spring:message code="label.aluno.matricula"/></th>
                <th><spring:message code="label.curso"/></th>
                <th><spring:message code="label.universidade"/></th>
                <th><spring:message code="label.acoes"/></th>                
            </tr>
            <c:forEach items="${paginas.content}" var="pagina">
                <tr>
                    <td>${f:h(pagina.id)}</td>
                    <td>${f:h(pagina.nome)}</td>
                    <td>${f:h(pagina.matricula)}</td>
                    <td>${f:h(pagina.curso.nome)}</td>
                    <td>${f:h(pagina.curso.universidade.nome)}</td>
                    <td>
                    	<a href='${contextRoot}/aluno/edit/${pagina.id}'><spring:message code="label.editar"/></a> | 
                        <a href='${contextRoot}/aluno/excluir/${pagina.id}'><spring:message code="label.excluir"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <jsp:include page="/WEB-INF/views/common/paginacao.jsp" />
        
        <div class="form-actions">
            <a href='${contextRoot}/aluno/form/' class="btn btn-primary">
            	<spring:message code="label.novo"/>
            </a>
        </div>
    </c:param>
</c:import>