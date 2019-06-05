package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.utils.AppUtils;

public class TipoValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(TipoValidateRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[TipoValidateRule] Validando Tipo(" + regData.getTipo() + ")...");

		if (regData.getTipo() == null) {
			throw new IllegalArgumentException("El tipo no es valido");
		} else {
			if (regData.getTipo().equals("")
					|| regData.getTipo().length() > AppConstants.MAXIMO_LARGO_SISTEMA_CANAL_SUBPRODUCTO
					|| !AppUtils.isNumeric(regData.getTipo())) {

				throw new IllegalArgumentException("El tipo no es valido");
			}
		}
	}

}