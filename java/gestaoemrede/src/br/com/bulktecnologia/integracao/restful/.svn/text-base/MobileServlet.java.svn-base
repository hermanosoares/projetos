package br.com.bulktecnologia.integracao.restful;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bulktecnologia.dp.EntityManagerFactorySingleton;
import br.com.bulktecnologia.modelo.entidade.PessoaEntity;


public class MobileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance().getDefaultEntityManagerFactory();
		
		EntityManager em =  emf.createEntityManager();
		
		
		PrintWriter out = response.getWriter();
		

			Query q =  em.createQuery("select new PessoaEntity(p.nome) from PessoaEntity p");
			List<PessoaEntity> pessoas = q.getResultList();
			
			for (PessoaEntity p: pessoas){
				out.write(p.getNome());
				out.write("<br/>");
			}
			
		em.close();	
		
	}

}
