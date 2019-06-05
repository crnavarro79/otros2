package cl.ccla.to;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RendicionTO {

	@JsonProperty("id_ap")
	private Integer idAp;
	
	@JsonProperty("nro_contrato")
	private Integer nroContrato;
	
	private Date fecha;
	
	@JsonProperty("rut_afiliado")
	private Integer rutAfiliado;
	
	@JsonProperty("dv_afiliado")
	private String dvAfiliado;
	
	private Integer monto;
	
	@JsonProperty("cod_ap")
	private String codAp;
	
	@JsonProperty("gls_ap")
	private String glsAp;
	
	private Integer canal;
	
	private Integer producto;

	public Integer getIdAp() {
		return idAp;
	}

	public void setIdAp(Integer idAp) {
		this.idAp = idAp;
	}

	public Integer getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(Integer nroContrato) {
		this.nroContrato = nroContrato;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	public String getCodAp() {
		return codAp;
	}

	public void setCodAp(String codAp) {
		this.codAp = codAp;
	}

	public String getGlsAp() {
		return glsAp;
	}

	public void setGlsAp(String glsAp) {
		this.glsAp = glsAp;
	}

	public Integer getCanal() {
		return canal;
	}

	public void setCanal(Integer canal) {
		this.canal = canal;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "DetalleRendicionTO [idAp=" + idAp + ", nroContrato=" + nroContrato + ", fecha=" + fecha
				+ ", rutAfiliado=" + rutAfiliado + ", dvAfiliado=" + dvAfiliado + ", monto=" + monto + ", codAp="
				+ codAp + ", glsAp=" + glsAp + ", canal=" + canal + ", producto=" + producto + "]";
	}

}