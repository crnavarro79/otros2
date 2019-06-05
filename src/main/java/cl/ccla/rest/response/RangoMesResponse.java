package cl.ccla.rest.response;

public class RangoMesResponse extends DetalleStatus {

	private Integer rango;

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}

	@Override
	public String toString() {
		return "RangoMesResponse [rango=" + rango + ", toString()=" + super.toString() + "]";
	}

}