<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" 	href='${contextRoot}/css/main.css'>
<link rel="stylesheet" type="text/css" 	href='${contextRoot}/css/bootstrap/bootstrap.min.css'>
<link rel="stylesheet" type="text/css" 	href='${contextRoot}/css/redmond/jquery-ui-1.8.23.custom.css'>

<script type="text/javascript" 	src='${contextRoot}/js/jquery-1.7.1.min.js'></script>
<script type="text/javascript" 	src='${contextRoot}/js/jquery-ui-1.8.23.custom.min.js'></script>

<title><spring:message code="label.uninet" /></title>

<script type="text/javascript">
$(document).ready(function() {
	
	//Janela de confimação
	$('#btn_confirmar').click(function() {
		
		$('#msg_confirmacao').empty();
		
		//Se id estiver preenchido é uma alteração, senão é inclusão;
		if($("input[id='id']").val() == ''){
			
			//Pega no resource(message.properties) a chave correspondente.
			var msg = '<spring:message code="MSG01"/>';
			$('#msg_confirmacao').append(msg);
			
		} else {
			
			var msg = '<spring:message code="MSG02"/>';
			$('#msg_confirmacao').append(msg);
			
		}
		
		$('#dialog-confirm').dialog('open');
	});
	
	//Dialog de confirmação
	$('#dialog-confirm').dialog({
		autoOpen : false,
		modal : true,
		resizable : false,
		buttons : {
			'<spring:message code="label.sim"/>' : function() {
				$('#form').submit();
				$(this).dialog("close");
			},
			'<spring:message code="label.nao"/>' : function() {
				$(this).dialog("close");	
			}
		}
	});
});
	
</script>

</head>


<div id="dialog-confirm" title='<spring:message code="label.confirmacao"/>'>
	<p id="msg_confirmacao"></p>
</div>

<body>
	<div class="container-fluid">
		
			<div class="page-header">
				<h1>
					<spring:message code="label.uninet"/>
				</h1>
			</div>
			
			<div class="row-fluid">
				<div class="span2">
					<h2>Menu</h2>
			        <ul>
			            <li><a href='${contextRoot}/aluno/list/'>Alunos</a></li>
			            <li><a href='${contextRoot}/curso/list/'>Cursos</a></li>
			            <li><a href='${contextRoot}/universidade/list/'>Universidades</a></li>
			        </ul>
				</div>
			    <div class="span9">
			    	<div class="largura746">
			    		${param.body}
			    	</div>
			      
			    </div>
			</div>
			<div class="row-fluid">
				<footer>
					<p>&copy; UFG 2012</p>
				</footer>
			</div>
		
	</div>
</body>
</html>