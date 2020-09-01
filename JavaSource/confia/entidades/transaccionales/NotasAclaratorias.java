package confia.entidades.transaccionales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "notas_aclaratorias_tbl")
public class NotasAclaratorias {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_nota_aclaratoria")
	@SequenceGenerator(sequenceName = "secuencia_cd_nota_aclaratoria", name = "secuencia_cd_nota_aclaratoria", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_nota_aclaratoria")
	private Integer cd_nota_aclaratoria;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="cd_ramo_cotizacion")
	private Integer cd_ramo_cotizacion;
	@Column(name="detalle_aclaratorio")
	private String detalle_aclaratorio;
	public Integer getCd_nota_aclaratoria() {
		return cd_nota_aclaratoria;
	}
	public void setCd_nota_aclaratoria(Integer cd_nota_aclaratoria) {
		this.cd_nota_aclaratoria = cd_nota_aclaratoria;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public Integer getCd_ramo_cotizacion() {
		return cd_ramo_cotizacion;
	}
	public void setCd_ramo_cotizacion(Integer cd_ramo_cotizacion) {
		this.cd_ramo_cotizacion = cd_ramo_cotizacion;
	}
	public String getDetalle_aclaratorio() {
		return detalle_aclaratorio;
	}
	public void setDetalle_aclaratorio(String detalle_aclaratorio) {
		this.detalle_aclaratorio = detalle_aclaratorio;
	}
	
}
