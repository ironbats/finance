package br.com.homeoffice.util;

import java.io.Serializable;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 * 
 * 
 *         Metodo main que cria as tabelas no Banco de dados se for necessario
 * 
 */

@SuppressWarnings("deprecation")
public class CriadadosBanco implements Serializable {

	private static final long serialVersionUID = -2818367818949779947L;

	public static void main(String[] args) {

		AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);

	}

}
