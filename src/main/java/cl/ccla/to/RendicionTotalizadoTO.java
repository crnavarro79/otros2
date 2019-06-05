package cl.ccla.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RendicionTotalizadoTO {

	@JsonProperty("nro_contrato")
	private String codRespAp;
	
	@JsonProperty("glosa_ap")
	private String glosaAp;
	
	private Integer canal;
	
	@JsonProperty("desc_canal")
	private String descCanal;
	
	private Integer procesados;
	
	@JsonProperty("monto_total")
	private Integer montoTotal;

	public String getCodRespAp() {
		return codRespAp;
	}

	public void setCodRespAp(String codRespAp) {
		this.codRespAp = codRespAp;
	}

	public String getGlosaAp() {
		return glosaAp;
	}

	public void setGlosaAp(String glosaAp) {
		this.glosaAp = glosaAp;
	}

	public Integer getCanal() {
		return canal;
	}

	public void setCanal(Integer canal) {
		this.canal = canal;
	}

	public String getDescCanal() {
		return descCanal;
	}

	public void setDescCanal(String descCanal) {
		this.descCanal = descCanal;
	}

	public Integer getProcesados() {
		return procesados;
	}

	public void setProcesados(Integer procesados) {
		this.procesados = procesados;
	}

	public Integer getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Integer montoTotal) {
		this.montoTotal = montoTotal;
	}

	@Override
	public String toString() {
		return "RendicionTO [codRespAp=" + codRespAp + ", glosaAp=" + glosaAp + ", canal=" + canal + ", descCanal="
				+ descCanal + ", procesados=" + procesados + ", montoTotal=" + montoTotal + "]";
	}

}