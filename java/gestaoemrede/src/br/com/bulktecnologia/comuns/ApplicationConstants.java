package br.com.bulktecnologia.comuns;

import java.util.Locale;

import br.com.bulktecnologia.modelo.enums.Skins;

public interface ApplicationConstants {


	public interface Annotation{
		public static final String INSERE = "insere";
		public static final String EXCLUI = "exclui";
		public static final String SELECIONA = "seleciona";
		public static final String ATUALIZA = "atualiza";
	}
	
	
	public interface Paginas{
		
		public static final String PAGE_PRINCIPAL = "/principal.faces";
		public static final String PAGE_LOGIN = "/login.faces";
		public static final String PAGE_ADVERTENCIA = "/advertencia.faces";
		public static final String PAGE_SEMSESSAO = "/semsessao.faces";
		public static final String PAGE_SEM_ACESSO_URL = "/urlnaopermitida.faces";
	}
	
	public interface CasoNavegacional{
		public static final String CASE_PAGE_PRINCIPAL = "PAGE_PRINCIPAL";
		public static final String CASE_LOGIN = "PAGE_LOGIN";
		public static final String CASO_MENU_ALUNO_CADASTRO = "PAGE_INSERCAO_UC_ALUNO_CADASTRO";
		
	}
	
	public interface Seguranca{
		public static final String CHAVE_COOKIE_SESSAO_USUARIO = "cookie_sessao_usuario";
		public static final String FLAG_SKINNABILITY_RICHFACES = "/a4j/";
	}
	
	public interface Parametros{
		
		public static final String NAMEDQUERY_SELECT_COUNT = "namedquerySelectCountParameter";
		
		public static final String QUERY_SELECT_COUNT = "querySelectCountParameter";
		
		public static final String[] LOGINS_RESERVADOS = {"app","admin","root","adm"};
		
		public static final Skins SKIN_PADRAO = Skins.classic;
		
		public static final Integer MIN_LENGTH_SENHA = 6;
		
		public static final Integer MIN_LENGTH_LOGIN = 6;
		
		public static final String pwdNoReply = "account@noreply@123";
		
		public static final Integer IDADE_MAIORIDADE = 18;
		
		public static final Integer MAX_RESULTADO_QUERY_JPA = 10;
		
		public static final Integer MAX_PAGINAS_COMBO = 10;
		
		public static final Integer MAX_REDEFINICOES_ESQUECI_SENHA_POR_DIA = 3;
		
		public static final String OBSERVACAO_ACESSO_ADMIN = "acesso-administrativo"; 
		
		public static final String UsuarioAplicacao = "app";
		
		/**
		 * locale default da aplicacao "pt" BR
		 */
		public static final Locale DefaultLocale = new Locale("pt","BR");
		public static final String DefaultLocaleName = "messages";
		
		public static final String IMAGES_PACKAGE ="/images";
		public static final String BASE_ENTITY_PACKAGE = "br.com.bulktecnologia.modelo.entidade.";
		public static final String CHAVE_JNDI_DATASOURCE = "java:/comp/env/jdbc/educaweb";
		public static final String BASE_PACKAGE_CRUDMETAOBJECT = "br.com.bulktecnologia.configuration.crud";
		public static final String FACADE_PADRAO_IMPL = "br.com.bulktecnologia.dp.FacadeImpl";
		public static final String MYFACES_EXTENSION_URL = ".faces";
		/**
		 * caminho base dos casos de uso dentro do projeto e visivel na url
		 */
		public static final String RAIZ_BASE_UC = "/uc/";
		public static final String CSS_EXTENSION = ".css";
		
		
		/**
		 * valor de um combo quando nao h� selecao.
		 */
		public static final String DEFAULT_VALUE_COMBO = "-";

		/**
		 * ID da sequence que identificado (super-usuário APLICACAO)
		 */
		public static final long ID_USUARIO_APLICACAO = 0;
	}

	
	public interface MetaObject{
		
		public static final String ATRIBUTO_SELECAO_QUERY = "att_query_selecao";
		public static final String ATRIBUTO_SELECAO_CASO_NAVEGACIONAL = "att_selecao_caso_navegacional";
		public static final String ATRIBUTO_ENTIDADE_RESULTANTE = "att_clazz_entity_resultante";
		public static final String ATRIBUTO_SELECAO_TIPO_PARAMETROS_ENTRADA = "att_selecao_tipo_param_entrada";
		public static final String ATRIBUTO_SELECAO_USA_PARAMETROS_ENTRADA = "att_selecao_usa_param_entrada";
		
		
		public static final String ATRIBUTO_ATUALIZACAO_QUERY = "att_query_atualizacao";
		public static final String ATRIBUTO_ATUALIZACAO_TIPO_PARAMETROS_ENTRADA = "att_atualiazacao_tip_param_entrada";
		public static final String ATRIBUTO_ATUALIZACAO_NOME_PROPRIEDADES_ENTIDADE = "att_atualizacao_nome_propriedades_entidade";
		
		
		public static final String ATRIBUTO_EXCLUSAO_QUERY = "att_query_exclusao";
	
		
		public static final String ATRIBUTO_INSERCAO_QUERY = "att_query_insercao";
		public static final String ATRIBUTO_INSERCAO_CASO_NAVEGACIONAL = "att_insert_caso_navegacional";
		public static final String ATRIBUTO_INSERCAO_TIPO_PARAMETROS_ENTRADA = "att_insercao_tp_param_entrada";
		public static final String ATRIBUTO_INSERCAO_QTDE_REGISTROS = "att_insert_qtde_registros";
		public static final String ATRIBUTO_INSERCAO_NOMES_PROPRIEDADES = "att_insert_nomes_propriedades";
		
	
		public static final String ATRIBUTO_PAGINACAO_QUERY = "att_query_paginacao";
		
		public static final String ATRIBUTO_PAGINACAO_QTDE_REGISTROS = "att_qtde_reg_paginacao";
		
		public static final String ATRIBUTO_PAGINACAO_USA = "att_usa_paginacao";
		
	
		public static final String ATRIBUTO_EDICAO_CASO_NAVEGACIONAL = "att_edicao_caso_navegacional";
		public static final String ATRIBUTO_EDICAO_QUERY = "att_edicao_query";	
		
		public static final String ATRIBUTO_COMBOS = "att_combo";
		
	}
	
	
}
