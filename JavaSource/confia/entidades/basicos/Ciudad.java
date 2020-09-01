package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ciudad_tbl")
public class Ciudad {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_ciudad")
	@SequenceGenerator(sequenceName = "secuencia_cd_ciudad", name = "secuencia_cd_ciudad", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_ciudad")
	private Integer cd_ciudad;
	@Column(name="nm_ciudad")
	private String nm_ciudad;
	public Integer getCd_ciudad() {
		return cd_ciudad;
	}
	public void setCd_ciudad(Integer cd_ciudad) {
		this.cd_ciudad = cd_ciudad;
	}
	public String getNm_ciudad() {
		return nm_ciudad;
	}
	public void setNm_ciudad(String nm_ciudad) {
		this.nm_ciudad = nm_ciudad;
	}

}
