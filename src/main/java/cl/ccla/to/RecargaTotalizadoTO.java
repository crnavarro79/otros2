package cl.ccla.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecargaTotalizadoTO {

	private Integer sistema;
	
	@JsonProperty("cod_estado_ap")
	private String codEstadoAp;
	
	private String estado;
	
	private Integer procesados;
	
	@JsonProperty("total_monto")
	private Integer totalMonto;

	public Integer getSistema() {
		return sistema;
	}

	public void setSistema(Integer sistema) {
		this.sistema = sistema;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getProcesados() {
		return procesados;
	}

	public void setProcesados(Integer procesados) {
		this.procesados = procesados;
	}

	public String getCodEstadoAp() {
		return codEstadoAp;
	}

	public void setCodEstadoAp(String codEstadoAp) {
		this.codEstadoAp = codEstadoAp;
	}

	public Integer getTotalMonto() {
		return totalMonto;
	}

	public void setTotalMonto(Integer totalMonto) {
		this.totalMonto = totalMonto;
	}

	@Override
	public String toString() {
		return "DetalleRecargaTO [sistema=" + sistema + ", codEstadoAp=" + codEstadoAp + ", estado=" + estado
				+ ", procesados=" + procesados + ", totalMonto=" + totalMonto + "]";
	}

}