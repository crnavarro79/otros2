package cl.ccla.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import cl.ccla.constants.AppConstants;
import cl.ccla.dao.ParametroDAO;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.ParametroCrearRequest;
import cl.ccla.rest.response.ParametroResponse;
import cl.ccla.rest.response.ParametrosResponse;
import cl.ccla.rest.response.ParametrosUpdateResponse;
import cl.ccla.to.ParametroTO;

@Repository
public class ParametroDAOImpl implements ParametroDAO {

	private static final Logger LOG = Logger.getLogger(ParametroDAOImpl.class);

	@Autowired
	private DataSource dataSourceTpp;

	public ParametrosResponse obtenerParametros() throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerParametros] Inicio");

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_MANTENEDOR_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_SELECT_CONFIGURACIONES);

		Map<String, Object> mapReturn = jdbcCall.execute();
		ParametrosResponse dataOutput = obtenerDataFromResponse(mapReturn);

		LOG.info("[obtenerParametros] Salida: " + dataOutput.toString());
		LOG.info("[obtenerParametros] Fin");
		return dataOutput;
	}

	public ParametroResponse obtenerParametro(Integer idParametro)
			throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerParametro] Inicio");

		ParametroResponse parametroResponse = new ParametroResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_MANTENEDOR_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_SELECT_CONFIGURACION);

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_ID", idParametro);

		Map<String, Object> mapReturn = jdbcCall.execute(in);
		ParametrosResponse dataOutput = obtenerDataFromResponse(mapReturn);
		
		if (!dataOutput.getParametros().isEmpty()) {
			parametroResponse.setParametro(dataOutput.getParametros().get(0));
		}

		LOG.info("[obtenerParametro] Salida: " + dataOutput.toString());
		LOG.info("[obtenerParametro] Fin");
		return parametroResponse;
	}

	public ParametrosUpdateResponse actualizaParametro(Integer idParametro, String valor, Integer esActivo)
			throws NamingException, PropertiesException, SpException {
		LOG.info("[actualizaParametro] Inicio");

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_MANTENEDOR_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_UPDATE_CONFIGURACION);

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_TPPC_ID",idParametro);
		in.addValue("P_TPPC_VALOR", valor);
		in.addValue("P_TPPC_EST_ACTIVO", esActivo);
		in.addValue("P_USER_ACT", "PGOMEZ");

		ParametrosUpdateResponse dataOutput = new ParametrosUpdateResponse();
		Map<String, Object> mapReturn = jdbcCall.execute(in);

		String mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);
		BigDecimal estadoActualizacion = (BigDecimal) mapReturn.get("P_ESTADO");

		if (mensajeError == null) {
			dataOutput.setEstado(estadoActualizacion.intValue());
			dataOutput.setGlosa("Actualizacion satisfactoria");
		} else {
			throw new SpException(mensajeError);
		}

		LOG.info("[actualizaParametro] Salida: " + dataOutput.toString());
		LOG.info("[actualizaParametro] Fin");
		return dataOutput;
	}

	public ParametrosUpdateResponse crearParametro(ParametroCrearRequest dataInput)
			throws NamingException, PropertiesException, SpException {
		LOG.info("[crearParametro] Inicio");

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_MANTENEDOR_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_INSERT_CONFIGURACION);

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_TPPC_DATO", dataInput.getNombre());
		in.addValue("P_TPPC_VALOR", dataInput.getValor());
		in.addValue("P_TPPC_GRUPO", dataInput.getGrupo());
		in.addValue("P_TPPC_APLICAR", dataInput.getAplicar());
		in.addValue("P_TPPC_USER_REGISTRO", "PGOMEZ");

		ParametrosUpdateResponse dataOutput = new ParametrosUpdateResponse();
		Map<String, Object> mapReturn = jdbcCall.execute(in);

		String mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);
		BigDecimal estado = (BigDecimal) mapReturn.get("P_ESTADO");
	

		if (mensajeError == null) {
			dataOutput.setEstado(estado.intValue());
			dataOutput.setGlosa("Insercion satisfactoria");
		} else {
			throw new SpException(mensajeError);
		}

		LOG.info("[crearParametro] Salida: " + dataOutput.toString());
		LOG.info("[crearParametro] Fin");
		return dataOutput;
	}

	private ParametrosResponse obtenerDataFromResponse(Map<String, Object> mapReturn) throws SpException {

		ParametrosResponse dataOutput = new ParametrosResponse();
		List<ParametroTO> dataList = new ArrayList<ParametroTO>();
		String mensajeError;

		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) mapReturn.get("P_LISTA_RESP");

		mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			for (HashMap<String, Object> hashMapParam : hashMap) {

				ParametroTO data = new ParametroTO();
				data.setIdParametro(((BigDecimal) hashMapParam.get("ID_PARAMETRO")).intValue());
				data.setDescripcion((String) hashMapParam.get("DESCRIPCION"));
				data.setValor(((BigDecimal) hashMapParam.get("VALOR")).intValue());
				data.setEstado(((BigDecimal) hashMapParam.get("ESTADO")).intValue());
				dataList.add(data);
			}

			dataOutput.setParametros(dataList);
		} else {
			throw new SpException(mensajeError);
		}

		return dataOutput;
	}

	public ParametroResponse obtenerParametro(String nombre) throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerParametro] Inicio");

		ParametroResponse parametroResponse = new ParametroResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_MANTENEDOR_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_SELECT_CONFIGURACION_NOMBRE);

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_DATO", nombre);

		Map<String, Object> mapReturn = jdbcCall.execute(in);
		ParametrosResponse dataOutput = obtenerDataFromResponse(mapReturn);
		
		if (!dataOutput.getParametros().isEmpty()) {
			parametroResponse.setParametro(dataOutput.getParametros().get(0));
		}

		LOG.info("[obtenerParametro] Salida: " + dataOutput.toString());
		LOG.info("[obtenerParametro] Fin");
		return parametroResponse;
	}

}