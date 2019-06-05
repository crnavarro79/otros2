package cl.ccla.to;

import java.util.Date;

public class ReintentoTPPRequest {
	private int idRecarga;
	private String idSolicitud;
	private int canal;
	private int sistema;
	private int producto;
	private String rut;
	private String nroContrato;
	private int monto;
	private Date fechaSolicitud;
	private String usuario;
	private String mail;
	private int codRespuestaSolicitud;
	private int idAp;
	private String codRespuestaAp;
	private String glsRespuestaAp;
	private Date fechaRespuestaAp;
	private int nroReintento;
	private Date fechaReintento;
	private int codEstrecarga;

	public int getIdRecarga() {
		return idRecarga;
	}

	public void setIdRecarga(int idRecarga) {
		this.idRecarga = idRecarga;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public int getCanal() {
		return canal;
	}

	public void setCanal(int canal) {
		this.canal = canal;
	}

	public int getSistema() {
		return sistema;
	}

	public void setSistema(int sistema) {
		this.sistema = sistema;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(String nroContrato) {
		this.nroContrato = nroContrato;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getCodRespuestaSolicitud() {
		return codRespuestaSolicitud;
	}

	public void setCodRespuestaSolicitud(int codRespuestaSolicitud) {
		this.codRespuestaSolicitud = codRespuestaSolicitud;
	}

	public int getIdAp() {
		return idAp;
	}

	public void setIdAp(int idAp) {
		this.idAp = idAp;
	}

	public String getCodRespuestaAp() {
		return codRespuestaAp;
	}

	public void setCodRespuestaAp(String codRespuestaAp) {
		this.codRespuestaAp = codRespuestaAp;
	}

	public String getGlsRespuestaAp() {
		return glsRespuestaAp;
	}

	public void setGlsRespuestaAp(String glsRespuestaAp) {
		this.glsRespuestaAp = glsRespuestaAp;
	}

	public Date getFechaRespuestaAp() {
		return fechaRespuestaAp;
	}

	public void setFechaRespuestaAp(Date fechaRespuestaAp) {
		this.fechaRespuestaAp = fechaRespuestaAp;
	}

	public int getNroReintento() {
		return nroReintento;
	}

	public void setNroReintento(int nroReintento) {
		this.nroReintento = nroReintento;
	}

	public Date getFechaReintento() {
		return fechaReintento;
	}

	public void setFechaReintento(Date fechaReintento) {
		this.fechaReintento = fechaReintento;
	}

	public int getCodEstrecarga() {
		return codEstrecarga;
	}

	public void setCodEstrecarga(int codEstrecarga) {
		this.codEstrecarga = codEstrecarga;
	}

	@Override
	public String toString() {
		return "ReintentoTPPRequest [idRecarga=" + idRecarga + ", idSolicitud=" + idSolicitud + ", canal=" + canal
				+ ", sistema=" + sistema + ", producto=" + producto + ", rut=" + rut + ", nroContrato=" + nroContrato
				+ ", monto=" + monto + ", fechaSolicitud=" + fechaSolicitud + ", usuario=" + usuario + ", mail=" + mail
				+ ", codRespuestaSolicitud=" + codRespuestaSolicitud + ", idAp=" + idAp + ", codRespuestaAp="
				+ codRespuestaAp + ", glsRespuestaAp=" + glsRespuestaAp + ", fechaRespuestaAp=" + fechaRespuestaAp
				+ ", nroReintento=" + nroReintento + ", fechaReintento=" + fechaReintento + ", codEstrecarga="
				+ codEstrecarga + "]";
	}

}
