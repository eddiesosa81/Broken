package upp.confia.controladores;

import java.util.Date;

public class UppSolicitud {
	
	private Integer almacen_id;
	private Integer tecnico_reporta;
	private Integer tecnico_asignado;
	private String detalle_problema;
	private String solucion_tecnica;
	private String problema;
	private Integer estados_id;
	private Date fecha;
	private Date fecha_asignada;
	private Integer cliente_id;
	private Integer id_padre;
	private Integer usrsupervisa;
	private String cod_comite_subcomite;
	private Integer solicitud_proy;
	public Integer getAlmacen_id() {
		return almacen_id;
	}
	public void setAlmacen_id(Integer almacen_id) {
		this.almacen_id = almacen_id;
	}
	public Integer getTecnico_reporta() {
		return tecnico_reporta;
	}
	public void setTecnico_reporta(Integer tecnico_reporta) {
		this.tecnico_reporta = tecnico_reporta;
	}
	public Integer getTecnico_asignado() {
		return tecnico_asignado;
	}
	public void setTecnico_asignado(Integer tecnico_asignado) {
		this.tecnico_asignado = tecnico_asignado;
	}
	public String getDetalle_problema() {
		return detalle_problema;
	}
	public void setDetalle_problema(String detalle_problema) {
		this.detalle_problema = detalle_problema;
	}
	public String getSolucion_tecnica() {
		return solucion_tecnica;
	}
	public void setSolucion_tecnica(String solucion_tecnica) {
		this.solucion_tecnica = solucion_tecnica;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	public Integer getEstados_id() {
		return estados_id;
	}
	public void setEstados_id(Integer estados_id) {
		this.estados_id = estados_id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha_asignada() {
		return fecha_asignada;
	}
	public void setFecha_asignada(Date fecha_asignada) {
		this.fecha_asignada = fecha_asignada;
	}
	public Integer getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}
	public Integer getId_padre() {
		return id_padre;
	}
	public void setId_padre(Integer id_padre) {
		this.id_padre = id_padre;
	}
	public Integer getUsrsupervisa() {
		return usrsupervisa;
	}
	public void setUsrsupervisa(Integer usrsupervisa) {
		this.usrsupervisa = usrsupervisa;
	}
	public String getCod_comite_subcomite() {
		return cod_comite_subcomite;
	}
	public void setCod_comite_subcomite(String cod_comite_subcomite) {
		this.cod_comite_subcomite = cod_comite_subcomite;
	}
	public Integer getSolicitud_proy() {
		return solicitud_proy;
	}
	public void setSolicitud_proy(Integer solicitud_proy) {
		this.solicitud_proy = solicitud_proy;
	}
}
