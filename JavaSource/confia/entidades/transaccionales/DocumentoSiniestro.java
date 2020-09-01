package confia.entidades.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "documentos_siniestro_tbl")
public class DocumentoSiniestro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "secuencia_cd_doc_siniestro")
	@SequenceGenerator(sequenceName = "secuencia_cd_doc_siniestro", name = "secuencia_cd_doc_siniestro", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_doc_siniestro")
	private Integer cd_doc_siniestro;
	@Column(name="cd_siniestro")
	private Integer cd_siniestro;
	@Column(name="tp_doc_siniestro")
	private String tp_doc_siniestro;
	@Column(name="cd_compania")
	private Integer cd_compania;
	@Column(name="obs_doc_siniestro")
	private String obs_doc_siniestro;
	@Column(name="fc_recibo_brk")
	private Date fc_recibo_brk;
	@Column(name="fc_envio_aseg")
	private Date fc_envio_aseg;
	@Column(name="fc_recuerda_cli")
	private Date fc_recuerda_cli;
	@Column(name="doc_recuerda_cli")
	private String doc_recuerda_cli;
	@Column(name="fc_rec_cli_correo")
	private Date fc_rec_cli_correo;
	@Column(name="cd_rubro")
	private Integer cd_rubro;
	public Integer getCd_doc_siniestro() {
		return cd_doc_siniestro;
	}
	public void setCd_doc_siniestro(Integer cd_doc_siniestro) {
		this.cd_doc_siniestro = cd_doc_siniestro;
	}
	public Integer getCd_siniestro() {
		return cd_siniestro;
	}
	public void setCd_siniestro(Integer cd_siniestro) {
		this.cd_siniestro = cd_siniestro;
	}
	public String getTp_doc_siniestro() {
		return tp_doc_siniestro;
	}
	public void setTp_doc_siniestro(String tp_doc_siniestro) {
		this.tp_doc_siniestro = tp_doc_siniestro;
	}
	public Integer getCd_compania() {
		return cd_compania;
	}
	public void setCd_compania(Integer cd_compania) {
		this.cd_compania = cd_compania;
	}
	public String getObs_doc_siniestro() {
		return obs_doc_siniestro;
	}
	public void setObs_doc_siniestro(String obs_doc_siniestro) {
		this.obs_doc_siniestro = obs_doc_siniestro;
	}
	public Date getFc_recibo_brk() {
		return fc_recibo_brk;
	}
	public void setFc_recibo_brk(Date fc_recibo_brk) {
		this.fc_recibo_brk = fc_recibo_brk;
	}
	public Date getFc_envio_aseg() {
		return fc_envio_aseg;
	}
	public void setFc_envio_aseg(Date fc_envio_aseg) {
		this.fc_envio_aseg = fc_envio_aseg;
	}
	public Date getFc_recuerda_cli() {
		return fc_recuerda_cli;
	}
	public void setFc_recuerda_cli(Date fc_recuerda_cli) {
		this.fc_recuerda_cli = fc_recuerda_cli;
	}
	public String getDoc_recuerda_cli() {
		return doc_recuerda_cli;
	}
	public void setDoc_recuerda_cli(String doc_recuerda_cli) {
		this.doc_recuerda_cli = doc_recuerda_cli;
	}
	public Date getFc_rec_cli_correo() {
		return fc_rec_cli_correo;
	}
	public void setFc_rec_cli_correo(Date fc_rec_cli_correo) {
		this.fc_rec_cli_correo = fc_rec_cli_correo;
	}
	public Integer getCd_rubro() {
		return cd_rubro;
	}
	public void setCd_rubro(Integer cd_rubro) {
		this.cd_rubro = cd_rubro;
	}
	
	
	
}
