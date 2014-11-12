package br.com.bulktecnologia.teste;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class JasperTOJrXml {

	public static void main(String[] args) throws JRException {
		String fname = "rptAuditoria.jasper";
		JasperReport report = (JasperReport) JRLoader.loadObject("c:\\temp\\relatorios\\"+fname);
		JRXmlWriter.writeReport(report, "C:\\temp\\relatorios\\generated\\"+fname+".jrxml", "UTF-8");
		System.out.println("Report Genereated ! "+fname);
	}
}
