package cl.ccla.services.impl;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.sun.xml.ws.client.BindingProviderProperties;

import cl.ccla.CashinPort;
import cl.ccla.CashinPortService;
import cl.ccla.CashinRequest;
import cl.ccla.CashinResponse;
import cl.ccla.DataInput;
import cl.ccla.DataOutput;
import cl.ccla.ReturnCodeOutput;
import cl.ccla.constants.AppConstants;
import cl.ccla.context.AppContext;
import cl.ccla.dao.RecargaTPPDAO;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.services.RecargaRoutinesService;
import cl.ccla.to.CashinTPPResponseTO;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;
import cl.ccla.to.ReintentoTPPRequest;
import cl.ccla.to.ReintentoTPPResponse;
import cl.ccla.to.UpdateTPPRequest;
import cl.ccla.to.UpdateTPPResponse;

public class RecargaRoutinesServiceImpl implements RecargaRoutinesService {

	private final static Logger LOG = Logger.getLogger(RecargaRoutinesServiceImpl.class);

	public static RecargaTPPDAO recargaDao = (RecargaTPPDAO) AppContext.getAppContext().getBean("recargaDAO");

	public Integer obtenerReintentos() throws NamingException, PropertiesException, SQLException {

		Integer cantidadReintentos;
		ReintentoTPPResponse reintentoResponse = recargaDao.selectReintento();
		if (reintentoResponse.getCodigo() != 1) {
			cantidadReintentos = 0;
		} else {
			cantidadReintentos = Integer.parseInt(reintentoResponse.getDato());
		}

		return cantidadReintentos;
	}

	/**
	 * Metodo que graba los reintentos
	 * 
	 * @param request
	 * @param nroReintento
	 * @param id
	 * @return
	 */
	public String crearReintento(ReintentoTPPRequest request, Integer nroReintento, Integer idRegistroRecargaTpp) {
		String respuesta = "OK";
		ReintentoTPPResponse response = new ReintentoTPPResponse();

		try {
			response = recargaDao.insertReintento(request);
			if (response.getCodigo() != 1) {

				return "Ocurrio un error al realizar el insert del reintento";
			}
			response = recargaDao.updateReintento(nroReintento, idRegistroRecargaTpp);
			if (response.getCodigo() != 1) {

				return "Ocurrio un error al realizar el update del reintento";
			}
		} catch (Exception e) {
			respuesta = "Ocurrio un error en el reintento.";

			LOG.error("Ocurrio un error en el reintento", e);
		}

		return respuesta;
	}

	/**
	 * Metodo que invoca al API de U-payment
	 * 
	 * @param request
	 * @param prop
	 * @return
	 */
	public CashinTPPResponseTO llamarServicioRecargaAP(CashinRequest request, final Properties prop) {
		CashinTPPResponseTO response = new CashinTPPResponseTO();
		LOG.info("[Operacion Recarga] Llamada a Servicio CashIn...");
		Long tiempoInicial = System.currentTimeMillis();
		LOG.info("[Operacion Recarga] URL Servicio: " + prop.getProperty("tap.cashin.wsdl"));
		LOG.info("[Operacion Recarga] TimeOut Request Servicio: " + prop.getProperty("tap.timeout.request")
				+ " milisegundos");
		LOG.info("[Operacion Recarga] TimeOut Conexion Servicio: " + prop.getProperty("tap.timeout.conexion")
		+ " milisegundos");

		try {

			CashinPortService service = new CashinPortService(new URL(prop.getProperty("tap.cashin.wsdl")));
			CashinPort port = service.getCashinPortSoap11();
			BindingProvider bp = (BindingProvider)port;
	
			Map<String, Object> requestContext = bp.getRequestContext();
			requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, Integer.parseInt(prop.getProperty("tap.timeout.request"))); // Timeout in millis
			requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, Integer.parseInt(prop.getProperty("tap.timeout.conexion"))); // Timeout in millis
			
			DataInput input = request.getDataInput();

			LOG.info("[Operacion Recarga] Datos Entrada Servicio CashIn:");
			LOG.info("[Operacion Recarga] getAmount:" + input.getAmount());
			LOG.info("[Operacion Recarga] getChannel:" + input.getChannel());
			LOG.info("[Operacion Recarga] getContractNumber:" + input.getContractNumber());
			LOG.info("[Operacion Recarga] getDateTime:" + input.getDateTime());
			LOG.info("[Operacion Recarga] getHash:" + input.getHash());
			LOG.info("[Operacion Recarga] getId:" + input.getId());
			LOG.info("[Operacion Recarga] getToken:" + input.getToken());
			LOG.info("[Operacion Recarga] getType:" + input.getType());

			CashinResponse resp = port.cashin(request);

			if (resp != null) {
				DataOutput dataOutput = resp.getDataOutput();
				if (dataOutput != null) {
					ReturnCodeOutput returnData = dataOutput.getStatus();
					LOG.info("[Operacion Recarga] Respuesta Servicio CashIn:");
					LOG.info("[Operacion Recarga] getAmount:" + returnData.getAmount());
					LOG.info("[Operacion Recarga] getCode:" + returnData.getCode());
					LOG.info("[Operacion Recarga] getCodResult:" + returnData.getCodResult());
					LOG.info("[Operacion Recarga] getCodTransactionId:" + returnData.getCodTransactionId());
					LOG.info("[Operacion Recarga] getContractNumber:" + returnData.getContractNumber());
					LOG.info("[Operacion Recarga] getEnterpriseId:" + returnData.getEnterpriseId());
					LOG.info("[Operacion Recarga] getMessage:" + returnData.getMessage());
					LOG.info("[Operacion Recarga] getTransactionId:" + returnData.getTransactionId());
					LOG.info("[Operacion Recarga] getUpdatedBalance:" + returnData.getUpdatedBalance());
					response.setResponse(resp);
					response.setEstado(true);
				} else {
					LOG.error("[Operacion Recarga] Error en retorno de datos del servicio");
					response.setEstado(false);
					response.setError("[Operacion Recarga] Error en retorno de datos del servicio");
				}
			} else {
				LOG.error("[Operacion Recarga] Error en llamada al servicio, respuesta nula");
				response.setEstado(false);
				response.setError("[Operacion Recarga] Error en llamada al servicio");
			}

			Long tiempoFinal = System.currentTimeMillis();
			Long tiempoTranscurrido = tiempoFinal - tiempoInicial;

			LOG.info("[Main] Tiempo de Respuesta Servicio CashIn: " + tiempoTranscurrido + " milisegundos");

		} catch (Exception e) {
			LOG.error("[Operacion Recarga] Error en comunicacion API CashIn", e);
			response.setEstado(false);
			response.setError(e.getMessage());
		}
		return response;
	}

	public RecargaTPPResponse inserRegistro(RecargaTPPRequest entrada)
			throws CustomException, NamingException, PropertiesException, SQLException {
		RecargaTPPResponse respuesta = recargaDao.insertRegistro(entrada);

		switch (Integer.parseInt(respuesta.getCodigoRespuesta())) {

		case 0:
			return respuesta;
		default:

			throw new CustomException(respuesta.getGlosaRespuesta(), AppConstants.COD_ERROR);

		}
	}

	public void updateRegistro(RecargaTPPRequest entrada, Integer idTransaccionTpp, Integer estadoAccion)
			throws CustomException, NumberFormatException, NamingException, PropertiesException, SQLException {

		UpdateTPPResponse updateEstado = recargaDao.updateEstado(estadoAccion, idTransaccionTpp);

		if (!AppConstants.SOLICITUD_OK.equals(updateEstado.getCodigoRespuesta())) {
			throw new CustomException(updateEstado.getGlosaRespuesta(), AppConstants.COD_ERROR);
		}

	}

	public void actualizaEstado(Integer idRegistroRecargaTpp, String code)
			throws CustomException, NumberFormatException, NamingException, PropertiesException, SQLException {

		if (Integer.parseInt(code) == 0) {
			UpdateTPPResponse updateEstado = recargaDao.updateEstado(AppConstants.AR_ENVIANDO_RESP_A_SP,
					idRegistroRecargaTpp);
			if (!AppConstants.SOLICITUD_OK.equals(updateEstado.getCodigoRespuesta())) {
				throw new CustomException(updateEstado.getGlosaRespuesta(), AppConstants.COD_ERROR);
			}
		} else {
			UpdateTPPResponse updateEstado = recargaDao.updateEstado(AppConstants.AR_ERROR_EN_ESPERA_RESP_AP,
					idRegistroRecargaTpp);
			if (!AppConstants.SOLICITUD_OK.equals(updateEstado.getCodigoRespuesta())) {
				throw new CustomException(updateEstado.getGlosaRespuesta(), AppConstants.COD_ERROR);
			}
		}
	}

	public void actualizaRegistroFinal(CashinResponse response, String idRegistroRecargaTpp, RecargaTPPRequest entrada,
			Integer codigoRespuestaTpp) throws CustomException, NamingException, PropertiesException, SQLException {

		UpdateTPPRequest updateRequest = new UpdateTPPRequest();
		updateRequest.setCodigoRespuestaAP(response.getDataOutput().getStatus().getCode());
		updateRequest.setIdTransaccionTpp(idRegistroRecargaTpp);
		updateRequest.setFechaAP(entrada.getFechaTransaccion());
		updateRequest.setIdTransaccionAP(response.getDataOutput().getStatus().getCodTransactionId());
		updateRequest.setMensajeRespuestaAP(response.getDataOutput().getStatus().getMessage());
		updateRequest.setCodigoRespuestaSP(codigoRespuestaTpp);

		UpdateTPPResponse updateResponse = recargaDao.updateRegistro(updateRequest);

		if (!updateResponse.getEstado().equals(AppConstants.SOLICITUD_OK)) {
			throw new CustomException(updateResponse.getGlosaRespuesta(), AppConstants.COD_ERROR);

		}
	}

}
