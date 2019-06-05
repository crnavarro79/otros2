package cl.ccla.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.IncompleteDataException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.ProductoResponse;
import cl.ccla.rest.response.RangoMesResponse;
import cl.ccla.rest.response.RecargaResponse;
import cl.ccla.rest.response.TotalizadoRecargaResponse;
import cl.ccla.service.RecargaService;
import cl.ccla.utils.AppUtils;

@RestController
public class RecargaRestController {
	
	@Autowired 
	RecargaService recargaService;

	private static final Log LOG = LogFactory.getLog(RecargaRestController.class);

	@RequestMapping(value = "/obtenerTotalizadoRecargas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<TotalizadoRecargaResponse> obtenerTotalizadoRecargas(
			@RequestBody FiltroRequest dataInput) {
		LOG.info("[obtenerTotalizadoRecargas] Inicio");
		LOG.info("[obtenerTotalizadoRecargas] Entrada: " + dataInput.toString());

		TotalizadoRecargaResponse output = new TotalizadoRecargaResponse();
		try {
			output = recargaService.obtenerTotalizadoRecargas(dataInput);
			LOG.info("[obtenerTotalizadoRecargas] Salida: " + output.toString());
			return new ResponseEntity<TotalizadoRecargaResponse>(output, HttpStatus.OK);
		} catch (IncompleteDataException e) {
			LOG.error("[obtenerTotalizadoRecargas] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<TotalizadoRecargaResponse>(output, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("[obtenerTotalizadoRecargas] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<TotalizadoRecargaResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/obtenerRecargasReporte", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RecargaResponse> obtenerRecargasReporte(@RequestBody FiltroDetalleRequest dataInput) {
		LOG.info("[obtenerRecargasReporte] Inicio");
		LOG.info("[obtenerRecargasReporte] Entrada: " + dataInput.toString());
		RecargaResponse output = new RecargaResponse();
		try {
			output = recargaService.obtenerRecargasReporte(dataInput);
			LOG.info("[obtenerRecargasReporte] Salida: " + output.toString());
			return new ResponseEntity<RecargaResponse>(output, HttpStatus.OK);
		} catch (IncompleteDataException e) {
			LOG.error("[obtenerRecargasReporte] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<RecargaResponse>(output, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("[obtenerRecargasReporte] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<RecargaResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "obtenerRangoMes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RangoMesResponse> obtenerRangoMes() {

		LOG.info("[obtenerRangoMes] Inicio");
		RangoMesResponse output = new RangoMesResponse();
		try {
			output = recargaService.obtenerRangoMes();
			LOG.info("[obtenerRangoMes] Salida: " + output.toString());
			return new ResponseEntity<RangoMesResponse>(output, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("[obtenerRangoMes] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<RangoMesResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "obtenerSistemasProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductoResponse> obtenerSistemasProductos() {

		LOG.info("[obtenerSistemasProductos] Inicio");
		ProductoResponse output = new ProductoResponse();
		try {
			output = recargaService.obtenerSistemasProductos();
			LOG.info("[obtenerSistemasProductos] Salida: " + output.getProductoTOs().size() + " registros");
			return new ResponseEntity<ProductoResponse>(output, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("[obtenerSistemasProductos] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<ProductoResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}