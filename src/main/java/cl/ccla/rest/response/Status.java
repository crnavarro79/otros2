package cl.ccla.rest.response;

public class Status {
	
	private Integer codigo;
	private String mensaje;
	private String excepcion;
	
	

	public String getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [codigo=");
		builder.append(codigo);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append("]");
		return builder.toString();
	}

	
}
