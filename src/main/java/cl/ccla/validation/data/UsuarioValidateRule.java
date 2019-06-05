package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;

public class UsuarioValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(UsuarioValidateRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[UsuarioValidateRule] Validando Usuario(" + regData.getNombreUsuario() + ")...");
		if (regData.getNombreUsuario() == null) {
			throw new IllegalArgumentException("El usuario no es valido");
		} else {

			if (regData.getNombreUsuario().length() > AppConstants.MAXIMO_LARGO_NOMBRE_USUARIO
					|| regData.getNombreUsuario().equals("")) {

				throw new IllegalArgumentException("El usuario no es valido");
			}
		}
		LOG.info("[UsuarioValidateRule] Usuario OK!!");
	}

}