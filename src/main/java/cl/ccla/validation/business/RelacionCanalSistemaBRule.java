package cl.ccla.validation.business;

import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.utils.AppUtils;
import cl.ccla.utils.TppRoutines;

public class RelacionCanalSistemaBRule implements BusinessRule {

	private final static Logger LOG = Logger.getLogger(RelacionCanalSistemaBRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) throws CustomException {

		LOG.info("[RelacionCanalSistemaRule] Se valida la relacion canal sistema...");
		Properties properties;
		try {
			properties = AppUtils.cargarPropertiesExterno();

			Boolean validacionCS = TppRoutines.validaRelacionCanalSistema(Integer.parseInt(regData.getCanal()),
					Integer.parseInt(regData.getSistema()), properties);

			if (!validacionCS) {

				throw new CustomException("No existe relacion entre el canal sistema ingresado",
						AppConstants.COD_ERROR);
			} else {
				LOG.info("[RelacionCanalSistemaRule] Validacion de relacion canal sistema  exitosa.");
			}

		} catch (PropertiesException e) {
			LOG.error("[RelacionCanalSistemaRule]",e);
			throw new CustomException("Error en la carga del properties", AppConstants.COD_ERROR);
		}
		LOG.info("[RelacionCanalSistemaRule] Validacion relacion canal sistema OK!!");

	}

}