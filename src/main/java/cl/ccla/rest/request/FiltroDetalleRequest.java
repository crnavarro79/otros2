package cl.ccla.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroDetalleRequest extends FiltroRequest{

	
	@JsonProperty("codigo_ap")
	private String codigoAP;
	

	public String getCodigoAP() {
		return codigoAP;
	}

	public void setCodigoAP(String codigoAP) {
		this.codigoAP = codigoAP;
	}



}