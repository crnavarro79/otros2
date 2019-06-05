package cl.ccla.constants;

public class AppConstants {

	private AppConstants() {

	}

	public static final String TAP_DATASOURCE_JNDI = "tap.datasource.jndi";

	public static final String NOMBRE_APP = "TPP";
	// PKG
	public static final String PKG_TPP_SERVICIO_WEB = "PKG_TPP_SERVICIO_WEB";
	public static final String PKG_TPP_MANTENEDOR_WEB = "PKG_TPP_MANTENEDOR_WEB";
	// PARAMETRIA
	public static final String PRC_PARAMETRIA = "PRC_SELECT_RANGO_MES";
	// RECARGAS TODAS
	public static final String PRC_TOTALIZADO_RECARGA = "PRC_SELECT_TOT_SIST_ESTADOS";
	// EXCEL RECARCA
	public static final String PRC_RECARGA_DETALLE = "PRC_SELECT_DET_SIST_ESTADOS";
	// RANGO MES
	public static final String PRC_SELECT_RANGO_MES = "PRC_SELECT_RANGO_MES";
	// RENDICIONES TODAS
	public static final String PRC_TOTALIZADO_RENDICIONES = "PRC_SELECT_TOT_RENDICION";
	// EXCEL RENDICIONES
	public static final String PRC_RENDICIONES_DETALLE = "PRC_SELECT_DET_RENDICION";
	// PARAMETRIA
	public static final String PRC_SELECT_CONFIGURACIONES = "PRC_SELECT_CONFIGURACIONES";
	// PARAMETRIA
	public static final String PRC_SELECT_CONFIGURACION = "PRC_SELECT_CONFIGURACION";
	public static final String PRC_SELECT_CONFIGURACION_NOMBRE = "PRC_SELECT_CONFIGURACION_NOMBRE";
	// PARAMETRIA
	public static final String PRC_UPDATE_CONFIGURACION = "PRC_UPDATE_CONFIGURACION";
	// PARAMETRIA
	public static final String PRC_INSERT_CONFIGURACION = "PRC_INSERT_CONFIGURACION";

	// Codigos U-Payments
	public static final String COD_TRANSACCION_OK = "000";
	public static final String COD_CONTRATO_NO_EXISTE = "012";
	public static final String COD_RUT_NO_EXISTE = "014";
	public static final String COD_CANAL_NO_EXISTE = "015";
	public static final String COD_TOKEN_EXPIRADO = "016";
	public static final String COD_ERROR = "001";
	
	public static final String SCHEMA = "tap.schema.recarga";

	public static final String MODELO_SEGURIDAD_ACTIVADO = "1";

	public static final Integer COD_OK_INT_SP = 1;
	public static final String COD_OK_SP = "1";
	public static final String COD_NOK_SP = "0";

	public static final String ID_TRANSACCION_TPP = "P_IDTRANS_TPP";
	public static final String ERROR_MSG = "P_ERRORMSG";
	public static final String ERROR_COD = "P_ERRORCOD";
	public static final String NRO_REINTENTO = "P_NRO_REINTENTO";
	public static final String ID_RECARGA = "P_ID_RECARGA";
	public static final String COD_RESP = "P_COD_RESP_SOL";
	
	public static final String APLICAR_TODOS = "ALL";
	public static final String GRUPO_SISTEMA = "RN_SISTEMA";
	
	public static final Integer EXITO = 0;
	public static final Integer ERROR = 1;
	public static final String OK_RESPONSE = "Invocacion OK";
	

}
