package br.com.uninet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Validator;

/**
 * Classe abstrata contendo fun�oes comuns a todos os controllers. 
 * 
 * @author jose.rocha
 *
 */
public abstract class AbastractController implements Validator{

	protected static final int PAGINA_ATUAL = 0;
    protected static final int PAGINA_LIMITE = 5;
    
    protected static final Logger logger = LoggerFactory.getLogger(AbastractController.class);

    /**
     * Retorna a p�gina atual. 
     */
    protected int getPagina(final Integer page){
    	return page != null ? page : PAGINA_ATUAL;
    }


    @Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
