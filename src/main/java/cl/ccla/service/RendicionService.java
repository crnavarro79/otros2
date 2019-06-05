package cl.ccla.service;

import java.text.ParseException;

import javax.naming.NamingException;

import cl.ccla.exception.DateException;
import cl.ccla.exception.IncompleteDataException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RendicionResponse;
import cl.ccla.rest.response.TotalizadoRendicionResponse;

public interface RendicionService {
	TotalizadoRendicionResponse obtenerTotalizadoRendiciones(FiltroRequest filtroRendicionesRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, ParseException, DateException;

	RendicionResponse obtenerRendicionesReporte(FiltroDetalleRequest filtroDetalleRendicionRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, ParseException, DateException;
}
