package confia.entidades.vistas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta_fact_confia_view")
public class ConsultaFactConifaView {
	@Id
	@Column(name="num_factura")
	private String 	num_factura;
	@Column(name="val_comision")
	private Double   val_comision;
	@Column(name="poliza")
	private String poliza;
	@Column(name="factura")
	private String   factura;
	public String getNum_factura() {
		return num_factura;
	}
	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}
	public Double getVal_comision() {
		return val_comision;
	}
	public void setVal_comision(Double val_comision) {
		this.val_comision = val_comision;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
}
