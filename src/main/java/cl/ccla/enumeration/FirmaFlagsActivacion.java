package cl.ccla.enumeration;

public enum FirmaFlagsActivacion {
	CREDITO("SP_CREDITO_ACT_FIRMA", 1,"Credito"), LICENCIA_MEDICA("SP_LMEDICA_ACT_FIRMA",
			2,"Licencia Medica"), BENEFICIO("SP_BENEFICIO_ACT_FIRMA", 3, "Beneficios"), AHORRO("SP_AHORRO_ACT_FIRMA", 4,"Ahorro");

	private String nombreParametro;
	private Integer codigoSistema;
	private String nombreSistema;

	private FirmaFlagsActivacion(String nombre, Integer codigoSistema,String nombreSistema) {
		this.nombreParametro = nombre;
		this.codigoSistema = codigoSistema;
		this.nombreSistema = nombreSistema;
	}
	
	public static String getNombreParametro(Integer codigoSistema) {
		
		for(FirmaFlagsActivacion firmaFlagsActivacion : values()) {
			if(firmaFlagsActivacion.getCodigoSistema().equals(codigoSistema)) {
				return firmaFlagsActivacion.getNombreParametro();
			}
		}
		
		return null;
	}
	
	public static String getNombreSistema(Integer codigoSistema) {
		
		for(FirmaFlagsActivacion firmaFlagsActivacion : values()) {
			if(firmaFlagsActivacion.getCodigoSistema().equals(codigoSistema)) {
				return firmaFlagsActivacion.getNombreSistema();
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

	public String getNombreSistema() {
		return nombreSistema;
	}
	

}
