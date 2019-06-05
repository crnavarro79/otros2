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
import cl.ccla.exception.AutenticationServiceException;
import cl.ccla.rest.request.LoginRequest;
import cl.ccla.rest.response.UsuarioResponse;
import cl.ccla.service.LoginService;
import cl.ccla.utils.AppUtils;

@RestController
public class LoginRestController {
	
	private static final Log LOG = LogFactory.getLog(LoginRestController.class);
	
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UsuarioResponse> login(@RequestBody LoginRequest input) {
		LOG.info("[login] Entrada: " + input.toString());
		UsuarioResponse output = new UsuarioResponse();
		try {

			output = loginService.login(input);

			return new ResponseEntity<UsuarioResponse>(output, HttpStatus.OK);

		} catch (AutenticationServiceException e) {
			LOG.error("[login] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<UsuarioResponse>(output, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			LOG.error("[login] Error: ", e);
			output.setStatus(AppUtils.getStatusException(AppConstants.ERROR, e));
			return new ResponseEntity<UsuarioResponse>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
