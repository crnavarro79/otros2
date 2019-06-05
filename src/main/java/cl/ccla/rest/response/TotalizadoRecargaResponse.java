package cl.ccla.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.ccla.to.RecargaTotalizadoTO;

public class TotalizadoRecargaResponse extends DetalleStatus {

	@JsonProperty("recarga_totalizados")
	private List<RecargaTotalizadoTO> recargaTotalizados;

	public List<RecargaTotalizadoTO> getRecargaTotalizados() {
		return recargaTotalizados;
	}

	public void setRecargaTotalizados(List<RecargaTotalizadoTO> recargaTotalizados) {
		this.recargaTotalizados = recargaTotalizados;
	}

	@Override
	public String toString() {
		return "TotalizadoRecargaResponse [recargaTotalizados=" + recargaTotalizados + ", toString()="
				+ super.toString() + "]";
	}

}
