package cl.ccla.service.impl;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccla.constants.AppConstants;
import cl.ccla.dao.ParametroDAO;
import cl.ccla.exception.DataExistsException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.ParametroCrearRequest;
import cl.ccla.rest.request.ParametroRequest;
import cl.ccla.rest.response.ParametroResponse;
import cl.ccla.rest.response.ParametrosResponse;
import cl.ccla.rest.response.ParametrosUpdateResponse;
import cl.ccla.service.ParametroService;
import cl.ccla.utils.AppUtils;

@Service
public class ParametroServiceImpl implements ParametroService {

	private static final Logger LOG = Logger.getLogger(ParametroServiceImpl.class);

	@Autowired
	private ParametroDAO parametrosDAO;

	public ParametrosResponse obtenerParametros() throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerParametros] Inicio");
		ParametrosResponse response = parametrosDAO.obtenerParametros();
		LOG.info("[obtenerParametros] Salida:" + response.toString());
		LOG.info("[obtenerParametros] Fin");
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public ParametroResponse obtenerParametro(ParametroRequest datainput)
			throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerParametros dataInput] Inicio");
		LOG.info("[actualizaParametro] Entrada: " + datainput.toString());
		ParametroResponse response = parametrosDAO.obtenerParametro(datainput.getIdParametro());
		LOG.info("[obtenerParametros dataInput] Salida:" + response.toString());
		LOG.info("[obtenerParametros dataInput] Fin");
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public ParametrosUpdateResponse actualizaParametro(ParametroRequest datainput)
			throws NamingException, PropertiesException, SpException, DataExistsException {
		LOG.info("[actualizaParametro] Inicio");
		LOG.info("[actualizaParametro] Entrada: " + datainput.toString());
		
		if (parametrosDAO.obtenerParametro(datainput.getIdParametro()).getParametro()==null){
			throw new DataExistsException("El parametro no existe");
		}
		ParametrosUpdateResponse response = parametrosDAO.actualizaParametro(datainput.getIdParametro(),
				datainput.getValor(), datainput.getEsActivo());
		LOG.info("[actualizaParametro] Salida:" + response.toString());
		LOG.info("[actualizaParametro] Fin");
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public ParametrosUpdateResponse crearParametro(ParametroCrearRequest datainput)
			throws NamingException, PropertiesException, SpException, DataExistsException {
		LOG.info("[crearParametro] Inicio");

		datainput.setAplicar(AppConstants.APLICAR_TODOS);
		datainput.setGrupo(AppConstants.GRUPO_SISTEMA);

		ParametrosUpdateResponse response = null;

		ParametroResponse parametroResponse = parametrosDAO.obtenerParametro(datainput.getNombre());

		if (parametroResponse.getParametro() != null) {
			throw new DataExistsException("El parametro ya existe");
		} else {
			response = parametrosDAO.crearParametro(datainput);
		}
		LOG.info("[crearParametro] Salida:" + response.toString());
		LOG.info("[crearParametro] Fin");
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

}
