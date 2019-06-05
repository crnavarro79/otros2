package cl.ccla.validation.data;

import org.apache.log4j.Logger;

import cl.ccla.to.RecargaTPPRequest;

public class FechaValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(FechaValidateRule.class);
	
	@Override
	public void validate(RecargaTPPRequest regData) {
		LOG.info("[FechaValidateRule] Validando Fecha("+regData.getFechaTransaccion()+")...");
		if (regData.getFechaTransaccion() == null) {

			throw new IllegalArgumentException("La fecha no es valida. Debe tener formato YYYY-MM-DDT00:00:00, ejemplo 2019-01-11T00:00:00");
		}
		LOG.info("[FechaValidateRule] Fecha OK!!");
	}

}