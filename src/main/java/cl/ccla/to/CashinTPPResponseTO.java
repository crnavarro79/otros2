package cl.ccla.to;

import cl.ccla.CashinResponse;

public class CashinTPPResponseTO {
	private CashinResponse response;
	private boolean estado;
	private String error;
	
	public CashinResponse getResponse() {
		return response;
	}
	public void setResponse(CashinResponse response) {
		this.response = response;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
