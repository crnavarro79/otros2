package cl.ccla.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametroTO {

	@JsonProperty("id_parametro")
	private Integer idParametro;
	
	private String descripcion;
	
	private Integer valor;
	
	private Integer estado;
	
	

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ParametroTO [idParametro=" + idParametro + ", descripcion=" + descripcion + ", valor=" + valor
				+ ", estado=" + estado + "]";
	}

}
