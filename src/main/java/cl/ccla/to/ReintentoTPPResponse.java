package cl.ccla.to;

public class ReintentoTPPResponse {
	private String dato;
	private int codigo;
	private String glosa;

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	@Override
	public String toString() {
		return "ReintentoTPPResponse [dato=" + dato + ", codigo=" + codigo + ", glosa=" + glosa + "]";
	}

}
