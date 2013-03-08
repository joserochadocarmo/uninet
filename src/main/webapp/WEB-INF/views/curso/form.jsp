<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="body">
		<spring:hasBindErrors name="curso">
			<script type="text/javascript">
				$(document).ready(function() {
							$("div.control-group>div.controls>.error").parent().parent().addClass("error");
				});
			</script>
		</spring:hasBindErrors>
		<form:form id="form" method="post" action="." modelAttribute="curso"
			cssClass="form-horizontal">
			<fieldset>
				<legend>
					<spring:message code="label.curso.cadastro" />
				</legend>
				
				<div class="control-group">
					<label class="control-label" for="universidade"><spring:message
							code="label.universidade" /> </label>

					<div class="controls">
						<form:select path="universidade.id" id="universidade" >
         					<form:option value="" label="--selecione--"></form:option>
         					<form:options items="${universidades}" itemValue="id"  itemLabel="nome" ></form:options>
     					</form:select>
					</div>
				</div>
				
				<div class="control-group">
					
					<br> <label class="control-label" for="nome"><spring:message
							code="label.nome" /> </label>

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