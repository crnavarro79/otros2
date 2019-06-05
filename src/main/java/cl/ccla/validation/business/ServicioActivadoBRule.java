package cl.ccla.validation.business;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.context.AppContext;
import cl.ccla.dao.RecargaTPPDAO;
import cl.ccla.enumeration.ServicioFlagsActivacion;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.UpdateTPPResponse;

public class ServicioActivadoBRule implements BusinessRule {

	private final static Logger LOG = Logger.getLogger(ServicioActivadoBRule.class);

	@Override
	public void validate(RecargaTPPRequest regData) throws CustomException {

		LOG.info(
				"[ServicioActivadoRule] Se valida si el servicio esta activado para el Sistema Producto ingresado....");

		String nombreFlagsActivacionServicio = ServicioFlagsActivacion
				.getNombreParametro(Integer.parseInt(regData.getSistema()));
		RecargaTPPDAO recargaDao = (RecargaTPPDAO) AppContext.getAppContext().getBean("recargaDAO");
		UpdateTPPResponse responseFlagServicio;
		try {
			responseFlagServicio = recargaDao.flagEstado(nombreFlagsActivacionServicio);
		} catch (NamingException e) {
			LOG.error("[ServicioActivadoRule] Error en lookup ",e);
			throw new CustomException(e.getMessage(), AppConstants.COD_ERROR);
		} catch (PropertiesException e) {
			LOG.error("[ServicioActivadoRule] Error en carga de Properties",e);
			throw new CustomException(e.getMessage(), AppConstants.COD_ERROR);
		} catch (SQLException e) {
			LOG.error("[ServicioActivadoRule] Error en invocacion SQL",e);
			throw new CustomException(e.getMessage(), AppConstants.COD_ERROR);
		}

		if (Integer.parseInt(responseFlagServicio.getCodigoRespuesta()) == 1) {
			if (Integer.parseInt(responseFlagServicio.getEstado()) == 0) {
				throw new CustomException("La operación se encuentra temporalmente fuera de servicio.",
						AppConstants.COD_ERROR);
			}
		} else {
			throw new CustomException(responseFlagServicio.getGlosaRespuesta(), AppConstants.COD_ERROR);
		}

		LOG.info("[ServicioActivadoRule] Servicio se encuentra disponible.");

	}

}