/**
 * 
 */
package confia.entidades.basicos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Guido Guerrero
 *
 */

@Entity
@Table(name = "COMISIONRAMOASEGURADORA_TBL")
public class ComisionRamoAseguradora {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_com_ramaseg")
	@SequenceGenerator(sequenceName = "secuencia_cd_com_ramaseg", name = "secuencia_cd_com_ramaseg", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_com_ramaseg")
	private Integer cd_com_ramaseg;
	@Column(name="cd_ramoaseg")
	private Integer cd_ramoaseg;
	@Column(name="porcentaje_com_ramaseg")
	private Double porcentaje_com_ramaseg;
	@Column(name="fc_ini_com_ramaseg")
	private Date fc_ini_com_ramaseg;
	@Column(name="fc_fin_com_ramaseg")
	private Date fc_fin_com_ramaseg;
	@Column(name="estado_com_ramaseg")
	private String estado_com_ramaseg;
	
	@Column(name="cd_grupo_contratante")
	private String cd_grupo_contratante;
	@Column(name="nombre_corto_grupo_contratante")
	private String nombre_corto_grupo_contratante;
	
	public Integer getCd_com_ramaseg() {
		return cd_com_ramaseg;
	}
	public void setCd_com_ramaseg(Integer cd_com_ramaseg) {
		this.cd_com_ramaseg = cd_com_ramaseg;
	}
	public Integer getCd_ramoaseg() {
		return cd_ramoaseg;
	}
	public void setCd_ramoaseg(Integer cd_ramoaseg) {
		this.cd_ramoaseg = cd_ramoaseg;
	}
	public Double getPorcentaje_com_ramaseg() {
		return porcentaje_com_ramaseg;
	}
	public void setPorcentaje_com_ramaseg(Double porcentaje_com_ramaseg) {
		this.porcentaje_com_ramaseg = porcentaje_com_ramaseg;
	}
	public Date getFc_ini_com_ramaseg() {
		return fc_ini_com_ramaseg;
	}
	public void setFc_ini_com_ramaseg(Date fc_ini_com_ramaseg) {
		this.fc_ini_com_ramaseg = fc_ini_com_ramaseg;
	}
	public Date getFc_fin_com_ramaseg() {
		return fc_fin_com_ramaseg;
	}
	public void setFc_fin_com_ramaseg(Date fc_fin_com_ramaseg) {
		this.fc_fin_com_ramaseg = fc_fin_com_ramaseg;
	}
	public String getEstado_com_ramaseg() {
		return estado_com_ramaseg;
	}
	public void setEstado_com_ramaseg(String estado_com_ramaseg) {
		this.estado_com_ramaseg = estado_com_ramaseg;
	}
	public String getCd_grupo_contratante() {
		return cd_grupo_contratante;
	}
	public void setCd_grupo_contratante(String cd_grupo_contratante) {
		this.cd_grupo_contratante = cd_grupo_contratante;
	}
	public String getNombre_corto_grupo_contratante() {
		return nombre_corto_grupo_contratante;
	}
	public void setNombre_corto_grupo_contratante(String nombre_corto_grupo_contratante) {
		this.nombre_corto_grupo_contratante = nombre_corto_grupo_contratante;
	}
	
	

	
}
