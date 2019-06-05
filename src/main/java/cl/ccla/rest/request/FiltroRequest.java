package cl.ccla.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroRequest {
	
	@JsonProperty("fecha_inicio")
	private String fechaInicio;
	
	@JsonProperty("fecha_fin")
	private String fechaFin;
	
	@JsonProperty("sistema_producto")
	private String sistemaProducto;

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getSistemaProducto() {
		return sistemaProducto;
	}

	public void setSistemaProducto(String sistemaProducto) {
		this.sistemaProducto = sistemaProducto;
	}

	@Override
	public String toString() {
		return "\nstartDate=" + fechaInicio + " \nendDate=" + fechaFin + " \ncodeSystem=" + sistemaProducto;
	}
}
