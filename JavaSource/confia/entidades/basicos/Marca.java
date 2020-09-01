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
@Table(name = "marca_tbl")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "MARCA_TBL_SEQ")
	@SequenceGenerator(sequenceName = "MARCA_TBL_SEQ", name = "MARCA_TBL_SEQ", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_marca")
	private Integer cd_marca;
	
	@Column(name="desc_marca")
	private String desc_marca;
	
	@Column(name="estado_marca")
	private String estado_marca;

	public Integer getCd_marca() {
		return cd_marca;
	}

	public void setCd_marca(Integer cd_marca) {
		this.cd_marca = cd_marca;
	}

	public String getDesc_marca() {
		return desc_marca;
	}

	public void setDesc_marca(String desc_marca) {
		this.desc_marca = desc_marca;
	}

	public String getEstado_marca() {
		return estado_marca;
	}

	public void setEstado_marca(String estado_marca) {
		this.estado_marca = estado_marca;
	}

}
