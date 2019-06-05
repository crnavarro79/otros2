package cl.ccla.dao;

import javax.naming.NamingException;

import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.ParametroCrearRequest;
import cl.ccla.rest.response.ParametroResponse;
import cl.ccla.rest.response.ParametrosResponse;
import cl.ccla.rest.response.ParametrosUpdateResponse;

public interface ParametroDAO {

	ParametrosResponse obtenerParametros() throws NamingException, PropertiesException, SpException;

	ParametroResponse obtenerParametro(Integer idParametro)
			throws NamingException, PropertiesException, SpException;
	
	ParametroResponse obtenerParametro(String nombre)
			throws NamingException, PropertiesException, SpException;


	ParametrosUpdateResponse actualizaParametro(Integer idParametro, String valor, Integer esActivo)
			throws NamingException, PropertiesException, SpException;

	ParametrosUpdateResponse crearParametro(ParametroCrearRequest datainput)
			throws NamingException, PropertiesException, SpException;
}