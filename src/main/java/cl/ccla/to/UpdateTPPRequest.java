package cl.ccla.to;

import java.util.Date;

public class UpdateTPPRequest {
	
	private String idTransaccionAP;
	private String codigoRespuestaAP;
	private String mensajeRespuestaAP;
	private Date fechaAP;
	private String idTransaccionTpp;
	private Integer codigoRespuestaSP;
	
	
	
	public Integer getCodigoRespuestaSP() {
		return codigoRespuestaSP;
	}
	public void setCodigoRespuestaSP(Integer codigoRespuestaSP) {
		this.codigoRespuestaSP = codigoRespuestaSP;
	}
	public String getIdTransaccionAP() {
		return idTransaccionAP;
	}
	public void setIdTransaccionAP(String idTransaccionAP) {
		this.idTransaccionAP = idTransaccionAP;
	}
	public String getCodigoRespuestaAP() {
		return codigoRespuestaAP;
	}
	public void setCodigoRespuestaAP(String codigoRespuestaAP) {
		this.codigoRespuestaAP = codigoRespuestaAP;
	}
	public String getMensajeRespuestaAP() {
		return mensajeRespuestaAP;
	}
	public void setMensajeRespuestaAP(String mensajeRespuestaAP) {
		this.mensajeRespuestaAP = mensajeRespuestaAP;
	}
	public Date getFechaAP() {
		return fechaAP;
	}
	public void setFechaAP(Date fechaAP) {
		this.fechaAP = fechaAP;
	}
	public String getIdTransaccionTpp() {
		return idTransaccionTpp;
	}
	public void setIdTransaccionTpp(String idTransaccionTpp) {
		this.idTransaccionTpp = idTransaccionTpp;
	}
	@Override
	public String toString() {
		return "UpdateTPPRequest [idTransaccionAP=" + idTransaccionAP + ", codigoRespuestaAP=" + codigoRespuestaAP
				+ ", mensajeRespuestaAP=" + mensajeRespuestaAP + ", fechaAP=" + fechaAP + ", idTransaccionTpp="
				+ idTransaccionTpp + ", codigoRespuestaSP=" + codigoRespuestaSP + "]";
	}
	
	
}
