package cl.ccla.rest.response;

import cl.ccla.to.ParametroTO;

public class ParametroResponse extends DetalleStatus {

	private ParametroTO parametro;

	public ParametroTO getParametro() {
		return parametro;
	}

	public void setParametro(ParametroTO parametro) {
		this.parametro = parametro;
	}

	@Override
	public String toString() {
		return "ParametroResponse [parametro=" + parametro + ", toString()=" + super.toString() + "]";
	}

}
