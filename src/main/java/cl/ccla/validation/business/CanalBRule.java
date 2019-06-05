package cl.ccla.validation.business;

import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.utils.AppUtils;
import cl.ccla.utils.TppRoutines;

public class CanalBRule implements BusinessRule {

	private final static Logger LOG = Logger.getLogger(CanalBRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) throws CustomException {

		LOG.info("[CanalRule] Se valida el canal...");
		
		try {
			
			Properties properties = AppUtils.cargarPropertiesExterno();
			
			String glosaCanal = properties.getProperty("tap.glosa.validacion.canal");

		
			if (regData.getCanal() == null) {
				throw new IllegalArgumentException(glosaCanal);
			} else {
				if (regData.getCanal().equals("")
						|| regData.getCanal().length() > AppConstants.MAXIMO_LARGO_SISTEMA_CANAL_SUBPRODUCTO
						|| !AppUtils.isNumeric(regData.getCanal())) {

					throw new IllegalArgumentException(glosaCanal);
				}
				else {
				

					Boolean validacionCanal = TppRoutines.validaCanal(Integer.parseInt(regData.getCanal()), properties);

					if (!validacionCanal) {

						throw new CustomException(glosaCanal,
								AppConstants.COD_ERROR);
					} else {
						LOG.info("[CanalRule] Validacion de canal exitoso!");

						LOG.info("[CanalRule] Se valida si servicio se encuentra disponible.");
					}
				}
			}
	
		} catch (PropertiesException e) {
			LOG.error("[CanalRule]",e);
			throw new CustomException("Error en la carga del properties", AppConstants.COD_ERROR);
		}
		LOG.info("[CanalRule] Validacion canal OK!!");

	}

}