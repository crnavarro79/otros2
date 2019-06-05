package cl.ccla.to;

import cl.ccla.CashinResponse;

public class CashinTPPResponse {
	private CashinResponse response;
	private boolean estado;
	
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
}
