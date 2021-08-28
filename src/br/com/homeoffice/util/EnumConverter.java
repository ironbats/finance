package br.com.homeoffice.util;

import javax.faces.convert.FacesConverter;

import br.com.homeoffice.core.UFVO;

/**
 * 
 * @author Felipe Rodrigues
 * @since HomeOffice 1.0 RELEASE
 * @version 1.0.0
 * @category Projeto Financeiro
 * @serial Serial Version
 * 
 *         Objetivo Principal dessa classe Util e converter Enums em String para
 *         melhor vizualizacao do usuario
 * 
 */

/**
 * 
 * Anotacao Converter
 *
 */
@FacesConverter(value = "converter")
public class EnumConverter extends javax.faces.convert.EnumConverter {
    /**
     * Contrutor Super
     */
	public EnumConverter() {
		super(UFVO.class);
	}

}
