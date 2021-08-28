package br.com.homeoffice.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class ProdutosModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1649660210823981468L;


	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	private Integer idprodutoApagar;
	
	@Column
	@NotNull
	private String nameProdutoApagar;
	
	@Column
	private String  referenciaProdutoApagar;
	
	@Column
	private String  descricaoProdutoApagar;
	

	@Column
	private int  diasSempagar;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataProdutoApagar;
	
	
	@OneToOne
	@JoinColumn (name  = "produtoReferenteaoFavorecido")
	private Favorecidos produtoReferenteFavorecido;
	
	
	
	
	
	
	
	
	
	
}
