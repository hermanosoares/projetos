package br.com.bulktecnologia.modelo.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

import br.com.bulktecnologia.modelo.dto.ResultadoPesquisaAlunoDTO;
import br.com.bulktecnologia.modelo.entidade.AlunoEntity;
import br.com.bulktecnologia.modelo.entidade.InstituicaoEntity;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;
import br.com.bulktecnologia.modelo.entidade.RelacaoPessoaEntity;

@Name("AlunoDAO")
@AutoCreate
@Scope(ScopeType.STATELESS)
public class AlunoDAO extends BaseDAO{

	
	
	
	
	public ResultadoPesquisaAlunoDTO recuperaDtoAlunoByIdAluno(Long idAluno){
		try {
			Query  q = this.getEm().createNamedQuery("AlunoEntity.recuperaDTOAlunoByIdAluno");
			q.setParameter("idAluno", idAluno);
			return (ResultadoPesquisaAlunoDTO)q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	
	
	

	public AlunoEntity recuperaAlunoPorIdPessoa(Long idPessoa){

			String sql = "select a from AlunoEntity a where a.pessoa.id = :idpessoa";
			Query q =  getEm().createQuery(sql);
			q.setParameter("idpessoa", idPessoa);
			try {
				return (AlunoEntity) q.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
	}
	
	
	public Boolean AlunoAssociadoNaInstituicao(Long idPessoa,Long idInstituicao){
		//getEm().joinTransaction();
		String sql = "select ai from AlunoInstituicaoEntity ai where " +
					" ai.aluno.pessoa.id = :idPessoa and ai.instituicao.id = :idInstituicao";
		
		Query q = getEm().createQuery(sql);
		q.setParameter("idPessoa", idPessoa);
		q.setParameter("idInstituicao", idInstituicao);
		try {
			if (q.getResultList().size()>0){
				return Boolean.TRUE;			
			}
		} catch (NoResultException e) {
		}
		return false;
	}
	
	@Transactional
	public void insereAlunoNaInstituicao(AlunoEntity aluno,PessoaEntity pessoa, Long idInstituicao,String login){
		aluno.setPessoa(pessoa);
		InstituicaoEntity instituicao = getEm().find(InstituicaoEntity.class, idInstituicao);
		
		RelacaoPessoaEntity relacao = new RelacaoPessoaEntity();
		relacao.setPessoa(pessoa);
		relacao.setAluno(aluno);
		relacao.setInstituicao(instituicao);
		//relacao.setDtEntrada(new Date());
		
		getEm().persist(relacao);
	}
	

	
	

	
	
	
	
	
	/**
	 * 
	 * Verifica se ja existe o código do Aluno no EducaCenso informado na base de dados.
	 * 
	 * @param codEducaCenso
	 * @return AlunoEntity
	 * @author hsoares
	 */
	public AlunoEntity verificaCodigoEducaCenso(String codEducaCenso){
		
		//workaround para SMPC
		getEm().clear();
		Query q = getEm().createNamedQuery("AlunoEntity.validaCodigoEducaCenso");
		q.setParameter("idCodeducacenso", codEducaCenso);
		try{
			return (AlunoEntity) q.getSingleResult();
		}
		catch(NoResultException nre){
			return null;
		}
	}
	
	
	
	
	
}

