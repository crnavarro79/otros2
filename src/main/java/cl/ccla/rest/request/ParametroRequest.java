package cl.ccla.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametroRequest {
	
	@JsonProperty("id_parametro")
	private Integer idParametro;
	private String valor;
	@JsonProperty("es_activo")
	private Integer esActivo;

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Integer esActivo) {
		this.esActivo = esActivo;
	}

	@Override
	public String toString() {
		return "ParametrosTO [idParametro=" + idParametro + ", valor=" + valor + ", esActivo=" + esActivo + "]";
	}

	
}
