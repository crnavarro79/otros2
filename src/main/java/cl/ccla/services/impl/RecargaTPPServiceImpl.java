package cl.ccla.services.impl;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.CashinRequest;
import cl.ccla.CashinResponse;
import cl.ccla.DataInput;
import cl.ccla.constants.AppConstants;
import cl.ccla.context.AppContext;
import cl.ccla.dao.RecargaTPPDAO;
import cl.ccla.exception.CustomException;
import cl.ccla.services.RecargaRoutinesService;
import cl.ccla.services.RecargaTPPService;
import cl.ccla.to.CashinTPPResponseTO;
import cl.ccla.to.GlosaCodTO;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;
import cl.ccla.to.ReintentoTPPRequest;
import cl.ccla.utils.AppUtils;
import cl.ccla.utils.TppRoutines;

public class RecargaTPPServiceImpl implements RecargaTPPService {

	private final static Logger LOG = Logger.getLogger(RecargaTPPServiceImpl.class);

	/**
	 * Metodo principal que realizar todo el proceso de recarga
	 * 
	 * @param entrada
	 * @return
	 */
	@Override
	public RecargaTPPResponse recarga(RecargaTPPRequest entrada) {
		RecargaTPPResponse respuesta = new RecargaTPPResponse();
		String token;
		String hash;

		try {
			
			LOG.info("[Operacion Recarga] Request de Entrada:\n");
			LOG.info(entrada.toString());

			RecargaTPPDAO recargaDao = (RecargaTPPDAO) AppContext.getAppContext().getBean("recargaDAO");
			RecargaRoutinesService recargaRoutinesService = (RecargaRoutinesService) AppContext.getAppContext()
					.getBean("recargaRoutinesService");
			Properties properties = AppUtils.cargarPropertiesExterno();
			
			/*********************************************************************************************
			 * Validacion Data de Entrada
			 *********************************************************************************************/
			
			LOG.info("[Operacion Recarga] Inicio validacion campos request...");
			String respuestaValidacion = TppRoutines.validacionDatosEntrada(entrada);

			if (!respuestaValidacion.equals("OK")) {
				LOG.error("[Operacion Recarga] Ocurrio un error en la validacion del request!");
				throw new CustomException(respuestaValidacion,AppConstants.COD_ERROR);
			}
			LOG.info("[Operacion Recarga] Termino validacion campos request OK");

			/*********************************************************************************************
			 * Validacion Reglas de Negocio
			 *********************************************************************************************/

			LOG.info("[Operacion Recarga] Se realiza la validación de reglas de negocio...");
			String respValidacion = TppRoutines.validacionNegocio(entrada);

			if (!respValidacion.equals("OK")) {
				LOG.error("[Operacion Recarga] Ocurrio un error en la validacion del request!");
				throw new CustomException(respValidacion, AppConstants.COD_ERROR);
			}
			LOG.info("[Operacion Recarga] Termino validación de reglas de negocio OK");
			
			/*********************************************************************************************
			 * CREACION DEL REGISTRO DE RECARGA
			 *********************************************************************************************/
			
			LOG.info("[Operacion Recarga] Se realiza la creacion del registro de recarga...");
			respuesta = recargaRoutinesService.inserRegistro(entrada);
			Integer idRegistroRecargaTpp = Integer.parseInt(respuesta.getIdTransaccion());
			
			LOG.info("[Operacion Recarga] creacion del registro de recarga REALIZADO");

			/*********************************************************************************************
			 * ACTUALIZACION DEL REGISTRO DE RECARGA "FIRMANDO_SOLICITUD"
			 * *******************************************************************************************/
			
			LOG.info("[Operacion Recarga] Actualizacion registro de recarga 'Firmando Solicitud'...");
			recargaRoutinesService.updateRegistro(entrada,
					idRegistroRecargaTpp, AppConstants.AR_FIRMANDO_SOLICITUD_ENVIO_AP);
			LOG.info("[Operacion Recarga] Actualizacion registro de recarga 'Firmando Solicitud' OK"); 
			
			/*********************************************************************************************
			 * ACTUALIZACION DEL REGISTRO DE RECARGA "ESPERANDO RESPUESTA SERVICIO ANDES PREPAGO"
			 *********************************************************************************************/
			
			LOG.info("[Operacion Recarga] Obteniendo las firmas...");
			token = TppRoutines.firmaTokenUpayments(entrada, properties);
			hash = TppRoutines.firmaHashUpayments(entrada, properties);
			LOG.info("[Operacion Recarga] Firmas obtenidas satisfactoriamente");
			LOG.info("[Operacion Recarga] Actualizacion registro de recarga 'Esperando Respuesta Andes Prepago'...");
			recargaRoutinesService.updateRegistro(entrada, idRegistroRecargaTpp,
					AppConstants.AR_ESPERANDO_RESP_AP);
			LOG.info("[Operacion Recarga] Actualizacion registro de recarga 'Esperando Respuesta Andes Prepago' OK");
			
			/*********************************************************************************************
			 * LLAMANDO AL SERVICIO DE RECARGA
			 *********************************************************************************************/
			
			LOG.info("[Operacion Recarga] LLamando a servicio de recarga de Andes Prepago...");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			DataInput input = new DataInput();
			input.setAmount(entrada.getMonto());
			input.setChannel(Integer.parseInt(entrada.getCanal()));
			input.setContractNumber(Integer.toString(entrada.getNumeroContrato()));
			input.setDateTime(dateFormat.format(entrada.getFechaTransaccion()));
			input.setHash(hash);
			input.setId(entrada.getNumeroDocumento());
			input.setToken(token);
			input.setType(Integer.parseInt(entrada.getTipo()));

			CashinRequest request = new CashinRequest();
			request.setDataInput(input);
			
			LOG.info("[Operacion Recarga] Datos del Request CashIn:");
			LOG.info("[Operacion Recarga] Amount:"+entrada.getMonto());
			LOG.info("[Operacion Recarga] Channel:"+entrada.getCanal());
			LOG.info("[Operacion Recarga] ContractNumber:"+entrada.getNumeroContrato());
			LOG.info("[Operacion Recarga] DateTime:"+dateFormat.format(entrada.getFechaTransaccion()));
			LOG.info("[Operacion Recarga] Hash:"+hash);
			LOG.info("[Operacion Recarga] Id:"+entrada.getNumeroDocumento().replace("-", ""));
			LOG.info("[Operacion Recarga] Token:"+token);
			LOG.info("[Operacion Recarga] Type:"+entrada.getTipo());

			LOG.info("[Operacion Recarga] Se obtiene la cantidad de reintentos por hacer...");

			Integer cantidadReintentos = recargaRoutinesService.obtenerReintentos();

			if (cantidadReintentos == 0) {

				respuesta.setIdTransaccion(null);
				respuesta.setIdTransaccionAndesPrepago(null);
				respuesta.setCodigoRespuesta(AppConstants.COD_ERROR);
				respuesta.setGlosaRespuesta(AppConstants.ERROR_OBTENER_DATOS_REINTENTO);
				return respuesta;

			}

			LOG.info("[Operacion Recarga] Se obtuvieron los reintentos correctamente (" + cantidadReintentos + ")");

			int flagReintento = 1;
			boolean estadoReintento = true;
			CashinResponse response = new CashinResponse();
			String errorWebservice ="";

			ReintentoTPPRequest reintentoRequest = AppUtils.crearObjeto(entrada, idRegistroRecargaTpp);

			while (flagReintento <= cantidadReintentos) {
				LOG.info("[Operacion Recarga] Reintento numero " + flagReintento + "...");
				CashinTPPResponseTO responseTemp = recargaRoutinesService.llamarServicioRecargaAP(request, properties);
				if (responseTemp.isEstado()) {
					LOG.info("[Operacion Recarga] responseTemp.isEstado OK");
					response = responseTemp.getResponse();
					estadoReintento = true;
					errorWebservice = "";
					break;
				
				} else {
					errorWebservice = responseTemp.getError();
					reintentoRequest.setNroReintento(flagReintento);
					String respuestaReintento = recargaRoutinesService.crearReintento(reintentoRequest, flagReintento,
							idRegistroRecargaTpp);
					LOG.info("[Operacion Recarga] respuestaReintento: "+respuestaReintento);
					if (!"OK".equals(respuestaReintento)) {
						respuesta.setIdTransaccion(null);
						respuesta.setIdTransaccionAndesPrepago(null);
						respuesta.setCodigoRespuesta(AppConstants.COD_ERROR);
						respuesta.setGlosaRespuesta(respuestaReintento);

						return respuesta;
					}
					flagReintento++;
					estadoReintento = false;
				}
				
			}

			LOG.info("[Operacion Recarga] estadoReintento: "+estadoReintento);
			if (!estadoReintento) {
				respuesta.setIdTransaccion(null);
				respuesta.setIdTransaccionAndesPrepago(null);
				respuesta.setCodigoRespuesta(AppConstants.COD_ERROR);
				respuesta.setGlosaRespuesta("No hay comunicacion con CashIn, despues de " + cantidadReintentos + " reintentos ("+errorWebservice+"), URL:"+properties.getProperty("tap.cashin.wsdl"));
				LOG.error("[Operacion Recarga] No hay comunicacion con CashIn, despues de " + cantidadReintentos + " reintentos ("+errorWebservice+"), URL:"+properties.getProperty("tap.cashin.wsdl"));
				return respuesta;
			}

			LOG.info("[Operacion Recarga] Termino llamada a Servicio de recarga de Andes Prepago OK");

			/*********************************************************************************************
			 * ACTUALIZACION DEL REGISTRO DE RECARGA "ENVIANDO RESP ANDES PREPAGO"
			 *********************************************************************************************/
			
			String codigoRespuestaRecarga = response.getDataOutput().getStatus().getCode();
			LOG.info("[Operacion Recarga] Actualizacion registro de recarga 'Enviando respuesta Andes Prepago'...");
			recargaRoutinesService.actualizaEstado(idRegistroRecargaTpp, codigoRespuestaRecarga);
			GlosaCodTO glosaCodTO = recargaDao.getGlosaCodRecarga(codigoRespuestaRecarga);
			
		
			

			/*********************************************************************************************
			 * ACTUALIZACION FINAL DEL REGISTRO DE RECARGA
			 *********************************************************************************************/
			
			
			if (glosaCodTO != null) {
				LOG.info("[Operacion Recarga] Se realizo exitosamente el mapeo de la respuesta recibida en Andes Prepago.");
				LOG.info("[Operacion Recarga] Se realiza la actualizacion final registro de recarga...");
				recargaRoutinesService.actualizaRegistroFinal(response, Integer.toString(idRegistroRecargaTpp), entrada,
						glosaCodTO.getCodigoTpp());
				LOG.info("[Operacion Recarga] Actualizacion final registro de recarga OK");
				respuesta.setIdTransaccionAndesPrepago(response.getDataOutput().getStatus().getCodTransactionId());
				LOG.info("[Operacion Recarga] Glosa: " + glosaCodTO.getGlosa());
				respuesta.setCodigoRespuesta(codigoRespuestaRecarga);
				respuesta.setGlosaRespuesta(glosaCodTO.getGlosa());
			}else {
				LOG.info("[Operacion Recarga] No se encontro mapeo de respuesta, no se puede realizar la actualizacion final...");
				respuesta.setCodigoRespuesta(codigoRespuestaRecarga);
				respuesta.setGlosaRespuesta("No se encuentra registrado el codigo de retorno UP ["+codigoRespuestaRecarga+"] en Tpp");
			}
	
	
		} catch (CustomException e) {
			respuesta.setCodigoRespuesta(e.getCode());
			respuesta.setGlosaRespuesta(e.getMessage());
			LOG.error(AppConstants.ERROR_INESPERADO_SISTEMA, e);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(AppConstants.COD_ERROR);
			respuesta.setGlosaRespuesta(e.getMessage());
			LOG.error(AppConstants.ERROR_INESPERADO_SISTEMA, e);
		}
		
		respuesta.setIdTransaccion(entrada.getIdTransaccion());
		
		LOG.info("[Operacion Recarga] Response de Salida:\n");
		LOG.info(respuesta.toString());

		return respuesta;
	}

}
