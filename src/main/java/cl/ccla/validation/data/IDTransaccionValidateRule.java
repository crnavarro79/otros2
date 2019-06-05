package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;

public class IDTransaccionValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(IDTransaccionValidateRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[IDTransaccionValidateRule] Validando IDTransaccion(" + regData.getIdTransaccion() + ")...");

		if (regData.getIdTransaccion() == null) {
			throw new IllegalArgumentException("El IdTransaccion no es valido");
		} else {
			if (regData.getIdTransaccion().length() > AppConstants.MAXIMO_LARGO_ID_TRANSACCION
					|| regData.getIdTransaccion().equals("")) {

				throw new IllegalArgumentException("El IdTransaccion no es valido");
			}
			LOG.info("[IDTransaccionValidateRule] IDTransaccion OK!!");
		}
	}

}