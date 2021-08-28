package br.com.homeoffice.core;

public class Pagamentos {
	
	/**
	 * 
	 * @author Admin
}



@Entity
@Table(name = "pagamentos")

	private static final long serialVersionUID = -2311556290679236667L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpagamentos;

	@ManyToMany
	@JoinTable(name = "pagamentos_Contas", joinColumns = @JoinColumn(name = "idContas"), inverseJoinColumns = @JoinColumn(name = "idPagamentos"))
	private List<ContasApagarCad> contasaPagar;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dataPagamentoRealizado;

	@Column
	private Double ValorPagamento;

*/

}
	 

