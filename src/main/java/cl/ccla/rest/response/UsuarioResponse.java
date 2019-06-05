package cl.ccla.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioResponse extends DetalleStatus {

	@JsonProperty("user_name")
	private String userName;
	
	private String apellido;
	
	private String nombre;
	
	private String rol;
	
	
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "UsuarioResponse [userName=" + userName + ", apellido=" + apellido + ", nombre=" + nombre + ", rol="
				+ rol + ", toString()=" + super.toString() + "]";
	}


	

	

}
