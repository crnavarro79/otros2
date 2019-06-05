package cl.ccla.to;

public class UpdateTPPResponse {
	
	private String estado;
    private String codigoRespuesta;
    private String glosaRespuesta;
    
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getGlosaRespuesta() {
		return glosaRespuesta;
	}
	public void setGlosaRespuesta(String glosaRespuesta) {
		this.glosaRespuesta = glosaRespuesta;
	}
	@Override
	public String toString() {
		return "UpdateTPPResponse [estado=" + estado + ", codigoRespuesta=" + codigoRespuesta + ", glosaRespuesta="
				+ glosaRespuesta + "]";
	}
	
	
}
