package cl.ccla.to;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecargaTO {

	private Integer canal;
	
	private Integer sistema;
	
	private Integer producto;
	
	@JsonProperty("rut_afiliado")
	private Integer rutAfiliado;
	
	@JsonProperty("dv_afiliado")
	private String dvAfiliado;
	
	@JsonProperty("nro_contrato")
	private Integer nroContrato;
	
	@JsonProperty("monto_recarga")
	private Integer montoRecarga;
	
	@JsonProperty("fecha_operacion")
	private Date fechaOperacion;
	
	@JsonProperty("estado_transaccion")
	private String estadoTransaccion;
	
	@JsonProperty("cod_transaccion_ap")
	private Integer codTransaccionAp;
	
	@JsonProperty("cod_transaccion_tpp")
	private Integer codTransaccionTpp;
	
	@JsonProperty("cod_transaccion_sp")
	private String codTransaccionSp;
	
	@JsonProperty("mensaje_ap")
	private String mensajeAp;
	
	@JsonProperty("codigo_ap")
	private Integer codigoAp;
	

	public Integer getCanal() {
		return canal;
	}

	public void setCanal(Integer canal) {
		this.canal = canal;
	}

	public Integer getSistema() {
		return sistema;
	}

	public void setSistema(Integer sistema) {
		this.sistema = sistema;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(Integer rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getDvAfiliado() {
		return dvAfiliado;
	}

	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}

	public Integer getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(Integer nroContrato) {
		this.nroContrato = nroContrato;
	}

	public Integer getMontoRecarga() {
		return montoRecarga;
	}

	public void setMontoRecarga(Integer montoRecarga) {
		this.montoRecarga = montoRecarga;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public Integer getCodTransaccionAp() {
		return codTransaccionAp;
	}

	public void setCodTransaccionAp(Integer codTransaccionAp) {
		this.codTransaccionAp = codTransaccionAp;
	}

	public Integer getCodTransaccionTpp() {
		return codTransaccionTpp;
	}

	public void setCodTransaccionTpp(Integer codTransaccionTpp) {
		this.codTransaccionTpp = codTransaccionTpp;
	}

	public String getCodTransaccionSp() {
		return codTransaccionSp;
	}

	public void setCodTransaccionSp(String codTransaccionSp) {
		this.codTransaccionSp = codTransaccionSp;
	}

	public String getMensajeAp() {
		return mensajeAp;
	}

	public void setMensajeAp(String mensajeAp) {
		this.mensajeAp = mensajeAp;
	}

	public Integer getCodigoAp() {
		return codigoAp;
	}

	public void setCodigoAp(Integer codigoAp) {
		this.codigoAp = codigoAp;
	}

	@Override
	public String toString() {
		return "RecargaTO [canal=" + canal + ", sistema=" + sistema + ", producto=" + producto + ", rutAfiliado="
				+ rutAfiliado + ", dvAfiliado=" + dvAfiliado + ", nroContrato=" + nroContrato + ", montoRecarga="
				+ montoRecarga + ", fechaOperacion=" + fechaOperacion + ", estadoTransaccion=" + estadoTransaccion
				+ ", codTransaccionAp=" + codTransaccionAp + ", codTransaccionTpp=" + codTransaccionTpp
				+ ", codTransaccionSp=" + codTransaccionSp + ", mensajeAp=" + mensajeAp + ", codigoAp=" + codigoAp
				+ "]";
	}

}