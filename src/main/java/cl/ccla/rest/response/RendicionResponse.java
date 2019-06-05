package cl.ccla.rest.response;

import java.util.List;

import cl.ccla.to.RendicionTO;

public class RendicionResponse extends DetalleStatus {

	private List<RendicionTO> rendiciones;

	public List<RendicionTO> getRendiciones() {
		return rendiciones;
	}

	public void setRendiciones(List<RendicionTO> rendiciones) {
		this.rendiciones = rendiciones;
	}

	@Override
	public String toString() {
		return "RendicionResponse [rendiciones=" + rendiciones + ", toString()=" + super.toString() + "]";
	}

	

}
