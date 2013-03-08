<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="body">
		<spring:hasBindErrors name="universidade">
			<script type="text/javascript">
				$(document).ready(
						function() {
							$("div.control-group>div.controls>.error").parent()
									.parent().addClass("error");
						});
			</script>
		</spring:hasBindErrors>
		<form:form id="form" method="post" action="."
			modelAttribute="universidade" cssClass="form-horizontal">
			<fieldset>
				<legend>
					<spring:message code="label.universidade.cadastro" />
				</legend>
				<div class="control-group">
					<label class="control-label" for="nome"><spring:message code="label.nome" />
					</label>
					<div class="controls">
						<form:input path="nome" cssClass="span5" cssErrorClass="error" />
						<form:errors path="nome" cssClass="error help-inline inline"
							element="span" />
					</div>
				</div>
				<form:hidden path="id" />
				<div class="form-actions">
					<input id="btn_confirmar" type="button" class="btn btn-primary"
						value='<spring:message code="label.confirmar" />'>&nbsp;
				</div>
			</fieldset>
		</form:form>
		<hr>
	</c:param>
</c:import>

