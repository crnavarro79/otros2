package cl.ccla.service;

import java.text.ParseException;

import javax.naming.NamingException;

import cl.ccla.exception.DateException;
import cl.ccla.exception.IncompleteDataException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.ProductoResponse;
import cl.ccla.rest.response.RangoMesResponse;
import cl.ccla.rest.response.RecargaResponse;
import cl.ccla.rest.response.TotalizadoRecargaResponse;

public interface RecargaService {

	RecargaResponse obtenerRecargasReporte(FiltroDetalleRequest filtroDetalleRecargaRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException,DateException,ParseException;

	TotalizadoRecargaResponse obtenerTotalizadoRecargas(FiltroRequest filtroRecargaRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException,DateException,ParseException;

	RangoMesResponse obtenerRangoMes() throws NamingException, PropertiesException, SpException;

	ProductoResponse obtenerSistemasProductos() throws NamingException, PropertiesException;
}
