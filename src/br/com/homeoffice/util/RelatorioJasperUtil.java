package br.com.homeoffice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.homeoffice.dao.impl.ConexaoDefaultDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 */
public class RelatorioJasperUtil implements Serializable {

	/**
	 * Atributos Estaticos, para definir os tipos de relatorios os tipos sao
	 * PDF,EXCEL,HTML E OFFICE
	 */
	private static final long serialVersionUID = -1957352526319806081L;
	private static final int RELATORIO_PDF = 1;
	private static final int RELATORIO_EXCEL = 2;
	private static final int RELATORIO_HTML = 3;
	private static final int RELATORIO_OPEN_OFFICE = 4;

	/**
	 * Metodo que gera o relatorio e arquiv dentro do proprio sistema para o
	 * usuario poder exporta-lo sendo assim temos os parametros abaixo que nos
	 * dao a condicao universal de gerar os relatorios o Principio desse meto e
	 * gerar o relatorio , em seguinda verificamos onde o arquivo se encontra ,
	 * localizamos esse arquivo e passamos ele por parametros logo apos temos a
	 * condicao que para que tipo de exportacao o usuario final ira desejar
	 * exportar apos o relatorio ser exportado automaticamente ele e excluido da
	 * pasta raiz do sistema
	 * 
	 * @param parametrosRelatorio
	 * @param nomeRelatorioJasper
	 * @param nomeRelatorioSaidaJasper
	 * @param tipoRelatorio
	 * @return
	 * @throws Exception
	 */
	public StreamedContent geraRelatorio(Map<String, Object> parametrosRelatorio, String nomeRelatorioJasper,
			String nomeRelatorioSaidaJasper, int tipoRelatorio) throws Exception {
		// pega o retorno do arquivo
		StreamedContent arquivoRetorno = null;

		// abrimos um try catch para tentativa de gerar o arquivo com o banco de
		// dados

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			Connection con = this.getConexao();
			// pegamos o arquivo no caso o relatorio gerado na pasta raiz do
			// sistema
			String caminhoRelatorio = context.getExternalContext().getRealPath("/resources/relatorios");
			String caminhoArquivojasper = caminhoRelatorio + File.separator + nomeRelatorioJasper + ".jasper";

			String caminhoArquivoRelatorio = null;
			// Criamos esse arquivo de acordo com os caminhos acima e as
			// extensoes
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoArquivojasper);
			JasperPrint impressaoJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, con);

			// criamos os atributos para exportacao
			JRExporter tipoRelatorioExportado = null;
			String extensaoArquivoExportado = "";
			File arquivoGerado = null;
			// e Aaqui criamos uma condicao para exportacao do arquivo gerado
			// sendo assim e dessa condicao que o usuario ira escolher qual tipo
			// de arquivo ele deseja exportar para obter o relatorio
			switch (tipoRelatorio) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				tipoRelatorioExportado = new JRPdfExporter();

				extensaoArquivoExportado = "pdf";
				break;

			case RelatorioJasperUtil.RELATORIO_EXCEL:
				tipoRelatorioExportado = new JRXlsExporter();
				extensaoArquivoExportado = "xls";
				break;

			case RelatorioJasperUtil.RELATORIO_HTML:
				tipoRelatorioExportado = new JRHtmlExporter();
				extensaoArquivoExportado = "html";
				break;

			case RelatorioJasperUtil.RELATORIO_OPEN_OFFICE:
				tipoRelatorioExportado = new JROdtExporter();
				extensaoArquivoExportado = "ods";
				break;

			default:
				tipoRelatorioExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			}
			// apos o usuario escolher o arquivo para exportacao geramos o
			// arquivo e setamos os parametros passados para gerar o relatorio
			caminhoArquivoRelatorio = caminhoRelatorio + File.separator + nomeRelatorioSaidaJasper + "."
					+ extensaoArquivoExportado;
			arquivoGerado = new File(caminhoArquivoRelatorio);

			tipoRelatorioExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressaoJasper);
			tipoRelatorioExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

			// apos o arquivo ser baixado ou obtido pelo Usuario ele e
			// descartado da pasta raiz do sistema
			tipoRelatorioExportado.exportReport();

			arquivoGerado.deleteOnExit();

			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado,
					nomeRelatorioSaidaJasper + "." + extensaoArquivoExportado);

		} catch (JRException causa) {
			causa.printStackTrace();
		} catch (FileNotFoundException causa) {
			causa.printStackTrace();

		}

		return arquivoRetorno;
	}

	private Connection getConexao() throws Exception {
		Connection connection = null;
		try {
			connection = ConexaoDefaultDAO.getconxao();

		} catch (Exception causa) {
			causa.printStackTrace();
		}

		return connection;
	}

	public static int getRelatorioPdf() {
		return RELATORIO_PDF;
	}

	public static int getRelatorioExcel() {
		return RELATORIO_EXCEL;
	}

	public static int getRelatorioHtml() {
		return RELATORIO_HTML;
	}

	public static int getRelatorioOpenOffice() {
		return RELATORIO_OPEN_OFFICE;
	}

}
