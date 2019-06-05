package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.utils.AppUtils;

public class NroDocValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(NroDocValidateRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[NroDocValidateRule] Validando NroDoc(" + regData.getNumeroDocumento() + ")...");
		if (regData.getNumeroDocumento() == null) {
			throw new IllegalArgumentException(
					"El NroDoc(RUT) no es valido, debe tener el el DV separado del RUT, ejemplo: 13694285-9");
		} else {
			if (regData.getNumeroDocumento().equals("")
					|| regData.getNumeroDocumento().length() > AppConstants.MAXIMO_LARGO_NUMERO_DOCUMENTO
					|| !AppUtils.validarRut(regData.getNumeroDocumento())) {

				throw new IllegalArgumentException(
						"El NroDoc(RUT) no es valido, debe tener el el DV separado del RUT, ejemplo: 13694285-9");
			}
			LOG.info("[NroDocValidateRule] NroDoc OK!!");
		}
	}
}