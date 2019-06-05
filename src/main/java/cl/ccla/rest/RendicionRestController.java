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
import cl.ccla.rest.response.RendicionResponse;
import cl.ccla.rest.response.TotalizadoRendicionResponse;
import cl.ccla.service.RendicionService;
import cl.ccla.utils.AppUtils;

@RestController
public class RendicionRestController {

	private static final Log LOG = LogFactory.getLog(RendicionRestController.class);

	@Autowired 
	RendicionService rendicionesService;

	@RequestMapping(value = "/obtenerTotalizadoRendiciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<TotalizadoRendicionResponse> obtenerTotalizadoRendiciones(
			@RequestBody FiltroRequest dataInput) {
		LOG.info("[obtenerTotalizadoRendiciones] Inicio");
		LOG.info("[obtenerTotalizadoRendiciones] Entrada: " + dataInput.toString());
		TotalizadoRendicionResponse output = new TotalizadoRendicionResponse();
		try {
			output = rendicionesService.obtenerTotalizadoRendiciones(dataInput);
			LOG.info("[obtenerTotalizadoRendiciones] Salida: " + output.toString());
			return new ResponseEntity<TotalizadoRendicionResponse>(output, HttpStatus.OK);
		} catch (IncompleteDataException e) {
			LOG.error("[obtenerTotalizadoRecargas] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<TotalizadoRendicionResponse>(output,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("[obtenerTotalizadoRendiciones] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<TotalizadoRendicionResponse>(output,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/obtenerRendicionesReporte", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RendicionResponse> obtenerRendicionesReporte(
			@RequestBody FiltroDetalleRequest dataInput) {
		LOG.info("[obtenerTotalizadoRendiciones] Inicio");
		LOG.info("[obtenerTotalizadoRendiciones] Entrada: " + dataInput.toString());
		RendicionResponse output = new RendicionResponse();
		try {
			output = rendicionesService.obtenerRendicionesReporte(dataInput);
			LOG.info("[obtenerTotalizadoRendiciones] Salida: " + output.toString());
			return new ResponseEntity<RendicionResponse>(output, HttpStatus.OK);
		} catch (IncompleteDataException e) {
			LOG.error("[obtenerTotalizadoRecargas] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<RendicionResponse>(output,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("[obtenerTotalizadoRendiciones] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<RendicionResponse>(output,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
