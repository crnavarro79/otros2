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
import cl.ccla.rest.request.ParametroCrearRequest;
import cl.ccla.rest.request.ParametroRequest;
import cl.ccla.rest.response.ParametroResponse;
import cl.ccla.rest.response.ParametrosResponse;
import cl.ccla.rest.response.ParametrosUpdateResponse;
import cl.ccla.service.ParametroService;
import cl.ccla.utils.AppUtils;

@RestController
public class ParametroRestController {

	private static final Log LOG = LogFactory.getLog(ParametroRestController.class);
	
	@Autowired 
	ParametroService parametroService;

	@RequestMapping(value = "obtenerParametros", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ParametrosResponse> obtenerParametros() {
		LOG.info("[obtenerParametros] Inicio");
		ParametrosResponse output = new ParametrosResponse();
		try {

			output = parametroService.obtenerParametros();
			LOG.info("[obtenerParametros] Salida: " + output.toString());
			return new ResponseEntity<ParametrosResponse>(output, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("[obtenerParametros] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<ParametrosResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/obtenerParametro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ParametroResponse> obtenerParametro(@RequestBody ParametroRequest dataInput) {
		ParametroResponse output = new ParametroResponse();
		try {

			output = parametroService.obtenerParametro(dataInput);
			return new ResponseEntity<ParametroResponse>(output, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("[obtenerParametros] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<ParametroResponse>(output,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/actualizaParametro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ParametrosUpdateResponse> actualizaParametro(@RequestBody ParametroRequest dataInput) {
		ParametrosUpdateResponse output = new ParametrosUpdateResponse();
		try {

			output = parametroService.actualizaParametro(dataInput);
			return new ResponseEntity<ParametrosUpdateResponse>(output, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("[actualizaParametro] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<ParametrosUpdateResponse>(output,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/crearParametro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ParametrosUpdateResponse> crearParametro(@RequestBody ParametroCrearRequest dataInput) {

		ParametrosUpdateResponse output = new ParametrosUpdateResponse();
		try {

			output = parametroService.crearParametro(dataInput);
			return new ResponseEntity<ParametrosUpdateResponse>(output, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("[crearParametro] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<ParametrosUpdateResponse>(output,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}