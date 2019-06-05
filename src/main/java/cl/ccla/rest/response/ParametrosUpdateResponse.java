package cl.ccla.rest.response;

public class ParametrosUpdateResponse extends DetalleStatus {

	private Integer estado;
	private String glosa;

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	@Override
	public String toString() {
		return "ParametrosUpdateResponse [estado=" + estado + ", glosa=" + glosa + ", toString()=" + super.toString()
				+ "]";
	}

}
