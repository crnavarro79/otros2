package cl.ccla.constants;

public class AppConstants {

	public static final String ERROR_INSERT_REINTENTOS = "Ocurrio un error inesperado al intentar insertar la cantidad de reintentos";
	public static final String ERROR_ACT_REINTENTOS = "Ocurrio un error inesperado al intentar actualizar la cantidad de reintentos";
	public static final String ERROR_REINTENTOS = "Ocurrio un error inesperado al intentar obtener la cantidad de reintentos";
	public static final String ERROR_ESTADO = "Ocurrio un error inesperado al intentar obtener el estado del servicio";
	public static final String ERROR_MAPEO = "Ocurrio un error al intentar mapear la respuesta";
	public static final String MENSAJE_ERROR = "Ocurrio un error inesperado al realizar el cambio de estado de la recarga";
	public static final String PARAM_MSG_ERROR_SP = "P_ERRORMSG";
	public static final String PARAM_ERROR_SP = "P_ERRORCOD";
	public static final String PARAM_IDTRANSACCION = "P_IDTRANS_TPP";
	public static final String TAP_DATASOURCE_JNDI = "tap.datasource.jndi";
	public static final String EMAIL_PATTERN = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"
			+ "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
	public static final String RSA = "RSA";
	public static final String SHA1WITH_RSA = "SHA1withRSA";
	public static final String ERROR_UPAYMENTS = "Ocurrio un error inesperado al intentar realizar la firma de la recarga para U-Payments";
	public static final String ERROR_NO_CONTROLADO = "Error no controlado\n";
	public static final String ERROR_OBTENER_DATOS_REINTENTO = "No se pudo obtener los datos de reintento";
	public static final String ERROR_INESPERADO_SISTEMA = "Ocurrio un error inesperado en el sistema.";

	private AppConstants() {

	}

	public static final Integer MAXIMO_VALOR_INTEGER = 2146000000;
	public static final Integer MAXIMO_VALOR_CONTRATO = 999999999;
	public static final Integer MAXIMO_LARGO_NUMERO_DOCUMENTO = 10;
	public static final Integer MAXIMO_LARGO_SISTEMA_CANAL_SUBPRODUCTO = 2;
	public static final Integer MAXIMO_LARGO_NOMBRE_USUARIO = 20;
	public static final Integer MAXIMO_LARGO_ID_TRANSACCION = 15;
	public static final Integer MAXIMO_LARGO_EMAIL = 60;
	public static final String SP_CREDITO = "1";
	public static final String SP_LICENCIA_MEDICA = "2";
	public static final String SP_BENEFICIO = "3";
	public static final String SP_AHORRO = "4";

	// Codigos U-Payments
	public static final String COD_TRANSACCION_OK = "000";
	public static final String COD_CONTRATO_NO_EXISTE = "012";
	public static final String COD_RUT_NO_EXISTE = "014";
	public static final String COD_CANAL_NO_EXISTE = "015";
	public static final String COD_TOKEN_EXPIRADO = "016";
	public static final String COD_ERROR = "001";

	// Codigos Tpp
	public static final String COD_ERROR_NO_CONTROLADO = "023";
	public static final String COD_ERROR_PROPERTIES = "022";
	public static final String COD_ERROR_COMUNICACION_CASHIN = "021";
	public static final String COD_HASH_ERRONEO = "020";
	public static final String COD_DESACTIVACION_SERVICIO = "024";

	// Codigos Glosa Acciones Recarga
	public static final Integer AR_CREAR_SOLICITUD_SP = 0;
	public static final Integer AR_VALIDANDO_FIRMA_SP = 1;
	public static final Integer AR_VALIDANDO_REGLAS_NEGOCIO = 2;
	public static final Integer AR_FIRMANDO_SOLICITUD_ENVIO_AP = 10;
	public static final Integer AR_ESPERANDO_RESP_AP = 11;
	public static final Integer AR_ENVIANDO_RESP_A_SP = 12;
	public static final Integer AR_ERROR_EN_ESPERA_RESP_AP = 13;

	public static final String SOLICITUD_OK = "1";

	public static final Integer COD_OK_INT_SP = 1;
	public static final String COD_OK_SP = "1";
	public static final String COD_NOK_SP = "0";

	public static final String SCHEMA = "tap.schema.recarga";
	public static final String PACKAGE = "tap.package.recarga";

	public static final String ID_TRANSACCION_TPP = "P_IDTRANS_TPP";
	public static final String ERROR_MSG = "P_ERRORMSG";
	public static final String ERROR_COD = "P_ERRORCOD";
	public static final String NRO_REINTENTO = "P_NRO_REINTENTO";
	public static final String ID_RECARGA = "P_ID_RECARGA";
	public static final String COD_RESP = "P_COD_RESP_SOL";

}
