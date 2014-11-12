package br.com.bulktecnologia.controle.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.bulktecnologia.modelo.entidade.EnsinoEntity;
import br.com.bulktecnologia.modelo.enums.TipoRelatorio;
@Name("relatorioService")
@Scope(ScopeType.CONVERSATION)
public class RelatorioService implements Serializable {

	private TipoRelatorio relatorioSelecionado;

	@In
	private EntityManager entityManager;

	@In
	private HttpServletResponse response;
	
	
	
	public void gerarRelatorio(){
		try {
			
			response.setContentType("application/pdf");
			
			InputStream inReport = FacesContext.getCurrentInstance().getExternalContext()
			.getResourceAsStream("/WEB-INF/reports/RelatorioAcessos.jasper");
			   
			//JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", false);
			
			JasperReport report = (JasperReport)JRLoader.loadObject(inReport);
			
			Collection result = this.entityManager.createQuery("from AuditoriaAcessoEntity").getResultList();
		
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(result);
			
			JasperPrint jp =  JasperFillManager.fillReport(report, null, ds);
			
			JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());
			
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public TipoRelatorio getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(TipoRelatorio relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}
	
}
