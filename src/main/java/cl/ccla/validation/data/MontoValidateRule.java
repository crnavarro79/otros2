package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;

public class MontoValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(MontoValidateRule.class);
	
	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[MontoValidateRule] Validando Monto ("+regData.getMonto()+")...");
		if (regData.getMonto() == 0 || regData.getMonto() < 0
				|| regData.getMonto() > AppConstants.MAXIMO_VALOR_INTEGER) {

			throw new IllegalArgumentException("El monto no es valido");
		}
		LOG.info("[MontoValidateRule] Monto OK!!");
	}

}