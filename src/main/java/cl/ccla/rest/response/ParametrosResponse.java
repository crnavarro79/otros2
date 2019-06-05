package cl.ccla.rest.response;

import java.util.List;

import cl.ccla.to.ParametroTO;

public class ParametrosResponse extends DetalleStatus {

	private List<ParametroTO> parametros;

	public List<ParametroTO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroTO> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "ParametrosResponse [parametros=" + parametros + ", toString()=" + super.toString() + "]";
	}

}
