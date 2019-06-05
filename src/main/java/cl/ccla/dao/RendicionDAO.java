package cl.ccla.dao;

import java.text.ParseException;

import javax.naming.NamingException;

import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RendicionResponse;
import cl.ccla.rest.response.TotalizadoRendicionResponse;

public interface RendicionDAO {

	TotalizadoRendicionResponse obtenerTotalizadoRendiciones(FiltroRequest filtroRendicionesRequest)
			throws NamingException, PropertiesException, SpException, ParseException;

	RendicionResponse obtenerRendicionesReporte(FiltroDetalleRequest filtroDetalleRendicionRequest)
			throws NamingException, PropertiesException, SpException, ParseException;

}
