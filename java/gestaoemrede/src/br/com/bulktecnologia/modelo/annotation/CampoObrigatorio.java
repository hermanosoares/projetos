package br.com.bulktecnologia.modelo.annotation;
/**
 * Esta anotação indica que o campo é obrigatorio.
 * 
 * <b>Workaround encontrada para evitar 'detached-entities' no entitymanager em situações de falha de validação
 * quando usado o Hibernate Validator, tornando a transação irrecuperável.</b>
 *
 * 
 * @author hsoares
 *
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface CampoObrigatorio {
	String nomeCampo();
}
