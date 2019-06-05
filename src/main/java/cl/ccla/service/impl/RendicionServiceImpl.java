package cl.ccla.service.impl;

import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccla.dao.RendicionDAO;
import cl.ccla.exception.DateException;
import cl.ccla.exception.IncompleteDataException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RendicionResponse;
import cl.ccla.rest.response.TotalizadoRendicionResponse;
import cl.ccla.service.RendicionService;
import cl.ccla.utils.AppUtils;

@Service
public class RendicionServiceImpl implements RendicionService {

	private static final Logger LOG = Logger.getLogger(RendicionServiceImpl.class);

	@Autowired
	private RendicionDAO rendicionDAO;

	public TotalizadoRendicionResponse obtenerTotalizadoRendiciones(FiltroRequest filtroRendicionesRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, ParseException,
			DateException {
		LOG.info("[obtenerTotalizadoRendiciones] Inicio");

		if (filtroRendicionesRequest.getFechaInicio() == null || filtroRendicionesRequest.getFechaFin() == null) {
			throw new IncompleteDataException("Los datos de entrada no pueden ser vacios");
		}
		if (!AppUtils.isValidDate(filtroRendicionesRequest.getFechaInicio())
				|| !AppUtils.isValidDate(filtroRendicionesRequest.getFechaFin())) {
			throw new DateException("El formato de fecha es incorrecto, debe ser YYYY-MM-DD");
		}

		TotalizadoRendicionResponse response = rendicionDAO.obtenerTotalizadoRendiciones(filtroRendicionesRequest);
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public RendicionResponse obtenerRendicionesReporte(FiltroDetalleRequest filtroDetalleRendicionRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, ParseException,
			DateException {
		LOG.info("[obtenerRendicionesReporte] Inicio");
		if (filtroDetalleRendicionRequest.getFechaInicio() == null
				|| filtroDetalleRendicionRequest.getFechaFin() == null) {
			throw new IncompleteDataException("Los datos de entrada no pueden ser vacios");
		}
		if (!AppUtils.isValidDate(filtroDetalleRendicionRequest.getFechaInicio())
				|| !AppUtils.isValidDate(filtroDetalleRendicionRequest.getFechaFin())) {
			throw new DateException("El formato de fecha es incorrecto, debe ser YYYY-MM-DD");
		}

		RendicionResponse response = rendicionDAO.obtenerRendicionesReporte(filtroDetalleRendicionRequest);
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

}
