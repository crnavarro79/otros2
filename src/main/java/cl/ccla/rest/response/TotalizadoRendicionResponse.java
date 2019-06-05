package cl.ccla.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.ccla.to.RendicionTotalizadoTO;

public class TotalizadoRendicionResponse extends DetalleStatus {

	@JsonProperty("rendicion_totalizados")
	private List<RendicionTotalizadoTO> rendicionTotalizados;

	public List<RendicionTotalizadoTO> getRendicionTotalizados() {
		return rendicionTotalizados;
	}

	public void setRendicionTotalizados(List<RendicionTotalizadoTO> rendicionTotalizados) {
		this.rendicionTotalizados = rendicionTotalizados;
	}

	@Override
	public String toString() {
		return "TotalizadoRendicionResponse [rendicionTotalizados=" + rendicionTotalizados + ", toString()="
				+ super.toString() + "]";
	}


}
