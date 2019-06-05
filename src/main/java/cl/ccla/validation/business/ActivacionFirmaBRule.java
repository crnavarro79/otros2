package cl.ccla.validation.business;

import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.context.AppContext;
import cl.ccla.dao.RecargaTPPDAO;
import cl.ccla.enumeration.FirmaFlagsActivacion;
import cl.ccla.exception.CertificadosException;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.UpdateTPPResponse;
import cl.ccla.utils.AppUtils;

public class ActivacionFirmaBRule implements BusinessRule {

	private final static Logger LOG = Logger.getLogger(ActivacionFirmaBRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) throws CustomException {
		LOG.info("[ActivacionFirmaBusinessRule] Validando Activacion de Firma...");
		String nombreParametroActivacionFirma = FirmaFlagsActivacion
				.getNombreParametro(Integer.parseInt(regData.getSistema()));
		String nombreSistema = FirmaFlagsActivacion.getNombreSistema(Integer.parseInt(regData.getSistema()));

		if (nombreSistema == null || nombreSistema.trim().equals("null")) {
			throw new CustomException("Codigo Sistema no Existe", AppConstants.COD_ERROR);
		}

		RecargaTPPDAO recargaDao = (RecargaTPPDAO) AppContext.getAppContext().getBean("recargaDAO");

		try {
			Properties properties = AppUtils.cargarPropertiesExterno();
			UpdateTPPResponse responseFlagFirma = recargaDao.flagEstado(nombreParametroActivacionFirma);

			if (Integer.parseInt(responseFlagFirma.getCodigoRespuesta()) == 1
					&& Integer.parseInt(responseFlagFirma.getEstado()) != 0) {

				LOG.info("[ActivacionFirmaBusinessRule] La utilizacion del Hash para el sistema producto ["
						+ nombreSistema + "] esta ACTIVADO");
				LOG.info("[ActivacionFirmaBusinessRule] Se realiza la validación del hash de entrada.");

				String respValidacionHash = AppUtils.validacionHash(regData, properties);

				if (respValidacionHash != null && !respValidacionHash.equals("OK")) {
					throw new CustomException(respValidacionHash, AppConstants.COD_ERROR);
				}

			} else {
				LOG.info("[ActivacionFirmaBusinessRule] La utilizacion del Hash para el sistema producto ["
						+ nombreSistema + "] esta DESACTIVADO");
			}

		} catch (CertificadosException e) {
			LOG.error("[ActivacionFirmaBusinessRule] Error en el procesado del Hash",e);
			throw new CustomException(e.getMessage(),e.getCode());
		} catch (PropertiesException e) {
			LOG.error("[ActivacionFirmaBusinessRule] Error en carga de Properties",e);
			throw new CustomException(e.getMessage(), AppConstants.COD_ERROR);
		}catch (Exception e) {
			LOG.error("[ActivacionFirmaBusinessRule] Error no Controlado",e);
			throw new CustomException(e.getMessage(), AppConstants.COD_ERROR);
		}

		LOG.info("[ActivacionFirmaBusinessRule] Validacion Activacion de Firma OK!!");

	}

}