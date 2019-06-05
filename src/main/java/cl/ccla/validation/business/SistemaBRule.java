package cl.ccla.validation.business;

import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.utils.AppUtils;
import cl.ccla.utils.TppRoutines;

public class SistemaBRule implements BusinessRule {

	private final static Logger LOG = Logger.getLogger(SistemaBRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) throws CustomException {

		LOG.info("[SistemaRule] Se valida el sistema...");
		Properties properties;

		try {

			if (regData.getSistema() == null) {
				throw new IllegalArgumentException("El sistema no es valido");
			} else {
				if (regData.getSistema().equals("")
						|| regData.getSistema().length() > AppConstants.MAXIMO_LARGO_SISTEMA_CANAL_SUBPRODUCTO
						|| !AppUtils.isNumeric(regData.getSistema())) {

					throw new IllegalArgumentException("El sistema no es valido");
				} else {
					properties = AppUtils.cargarPropertiesExterno();

					Boolean validacionSistema = TppRoutines.validaSistema(Integer.parseInt(regData.getSistema()),
							properties);

					if (!validacionSistema) {

						throw new CustomException("El sistema no es valido", AppConstants.COD_ERROR);
					} else {
						LOG.info("[SistemaRule] Validacion del sistema exitoso!");
					}
				}
			}

		} catch (PropertiesException e) {
			LOG.error("[SistemaRule]", e);
			throw new CustomException("Error en la carga del properties", AppConstants.COD_ERROR);
		}
		LOG.info("[SistemaRule] Validacion sistema OK!!");

	}

}