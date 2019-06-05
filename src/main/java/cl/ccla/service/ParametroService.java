package cl.ccla.service;

import javax.naming.NamingException;

import cl.ccla.exception.DataExistsException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.ParametroCrearRequest;
import cl.ccla.rest.request.ParametroRequest;
import cl.ccla.rest.response.ParametroResponse;
import cl.ccla.rest.response.ParametrosResponse;
import cl.ccla.rest.response.ParametrosUpdateResponse;

public interface ParametroService {

	ParametrosResponse obtenerParametros() throws NamingException, PropertiesException, SpException;

	ParametroResponse obtenerParametro(ParametroRequest datainput)
			throws NamingException, PropertiesException, SpException;

	ParametrosUpdateResponse actualizaParametro(ParametroRequest datainput)
			throws NamingException, PropertiesException, SpException, DataExistsException;

	ParametrosUpdateResponse crearParametro(ParametroCrearRequest datainput)
			throws NamingException, PropertiesException, SpException, DataExistsException;
}
