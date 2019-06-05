package cl.ccla.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.CashinResponse;
import cl.ccla.DataOutput;
import cl.ccla.ReturnCodeOutput;
import cl.ccla.constants.AppConstants;
import cl.ccla.exception.CertificadosException;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.ReintentoTPPRequest;
import cl.ccla.to.RespuestaTO;

public class AppUtils {

	private AppUtils() {

	}

	final static Logger LOG = Logger.getLogger(AppUtils.class);

	/**
	 * Servicio que valida Rut
	 * 
	 * @param rut
	 * @return
	 */
	public static Boolean validarRut(String rut) {

		Boolean validacion = false;
		try {

			String rutOriginal = rut;
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {

				LOG.info("rut:" + rutOriginal);
				String[] splitSt = rutOriginal.split("-");
				LOG.info("splitSt.length : " + splitSt.length);
				if (splitSt.length > 1) {
					validacion = true;
				}
			}

		} catch (java.lang.NumberFormatException e) {
			LOG.error("Error en el formato del rut", e);
		} catch (Exception e) {
			LOG.error("Error en el formato del rut", e);
		}
		return validacion;
	}

	/**
	 * 
	 * @return
	 * @throws PropertiesException
	 */
	public static Properties cargarPropertiesExterno() throws PropertiesException {
		Properties prop = new Properties();
		InputStream input = null;

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputstream = classloader.getResourceAsStream("application.properties");
		try {
			prop.load(inputstream);

			input = new FileInputStream(prop.getProperty("tap.properties"));
			prop.load(input);
		} catch (IOException e) {
			LOG.error("[cargarProperties] Error en obtener properties externo", e);
			throw new PropertiesException("Error en obtener properties externo [" + e.getMessage() + "]",
					AppConstants.COD_ERROR);

		}

		return prop;
	}

	/**
	 * 
	 * @return
	 * @throws PropertiesException
	 */
	public static Properties cargarPropertiesInterno() throws PropertiesException {
		Properties prop = new Properties();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputstream = classloader.getResourceAsStream("application.properties");
		try {
			prop.load(inputstream);
		} catch (IOException e) {
			LOG.error("[cargarProperties] Error en obtener properties interno", e);
			throw new PropertiesException("Error en obtener properties interno [" + e.getMessage() + "]",
					AppConstants.COD_ERROR);

		}

		return prop;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param entrada
	 * @param idRegistroRecargaTpp
	 * @return
	 */
	public static ReintentoTPPRequest crearObjeto(RecargaTPPRequest entrada, Integer idRegistroRecargaTpp) {

		ReintentoTPPRequest reintentoRequest = new ReintentoTPPRequest();
		reintentoRequest.setIdRecarga(idRegistroRecargaTpp);
		reintentoRequest.setIdSolicitud(entrada.getIdTransaccion());
		reintentoRequest.setCanal(Integer.parseInt(entrada.getCanal()));
		reintentoRequest.setSistema(Integer.parseInt(entrada.getSistema()));
		reintentoRequest.setProducto(Integer.parseInt(entrada.getTipo()));
		reintentoRequest.setRut(entrada.getNumeroDocumento());
		reintentoRequest.setNroContrato(Integer.toString(entrada.getNumeroContrato()));
		reintentoRequest.setMonto(entrada.getMonto());
		reintentoRequest.setFechaSolicitud(entrada.getFechaTransaccion());
		reintentoRequest.setUsuario(entrada.getNombreUsuario());
		reintentoRequest.setMail(entrada.getEmailDestino());
		reintentoRequest.setCodRespuestaSolicitud(0);
		reintentoRequest.setIdAp(0);
		reintentoRequest.setCodRespuestaAp(null);
		reintentoRequest.setGlsRespuestaAp(null);
		reintentoRequest.setFechaRespuestaAp(null);
		reintentoRequest.setFechaReintento(entrada.getFechaTransaccion());
		reintentoRequest.setCodEstrecarga(0);

		return reintentoRequest;
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static RespuestaTO obtenerRespuestaServicioRecarga(CashinResponse response) {

		RespuestaTO respuestaTO = new RespuestaTO();
		DataOutput output = response.getDataOutput();
		ReturnCodeOutput codeOutput = output.getStatus();
		String code = codeOutput.getCode();
		String codTransactionId = codeOutput.getCodTransactionId();
		String message = codeOutput.getMessage();
		double amount = codeOutput.getAmount();
		double updatedBalance = codeOutput.getUpdatedBalance();
		String codResult = codeOutput.getCodResult();
		String contractNumber = codeOutput.getContractNumber();
		String enterpriseId = codeOutput.getEnterpriseId();
		String transactionId = codeOutput.getTransactionId();

		LOG.info("[Operacion Recarga] La respuesta de Andes Prepago fue la siguiente:");
		LOG.info("[Operacion Recarga] El Codigo es: " + code);
		LOG.info("[Operacion Recarga] El Codigo Transaction Id es: " + codTransactionId);
		LOG.info("[Operacion Recarga] El Mensaje es: " + message);
		LOG.info("[Operacion Recarga] El Monto es: " + amount);
		LOG.info("[Operacion Recarga] El Updated Balance es: " + updatedBalance);
		LOG.info("[Operacion Recarga] El Cod Result es: " + codResult);
		LOG.info("[Operacion Recarga] El Contract Number es: " + contractNumber);
		LOG.info("[Operacion Recarga] El Enterprise Id es: " + enterpriseId);
		LOG.info("[Operacion Recarga] El Transaction Id es: " + transactionId);

		respuestaTO.setCodigo(code);
		respuestaTO.setMensaje(message);

		return respuestaTO;
	}

	/**
	 * Metodo que valida Hash
	 * 
	 * @param entrada
	 * @return
	 * @throws CustomException
	 */
	public static String validacionHash(RecargaTPPRequest entrada, Properties properties) throws CertificadosException {
		String respuesta = "OK";

		String hashCreado = TppRoutines.firmaRecarga(entrada, properties);
		String hashObtenido = entrada.getHash();

		LOG.info("[Operacion Recarga] hash__Creado: " + hashCreado);
		LOG.info("[Operacion Recarga] hashObtenido: " + hashObtenido);

		if (!hashCreado.equals(hashObtenido)) {
			respuesta = "El hash enviado no corresponde con los datos de la recarga";
		}

		return respuesta;
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static Long validarSiEsLong(String valor) {

		Long returnInt = null;
		if (valor != null && !valor.equals("") && (AppUtils.isNumeric(valor))) {
			returnInt = Long.parseLong(valor);
		}

		return returnInt;
	}



}
