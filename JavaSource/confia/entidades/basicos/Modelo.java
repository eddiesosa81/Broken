package confia.entidades.basicos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "modelo_tbl")
public class Modelo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "modelo_tbl_seq")
	@SequenceGenerator(sequenceName = "modelo_tbl_seq", name = "modelo_tbl_seq", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_modelo")
	private Integer cd_modelo;
	@Column(name="cd_marca")
	private Integer cd_marca;
	@Column(name="desc_modelo")
	private String desc_modelo;
	@Column(name="estado_modelo")
	private String estado_modelo;
	public Integer getCd_modelo() {
		return cd_modelo;
	}
	public void setCd_modelo(Integer cd_modelo) {
		this.cd_modelo = cd_modelo;
	}
	public Integer getCd_marca() {
		return cd_marca;
	}
	public void setCd_marca(Integer cd_marca) {
		this.cd_marca = cd_marca;
	}
	public String getDesc_modelo() {
		return desc_modelo;
	}
	public void setDesc_modelo(String desc_modelo) {
		this.desc_modelo = desc_modelo;
	}
	public String getEstado_modelo() {
		return estado_modelo;
	}
	public void setEstado_modelo(String estado_modelo) {
		this.estado_modelo = estado_modelo;
	}
}
