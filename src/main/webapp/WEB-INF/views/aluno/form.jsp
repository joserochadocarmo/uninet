<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="body">
		<spring:hasBindErrors name="aluno">
			<script type="text/javascript">
				$(document).ready(function() {
							$("div.control-group>div.controls>.error").parent().parent().addClass("error");
				});
				
				function getUniversidadeData() {
					   $.getJSON("/universidade/combo",{ }, 
						function(customerAvailable) {
					   		if(customerAvailable.available) {
					   		}
					      
					   	});
				}
				
			</script>
		</spring:hasBindErrors>
		<form:form id="form" method="post" action="." modelAttribute="aluno"
			cssClass="form-horizontal">
			<fieldset>
				<legend>
					<spring:message code="label.aluno.cadastro" />
				</legend>
				
				<div id="universidadeGrp" class="control-group">
					<label class="control-label" for="universidade"><spring:message
							code="label.universidade" /> </label>

					<div class="controls">
						<form:select path="curso.universidade.id" id="universidade" >
         					<form:option value="" label="--selecione--"></form:option>
         					<form:options items="${universidades}" itemValue="id"  itemLabel="nome" ></form:options>
     					</form:select>
					</div>
				</div>
				
				<div id="cursoGrp" class="control-group">
					<label class="control-label" for="curso"><spring:message
							code="label.curso" /> </label>

					<div class="controls">
						<form:select path="curso.id" id="curso" >
         					<form:option value="" label="--selecione--"></form:option>
         					<form:options items="${cursos}" itemValue="id"  itemLabel="nome" ></form:options>
     					</form:select>
					</div>
				</div>
								
				<div id="alunoGrp" class="control-group">
					
					<br> <label class="control-label" for="nome"><spring:message
							code="label.nome" /> </label>

					<div class="controls">
						<form:input path="nome" cssClass="span5" cssErrorClass="error" />
						<form:errors path="nome" cssClass="error help-inline inline"
							element="span" />
					</div>

				</div>
				
				<div id="alunoGrp" class="control-group">
					
					<br> <label class="control-label" for="nome"><spring:message
							code="label.aluno.matricula" /> </label>

					<div class="controls">
						<form:input path="matricula" cssClass="span5" cssErrorClass="error" />
						<form:errors path="matricula" cssClass="error help-inline inline"
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