package confia.entidades.basicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "diagnostico_tbl")
public class Diagnostico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_diagnostico")
	@SequenceGenerator(sequenceName = "secuencia_cd_diagnostico", name = "secuencia_cd_diagnostico", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_diagnostico")
	private Integer cd_diagnostico;
	@Column(name="diagnistico")
	private String diagnistico;
	public Integer getCd_diagnostico() {
		return cd_diagnostico;
	}
	public void setCd_diagnostico(Integer cd_diagnostico) {
		this.cd_diagnostico = cd_diagnostico;
	}
	public String getDiagnistico() {
		return diagnistico;
	}
	public void setDiagnistico(String diagnistico) {
		this.diagnistico = diagnistico;
	}
	

}
