package cl.ccla.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.CertificadosException;
import cl.ccla.exception.CustomException;
import cl.ccla.factory.RulesFactory;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.validation.business.BusinessRule;
import cl.ccla.validation.data.RegistrationRule;

public class TppRoutines {

	private TppRoutines() {

	}

	private final static Logger LOG = Logger.getLogger(TppRoutines.class);

	/**
	 * Metodo que realiza la firma
	 * 
	 * @param entrada
	 * @return
	 * @throws CustomException
	 * @throws FileNotFoundException
	 */
	public static String firmaRecarga(RecargaTPPRequest entrada, Properties properties) throws CertificadosException {

		String hashSalida = null;
		LOG.info("[Operacion Recarga] Firma Recarga - Inicio");

		InputStream inputstream = null;
		try {
			inputstream = new FileInputStream(properties.getProperty("tap.firmaHash.recarga"));
			hashSalida = generarHash(inputstream,entrada);
			
		} catch (FileNotFoundException e) {
			LOG.error(
					"[Operacion Recarga] Firma Recarga - Ocurrio un error inesperado al intentar realizar la firma de la recarga",
					e);
			throw new CertificadosException("No se pudo cargar el certificado de la firma de recarga["+e.getMessage()+"]",
					AppConstants.COD_ERROR);
		} catch (Exception e) {
			LOG.error(
					"[Operacion Recarga] Firma Recarga - Ocurrio un error inesperado al intentar realizar la firma de la recarga",
					e);
			throw new CertificadosException("Ocurrio un error inesperado al intentar realizar la firma de la recarga["+e.getMessage()+"]",
					AppConstants.COD_ERROR);
		} finally {
			try {
				if (inputstream != null) {
					inputstream.close();
				}

			} catch (IOException e) {
				LOG.error(AppConstants.ERROR_NO_CONTROLADO, e);
			}
		}
		LOG.info("[Operacion Recarga] Firma Recarga - Fin");
		return hashSalida;
	}

	/**
	 * Metodo que realiza el token de la firma del servicio hacia U-Payments
	 * 
	 * @param entrada
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String firmaTokenUpayments(RecargaTPPRequest entrada, Properties properties)
			throws FileNotFoundException {

		String tokenSalida = null;

		InputStream inputstream = null;

		try {

			inputstream = new FileInputStream(properties.getProperty("tap.firmaToken.upayments"));
			tokenSalida = generarHash(inputstream, entrada);
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		} finally {
			try {
				if (inputstream != null) {
					inputstream.close();
				}

			} catch (IOException e) {
				LOG.error(AppConstants.ERROR_NO_CONTROLADO, e);
			}
		}

		return tokenSalida;
	}

	/**
	 * Metodo que realiza el token de la firma del servicio hacia U-Payments
	 * 
	 * @param entrada
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String firmaHashUpayments(RecargaTPPRequest entrada, Properties properties)
			throws FileNotFoundException {

		String hashSalida = null;

		InputStream inputstream = null;

		try {
			inputstream = new FileInputStream(properties.getProperty("tap.firmaHash.upayments"));
			hashSalida = generarHash(inputstream,entrada);
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		} finally {
			try {
				if (inputstream != null) {
					inputstream.close();
				}

			} catch (IOException e) {
				LOG.error(AppConstants.ERROR_NO_CONTROLADO, e);
			}
		}

		return hashSalida;
	}

	/**
	 * Metodo que realiza la validacion de los datos de Canal, Sistema-Producto y
	 * Subproducto
	 * 
	 * @param entrada
	 * @return
	 */
	public static Boolean validaRelacionCanalSistemaSubproducto(int canal, int sistema, int subproducto,
			Properties prop) {
		boolean respuesta = false;
		String[] relacion;
		int tempCanal;
		int tempSistema;
		int tempTipo;

		try {
			String[] relaciones = prop.getProperty("tap.recarga.relacionCanalSistemaSubproducto").split("\\|");

			for (int i = 0; i < relaciones.length; i++) {
				relacion = relaciones[i].split(",");
				tempTipo = Integer.parseInt(relacion[0]);
				tempSistema = Integer.parseInt(relacion[1]);
				tempCanal = Integer.parseInt(relacion[2]);

				if (tempTipo == subproducto && tempSistema == sistema && tempCanal == canal) {
					respuesta = true;
				}
			}
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		}

		return respuesta;
	}
	
	/**
	 * Metodo que realiza la validacion de los datos de Canal, Sistema-Producto 
	 * 
	 * @param entrada
	 * @return
	 */
	public static Boolean validaRelacionCanalSistema(Integer canal, Integer sistema,
			Properties prop) {
		Boolean respuesta = false;
		String[] relacion;
		Integer tempCanal;
		Integer tempSistema;

		try {
			String[] relaciones = prop.getProperty("tap.recarga.relacionSistemaCanal").split("\\|");
			
			LOG.info("[validaRelacionCanalSistema] Sistema/Canal Validos:");
			for (int i = 0; i < relaciones.length; i++) {
				relacion = relaciones[i].split(",");
				tempSistema = Integer.parseInt(relacion[0]);
				tempCanal = Integer.parseInt(relacion[1]);
				LOG.info("[validaRelacionCanalSistema] Sistema:"+tempSistema+",Canal:"+tempCanal);
			}
			
			for (int i = 0; i < relaciones.length; i++) {
				relacion = relaciones[i].split(",");
				tempSistema = Integer.parseInt(relacion[0]);
				tempCanal = Integer.parseInt(relacion[1]);

				if (tempSistema == sistema && tempCanal == canal) {
					respuesta = true;
				}
			}
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		}

		return respuesta;
	}
	
	/**
	 * Metodo que realiza la validacion del sistema, si es valido
	 * 
	 * @param entrada
	 * @return
	 */
	public static Boolean validaSistema(Integer sistema, Properties prop) {
		Boolean respuesta = false;
		Integer tempSistema;

		try {
			String[] sistemasValidos = prop.getProperty("tap.sistemas.validos").split(",");

			LOG.info("[validaSistema] Sistema/Canal Validos:");
			for (int i = 0; i < sistemasValidos.length; i++) {
				tempSistema = Integer.parseInt(sistemasValidos[i]);
				if (tempSistema == sistema) {
					respuesta = true;
				}
			}
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		}

		return respuesta;
	}
	
	/**
	 * Metodo que realiza la validacion del canal, si es valido
	 * 
	 * @param entrada
	 * @return
	 */
	public static Boolean validaCanal(Integer canal, Properties prop) {
		Boolean respuesta = false;
		Integer tempCanal;

		try {
			String[] canalesValidos = prop.getProperty("tap.canales.validos").split(",");

			LOG.info("[validaSistema] Sistema/Canal Validos:");
			for (int i = 0; i < canalesValidos.length; i++) {
				tempCanal = Integer.parseInt(canalesValidos[i]);
				if (tempCanal == canal) {
					respuesta = true;
				}
			}
		} catch (Exception e) {
			LOG.error(AppConstants.ERROR_UPAYMENTS, e);
		}

		return respuesta;
	}


	/**
	 * Metodo que valida datos de entrada
	 * 
	 * @param entrada
	 * @return
	 */
	public static String validacionDatosEntrada(RecargaTPPRequest entrada) {

		String respuesta = "OK";
		RulesFactory factory = new RulesFactory();
		List<RegistrationRule> rules = factory.getRegistrationRules();

		for (RegistrationRule rule : rules) {
			try {
				rule.validate(entrada);
			} catch (Exception e) {
				LOG.error("[Operacion Recarga] Firma Recarga - Error Validacion Datos Entrada ("+e.getMessage()+")");
				respuesta = e.getMessage();
				break;
			}
		}

		return respuesta;
	}

	public static String validacionNegocio(RecargaTPPRequest entrada) {

		String respuesta = "OK";
		RulesFactory factory = new RulesFactory();
		List<BusinessRule> rules = factory.getBusinessRules();

		for (BusinessRule rule : rules) {
			try {
				rule.validate(entrada);
			} catch (CustomException e) {
				LOG.error("[Operacion Recarga] Firma Recarga - Error Validacion Negocio ("+e.getMessage()+")");
				respuesta = e.getMessage();
				break;
			}
		}

		return respuesta;

	}
	
	private static String generarHash(InputStream inputstream,RecargaTPPRequest entrada) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		
		String hashSalida = null;
		byte[] abyte0 = new byte[inputstream.available()];
		inputstream.read(abyte0);
		inputstream.close();
		KeyFactory keyfactory = KeyFactory.getInstance(AppConstants.RSA);
		PrivateKey privatekey = keyfactory.generatePrivate(new PKCS8EncodedKeySpec(abyte0));
		String entradaHash = entrada.getNumeroDocumento() + "|" + entrada.getNumeroContrato() + "|"
				+ entrada.getMonto() + "|" + entrada.getTipo() + "|" + entrada.getSistema() + "|"
				+ entrada.getCanal() + "|" + entrada.getNombreUsuario() + "|" + entrada.getIdTransaccion() + "|"
				+ entrada.getEmailDestino();
		
		Signature signature = Signature.getInstance(AppConstants.SHA1WITH_RSA);
		signature.initSign(privatekey);
		signature.update(entradaHash.getBytes());
		byte[] abyte1 = signature.sign();
		String hash = new String(Base64.encodeBase64(abyte1));
		hashSalida = hash;
		
		return hashSalida;
	}

}
