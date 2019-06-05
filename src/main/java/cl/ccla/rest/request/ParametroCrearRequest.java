package cl.ccla.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametroCrearRequest {
	private String nombre;
	private Integer valor;
	private String grupo;
	private String aplicar;
	@JsonProperty("usuario_registro")
	private String usuarioRegistro;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getAplicar() {
		return aplicar;
	}

	public void setAplicar(String aplicar) {
		this.aplicar = aplicar;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	@Override
	public String toString() {
		return "\nnombre=" + nombre + " \nvalor=" + valor + " \rgrupo=" + grupo + " \raplicar=" + aplicar
				+ " \rusuarioRegistro=" + usuarioRegistro;
	}

}
