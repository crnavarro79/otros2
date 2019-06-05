package cl.ccla.dao;

import java.text.ParseException;

import javax.naming.NamingException;

import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RangoMesResponse;
import cl.ccla.rest.response.RecargaResponse;
import cl.ccla.rest.response.TotalizadoRecargaResponse;

public interface RecargaDAO {

	RecargaResponse obtenerRecargasReporte(FiltroDetalleRequest filtroDetalleRecargaRequest)
			throws NamingException, PropertiesException, SpException,ParseException;

	TotalizadoRecargaResponse obtenerTotalizadoRecargas(FiltroRequest filtroRecargaRequest)
			throws NamingException, PropertiesException, SpException,ParseException;

	RangoMesResponse obtenerRangoMes() throws NamingException, PropertiesException, SpException;

}