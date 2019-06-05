package cl.ccla.constants;

public enum FormatoFechaEnum {

	FORMATO_SUFIJO_ARCHIVO("yyyyMMddHHmm"), FORMATO_HORA_MIN_SEG("HH:mm:ss"), FORMATO_PREFIJO_ARCHIVO(
			"yyyyMMdd"), FORMATO_ESTANDAR("dd-MM-yyyy"), FORMATO_BACKEND("yyyy-MM-dd");

	private String patron;

	FormatoFechaEnum(String patron) {
		this.patron = patron;
	}

	public String getPatron() {
		return patron;
	}

}
