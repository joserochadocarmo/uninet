<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="body">
		<fieldset>
			<legend>
				<spring:message code="label.aluno.excluir" />
			</legend>
			<div class="control-group">
				<label class="control-label" for="nome"><b><spring:message
							code="label.universidade" />:</b> </label>
				<div class="controls">
				${aluno.curso.universidade.nome}
				</div>
				
				<label class="control-label" for="nome"><b><spring:message
							code="label.curso" />:</b> </label>
				<div class="controls">
				${aluno.curso.nome}
				</div>
				
				<label class="control-label" for="nome"><b><spring:message
							code="label.nome" />:</b> </label>
				<div class="controls">
				${aluno.nome}
				</div>
			</div>

			<div class="form-actions">
				<a class="btn btn-danger"
					href='${contextRoot}/aluno/delete/${curso.id}'> <spring:message
						code="MSG03" />
				</a>
			</div>
		</fieldset>

		<hr>
	</c:param>
</c:import>