package cl.ccla.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import cl.ccla.dao.RendicionDAO;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RendicionResponse;
import cl.ccla.rest.response.TotalizadoRendicionResponse;
import cl.ccla.to.RendicionTO;
import cl.ccla.to.RendicionTotalizadoTO;
import cl.ccla.utils.AppUtils;

@Repository
public class RendicionDAOImpl implements RendicionDAO {

	
	private static final Logger LOG = Logger.getLogger(RecargaDAOImpl.class);
	
	@Autowired
	private DataSource dataSourceTpp;

	public TotalizadoRendicionResponse obtenerTotalizadoRendiciones(FiltroRequest filtroRendicionesRequest)
			throws NamingException, PropertiesException, SpException, ParseException {
		LOG.info("[obtenerTotalizadoRendiciones] Inicio");
		LOG.info("[obtenerTotalizadoRendiciones] Entrada: " + filtroRendicionesRequest.toString());

		List<RendicionTotalizadoTO> dataList = new ArrayList<RendicionTotalizadoTO>();
		TotalizadoRendicionResponse dataOutput = new TotalizadoRendicionResponse();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_SERVICIO_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_TOTALIZADO_RENDICIONES);
		
		LOG.info("[obtenerTotalizadoRendiciones] Fecha Inicio: "+AppUtils.convertToDate(filtroRendicionesRequest.getFechaInicio()));
		LOG.info("[obtenerTotalizadoRendiciones] Fecha Fin: "+AppUtils.convertToDate(filtroRendicionesRequest.getFechaFin()));

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_FECHAINI",  AppUtils.convertToDate(filtroRendicionesRequest.getFechaInicio()));
		in.addValue("P_FECHAFIN",  AppUtils.convertToDate(filtroRendicionesRequest.getFechaFin()));
		in.addValue("P_SIST_PROD", filtroRendicionesRequest.getSistemaProducto());

		Map<String, Object> mapReturn = jdbcCall.execute(in);
		String mensajeError;

		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) mapReturn.get("P_LISTA_RESP");

		mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			for (HashMap<String, Object> hashMapRecharge : hashMap) {

				RendicionTotalizadoTO data = new RendicionTotalizadoTO();
				data.setCodRespAp(((String) hashMapRecharge.get("COD_RESP_AP")));
				data.setGlosaAp((String) hashMapRecharge.get("GLOSA_AP"));
				data.setCanal(((BigDecimal) hashMapRecharge.get("CANAL")).intValue());
				data.setDescCanal((String) hashMapRecharge.get("DESC_CANAL"));
				data.setProcesados(((BigDecimal) hashMapRecharge.get("PROCESADOS")).intValue());
				data.setMontoTotal(((BigDecimal) hashMapRecharge.get("MONTO_TOTAL")).intValue());
				dataList.add(data);
			}
			dataOutput.setRendicionTotalizados(dataList);
		} else {
			throw new SpException(mensajeError);
		}

		LOG.info("[obtenerTotalizadoRendiciones]  Salida: " + dataOutput.toString());
		LOG.info("[obtenerTotalizadoRendiciones] Fin");
		return dataOutput;
	}

	public RendicionResponse obtenerRendicionesReporte(FiltroDetalleRequest filtroDetalleRendicionRequest)
			throws NamingException, PropertiesException, SpException, ParseException {
		LOG.info("[obtenerRendicionesReporte] Inicio");
		LOG.info("[obtenerRendicionesReporte] Entrada: " + filtroDetalleRendicionRequest.toString());

		List<RendicionTO> dataList = new ArrayList<RendicionTO>();
		RendicionResponse dataOutput = new RendicionResponse();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_SERVICIO_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_RENDICIONES_DETALLE);
		
		LOG.info("[obtenerRendicionesReporte] Fecha Inicio: "+AppUtils.convertToDate(filtroDetalleRendicionRequest.getFechaInicio()));
		LOG.info("[obtenerRendicionesReporte] Fecha Fin: "+AppUtils.convertToDate(filtroDetalleRendicionRequest.getFechaFin()));

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_FECHAINI", AppUtils.convertToDate(filtroDetalleRendicionRequest.getFechaInicio()));
		in.addValue("P_FECHAFIN", AppUtils.convertToDate(filtroDetalleRendicionRequest.getFechaFin()));
		in.addValue("P_SIST_PROD", filtroDetalleRendicionRequest.getSistemaProducto());
		in.addValue("P_ESTADO", filtroDetalleRendicionRequest.getCodigoAP());

		Map<String, Object> mapReturn = jdbcCall.execute(in);
		String mensajeError;

		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) mapReturn.get("P_LISTA_RESP");
		mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			for (HashMap<String, Object> hashMapRecharge : hashMap) {

				RendicionTO data = new RendicionTO();
				data.setIdAp(((BigDecimal) hashMapRecharge.get("ID_AP")).intValue());
				data.setNroContrato(((BigDecimal) hashMapRecharge.get("NRO_CONTRATO")).intValue());
				data.setFecha((Date) hashMapRecharge.get("FECHA"));
				data.setRutAfiliado(((BigDecimal) hashMapRecharge.get("RUT_AFILIADO")).intValue());
				data.setDvAfiliado((String) hashMapRecharge.get("DV_AFILIADO"));
				data.setMonto(((BigDecimal) hashMapRecharge.get("MONTO")).intValue());
				data.setCodAp((String) hashMapRecharge.get("COD_AP"));
				data.setGlsAp((String) hashMapRecharge.get("GLS_AP"));
				data.setCanal(((BigDecimal) hashMapRecharge.get("CANAL")).intValue());
				data.setProducto(((BigDecimal) hashMapRecharge.get("PRODUCTO")).intValue());
				dataList.add(data);
			}
			dataOutput.setRendiciones(dataList);
		} else {
			throw new SpException(mensajeError);
		}

		LOG.info("[obtenerRendicionesReporte]  Salida: " + dataOutput.toString());
		LOG.info("[obtenerRendicionesReporte] Fin");
		return dataOutput;
	}

}
