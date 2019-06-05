package cl.ccla.to;

public class RecargaTPPResponse {

	private String idTransaccion;
	private String codigoRespuesta;
	private String glosaRespuesta;
	private String idTransaccionAndesPrepago;
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
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
	public String getIdTransaccionAndesPrepago() {
		return idTransaccionAndesPrepago;
	}
	public void setIdTransaccionAndesPrepago(String idTransaccionAndesPrepago) {
		this.idTransaccionAndesPrepago = idTransaccionAndesPrepago;
	}
	@Override
	public String toString() {
		return "RecargaTPPResponse [idTransaccion=" + idTransaccion + ", codigoRespuesta=" + codigoRespuesta
				+ ", glosaRespuesta=" + glosaRespuesta + ", idTransaccionAndesPrepago=" + idTransaccionAndesPrepago
				+ "]";
	}

	

}
