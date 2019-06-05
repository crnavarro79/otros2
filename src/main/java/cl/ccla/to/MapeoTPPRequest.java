package cl.ccla.to;

public class MapeoTPPRequest {

	private int codigoRespuesta;
	private String glosaRespuesta;
	private int codigoError;
	private String mensajeError;

	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getGlosaRespuesta() {
		return glosaRespuesta;
	}

	public void setGlosaRespuesta(String glosaRespuesta) {
		this.glosaRespuesta = glosaRespuesta;
	}
	
	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	@Override
	public String toString() {
		return "MapeoTPPRequest [codigoRespuesta=" + codigoRespuesta + ", glosaRespuesta=" + glosaRespuesta
				+ ", codigoError=" + codigoError + ", mensajeError=" + mensajeError + "]";
	}

	
}
