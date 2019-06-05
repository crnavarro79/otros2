package cl.ccla.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.constants.FormatoFechaEnum;
import cl.ccla.exception.PropertiesException;
import cl.ccla.rest.response.Status;

public class AppUtils {

	private static final  Logger LOG = Logger.getLogger(AppUtils.class);

	
	private AppUtils() {

	}


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

			int m = 0;
			int s = 1;
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
	
	public static Properties cargarPropertiesExternoV2()  {
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
	
	public static Status getStatusException(Integer codigo, Exception e) {
		Status status = new Status();
		status.setCodigo(codigo);
		status.setMensaje(e.getMessage());
		status.setExcepcion(e.getClass().getName());
		return status;
	}
	
	public static Status getStatusOK() {
		Status status = new Status();
		status.setCodigo(AppConstants.EXITO);
		status.setMensaje(AppConstants.OK_RESPONSE);
		status.setExcepcion("N/A");
		return status;
	}
	
	public static Date convertToDate(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FormatoFechaEnum.FORMATO_BACKEND.getPatron());
		return sdf.parse(fecha);
	}
	
	public static Boolean isValidDate(String fecha) {
		Boolean isValid = true;
		LOG.info("[isValidDate] fecha: "+fecha);
		SimpleDateFormat sdf = new SimpleDateFormat(FormatoFechaEnum.FORMATO_BACKEND.getPatron());
		try {
			sdf.parse(fecha);

		} catch (ParseException e) {
			isValid = false;
			LOG.info("[isValidDate] Error: ",e);
		}

		return isValid;
	}
	
	public static Boolean validarRol(String rol) {
		
		Boolean isOk = false;
		String [] rolesValidos = AppUtils.cargarPropertiesExternoV2().getProperty("tap.perfiles.validos").split(";");
		
		LOG.info("Roles Validos:");
		for (String rolValido: rolesValidos) {
			LOG.info("->"+rolValido);
		}
		
		for (String rolValido: rolesValidos) {
			if(rol.equals(rolValido)) {
				isOk = true;
				LOG.info("Rol ["+rolValido+"] es valido!!");
				break;
			}
		}
		
		return isOk;
	}

}
