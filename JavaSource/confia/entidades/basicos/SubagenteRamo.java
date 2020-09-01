package confia.entidades.basicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "subagenteRamo_tbl")
public class SubagenteRamo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_subagenteramo")
	@SequenceGenerator(sequenceName = "secuencia_cd_subagenteramo", name = "secuencia_cd_subagenteramo", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_subagenteramo")
	private Integer cd_subagenteramo;
	@Column(name="cd_ramo")
	private Integer cd_ramo;
	@Column(name="cd_subagente")
	private Integer cd_subagente;
	public Integer getCd_subagenteramo() {
		return cd_subagenteramo;
	}
	public void setCd_subagenteramo(Integer cd_subagenteramo) {
		this.cd_subagenteramo = cd_subagenteramo;
	}
	public Integer getCd_ramo() {
		return cd_ramo;
	}
	public void setCd_ramo(Integer cd_ramo) {
		this.cd_ramo = cd_ramo;
	}
	public Integer getCd_subagente() {
		return cd_subagente;
	}
	public void setCd_subagente(Integer cd_subagente) {
		this.cd_subagente = cd_subagente;
	}
	
	

}
