package br.com.bulktecnologia.modelo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.ConfiguracaoEntity;
import br.com.bulktecnologia.modelo.entidade.TipoEncerramentoMatriculaEntity;
import br.com.bulktecnologia.modelo.enums.TipoConfiguracao;

@Name("ConfiguracaoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class ConfiguracaoDAO extends BaseDAO{

	
	
	
	public List<ConfiguracaoEntity> recuperaConfiguracoesGlobais(){
		Query q = this.getEm().createNamedQuery("ConfiguracaoEntity.recuperaConfiguracoesGlobais");
		return q.getResultList();
	}
	
	
	
	public List<ConfiguracaoEntity> recuperaConfiguracoesByInstituicao(Long idInstituicao){
		Query q = this.getEm().createNamedQuery("ConfiguracaoEntity.recuperaConfiguracoesByInstituicao");
		q.setParameter("idInstituicao",idInstituicao);
		return q.getResultList();
	}

/*
	public ConfiguracaoEntity recuperaConfiguracaoAtiva(Long idInstituicao, Boolean detached){
		
		String namedQuery = null;
		
		if (detached){
			namedQuery = "ConfiguracaoEntity.recuperaConfiguracaoAtivaDETACHEDByInstituicao";
		}
		else{
			namedQuery = "ConfiguracaoEntity.recuperaConfiguracaoAtivaByInstituicao";
		}
		
		Query q = this.getEm().createNamedQuery(namedQuery);
		q.setParameter("idInstituicao",idInstituicao);
		ConfiguracaoEntity c = null;
		try{
			 c = (ConfiguracaoEntity) q.getSingleResult();
			 return c;
		}
		catch(NoResultException nre){
			log.fatal("erro nenhuma configuracao ativa");
			return null;
		}
	}*/
	
	
	
	
	public List<TipoEncerramentoMatriculaEntity> recuperaTiposEncerramentoMatricula(Long idInstituicao){
		Query q =  this.getEm().createNamedQuery("TipoEncerramentoMatriculaEntity.recuperaTipoEncerramentoMatriculaPorInstituicao");
		q.setParameter("idinstituicao", idInstituicao);
		return q.getResultList();
	}
	
	
	
	
	
	
}
