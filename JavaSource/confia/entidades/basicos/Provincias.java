package confia.entidades.basicos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provincia_tbl")
public class Provincias {

	@Id
	@Column(name="cd_provincia")
	private Integer cd_provincia;
	
	@Column(name="desc_provincia")
	private String desc_provincia;

	public Integer getCd_provincia() {
		return cd_provincia;
	}

	public void setCd_provincia(Integer cd_provincia) {
		this.cd_provincia = cd_provincia;
	}

	public String getDesc_provincia() {
		return desc_provincia;
	}

	public void setDesc_provincia(String desc_provincia) {
		this.desc_provincia = desc_provincia;
	}
	
}
