package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;

public class NroContratoValidateRule implements RegistrationRule {
	
	private static final Logger LOG = Logger.getLogger(NroContratoValidateRule.class);
	
	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[NroContratoValidateRule] Validando NroContrato("+regData.getNumeroContrato()+")...");
		if (regData.getNumeroContrato() == 0 || regData.getNumeroContrato() > AppConstants.MAXIMO_VALOR_CONTRATO) {

			throw new IllegalArgumentException("El Numero de Contrato no es valido");
		}
		LOG.info("[NroContratoValidateRule] NroContrato OK!!");
	}

}