package cl.ccla.rest.response;

import java.util.List;

import cl.ccla.to.RecargaTO;

public class RecargaResponse extends DetalleStatus {

	private List<RecargaTO> recargas;

	public List<RecargaTO> getRecargas() {
		return recargas;
	}

	public void setRecargas(List<RecargaTO> recargas) {
		this.recargas = recargas;
	}

	@Override
	public String toString() {
		return "RecargaResponse [recargas=" + recargas + ", toString()=" + super.toString() + "]";
	}

}
