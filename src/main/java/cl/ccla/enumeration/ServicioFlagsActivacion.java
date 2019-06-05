package cl.ccla.enumeration;

public enum ServicioFlagsActivacion {
	CREDITO("SP_CREDITO_ACT_SERVICIO", 1), LICENCIA_MEDICA("SP_LMEDICA_ACT_SERVICIO",
			2), BENEFICIO("SP_BENEFICIO_ACT_SERVICIO", 3), AHORRO("SP_AHORRO_ACT_SERVICIO", 4);

	private String nombreParametro;
	private Integer codigoSistema;

	private ServicioFlagsActivacion(String nombreParametro, int codigoSistema) {
		this.nombreParametro = nombreParametro;
		this.codigoSistema = codigoSistema;
	}

	public static String getNombreParametro(Integer codigoSistema) {
		
		for(ServicioFlagsActivacion servicioFlagsActivacion : values()) {
			if(servicioFlagsActivacion.getCodigoSistema().equals(codigoSistema)) {
				return servicioFlagsActivacion.getNombreParametro();
			}
		}
		
		return null;
	}

	public String getNombreParametro() {
		return nombreParametro;
	}

	public Integer getCodigoSistema() {
		return codigoSistema;
	}
	
	
}
