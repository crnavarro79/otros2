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
import cl.ccla.dao.RecargaDAO;
import cl.ccla.exception.PropertiesException;
import cl.ccla.exception.SpException;
import cl.ccla.rest.request.FiltroDetalleRequest;
import cl.ccla.rest.request.FiltroRequest;
import cl.ccla.rest.response.RangoMesResponse;
import cl.ccla.rest.response.RecargaResponse;
import cl.ccla.rest.response.TotalizadoRecargaResponse;
import cl.ccla.to.RecargaTO;
import cl.ccla.to.RecargaTotalizadoTO;
import cl.ccla.utils.AppUtils;

@Repository
public class RecargaDAOImpl implements RecargaDAO {

	private static final Logger LOG = Logger.getLogger(RecargaDAOImpl.class);
	
	@Autowired
	private DataSource dataSourceTpp;


	public RecargaResponse obtenerRecargasReporte(FiltroDetalleRequest filtroDetalleRecargaRequest)
			throws NamingException, PropertiesException, SpException, ParseException {

		LOG.info("[obternerRecargasReporte] Inicio");
		LOG.info("[obternerRecargasReporte] Entrada: " + filtroDetalleRecargaRequest.toString());

		List<RecargaTO> dataList = new ArrayList<RecargaTO>();
		RecargaResponse dataOutput = new RecargaResponse();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_SERVICIO_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_RECARGA_DETALLE);
		
		LOG.info("[obternerRecargasReporte] Fecha Inicio: "+AppUtils.convertToDate(filtroDetalleRecargaRequest.getFechaInicio()));
		LOG.info("[obternerRecargasReporte] Fecha Fin: "+AppUtils.convertToDate(filtroDetalleRecargaRequest.getFechaFin()));

		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_FECHAINI",  AppUtils.convertToDate(filtroDetalleRecargaRequest.getFechaInicio()));
		in.addValue("P_FECHAFIN", AppUtils.convertToDate(filtroDetalleRecargaRequest.getFechaFin()));
		in.addValue("P_SIST_PROD", filtroDetalleRecargaRequest.getSistemaProducto());
		in.addValue("P_ESTADO", filtroDetalleRecargaRequest.getCodigoAP());

		Map<String, Object> mapReturn = jdbcCall.execute(in);
		String mensajeError;

		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) mapReturn.get("P_LISTA_RESP");

		mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			for (HashMap<String, Object> hashMapRecharge : hashMap) {

				RecargaTO data = new RecargaTO();
				data.setCanal(((BigDecimal) hashMapRecharge.get("CANAL")).intValue());
				data.setSistema(((BigDecimal) hashMapRecharge.get("SISTEMA")).intValue());
				data.setProducto(((BigDecimal) hashMapRecharge.get("PRODUCTO")).intValue());
				data.setRutAfiliado(((BigDecimal) hashMapRecharge.get("RUT_AFILIADO")).intValue());
				data.setDvAfiliado((String) hashMapRecharge.get("DV_AFILIADO"));
				data.setNroContrato(((BigDecimal) hashMapRecharge.get("NRO_CONTRATO")).intValue());
				data.setMontoRecarga(((BigDecimal) hashMapRecharge.get("MONTO_RECARGA")).intValue());
				data.setFechaOperacion((Date) hashMapRecharge.get("FECHA_OPERACION"));
				data.setEstadoTransaccion((String) hashMapRecharge.get("ESTADO_TRANSACCION"));
				data.setCodTransaccionAp(((BigDecimal) hashMapRecharge.get("COD_TRANSACCION_AP")).intValue());
				data.setCodTransaccionTpp(((BigDecimal) hashMapRecharge.get("COD_TRANSACCION_TPP")).intValue());
				data.setCodTransaccionSp((String) hashMapRecharge.get("COD_TRANSACCION_SP"));
				data.setMensajeAp((String) hashMapRecharge.get("MENSAJE_AP"));
				data.setCodigoAp(((BigDecimal) hashMapRecharge.get("CODIGO_AP")).intValue());
				dataList.add(data);
			}
			dataOutput.setRecargas(dataList);
		} else {
			throw new SpException(mensajeError);
		}
		
		LOG.info("[obternerRecargasReporte] Salida: " + dataOutput.toString());
		LOG.info("[obternerRecargasReporte] Fin");

		return dataOutput;
	}

	public TotalizadoRecargaResponse obtenerTotalizadoRecargas(FiltroRequest filtroRecargaRequest)
			throws NamingException, PropertiesException, SpException, ParseException {

		LOG.info("[obtenerTotalizadoRecargas] Inicio");
		LOG.info("[obtenerTotalizadoRecargas] Entrada: " + filtroRecargaRequest.toString());

		List<RecargaTotalizadoTO> dataList = new ArrayList<RecargaTotalizadoTO>();
		TotalizadoRecargaResponse dataOutput = new TotalizadoRecargaResponse();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);

		jdbcCall.withCatalogName(AppConstants.PKG_TPP_SERVICIO_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_TOTALIZADO_RECARGA);
		
		LOG.info("[obtenerTotalizadoRecargas] Fecha Inicio: "+AppUtils.convertToDate(filtroRecargaRequest.getFechaInicio()));
		LOG.info("[obtenerTotalizadoRecargas] Fecha Fin: "+AppUtils.convertToDate(filtroRecargaRequest.getFechaFin()));


		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("P_FECHAINI",  AppUtils.convertToDate(filtroRecargaRequest.getFechaInicio()));
		in.addValue("P_FECHAFIN",  AppUtils.convertToDate(filtroRecargaRequest.getFechaFin()));
		in.addValue("P_SIST_PROD", filtroRecargaRequest.getSistemaProducto());

		Map<String, Object> mapReturn = jdbcCall.execute(in);

		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> hashMap = (ArrayList<HashMap<String, Object>>) mapReturn.get("P_LISTA_RESP");
		String mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			for (HashMap<String, Object> hashMapRecharge : hashMap) {

				RecargaTotalizadoTO data = new RecargaTotalizadoTO();
				data.setSistema(((BigDecimal) hashMapRecharge.get("SISTEMA")).intValue());
				data.setCodEstadoAp((String) hashMapRecharge.get("COD_ESTADO_AP"));
				data.setEstado((String) hashMapRecharge.get("ESTADO"));
				data.setProcesados(((BigDecimal) hashMapRecharge.get("PROCESADOS")).intValue());
				data.setTotalMonto(((BigDecimal) hashMapRecharge.get("TOTAL_MONTO")).intValue());
				dataList.add(data);
			}
			dataOutput.setRecargaTotalizados(dataList);
		} else {
			throw new SpException(mensajeError);
		}

		
		LOG.info("[obternerRecargasReporte] Salida: " + dataOutput.toString());
		LOG.info("[obternerRecargasReporte] Fin");
		return dataOutput;
	}

	public RangoMesResponse obtenerRangoMes() throws NamingException, PropertiesException, SpException {

		LOG.info("[obtenerRangoMes] Inicio");
		RangoMesResponse dataOutput = new RangoMesResponse();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourceTpp);
		jdbcCall.withCatalogName(AppConstants.PKG_TPP_SERVICIO_WEB);
		jdbcCall.withProcedureName(AppConstants.PRC_SELECT_RANGO_MES);
		Map<String, Object> mapReturn = jdbcCall.execute();

		String mensajeError = (String) mapReturn.get(AppConstants.ERROR_MSG);

		if (mensajeError == null) {
			Integer rango = ((BigDecimal) mapReturn.get("P_VALOR")).intValue();
			dataOutput.setRango(rango);

		} else {
			throw new SpException(mensajeError);
		}

		LOG.info("[obtenerRangoMes] Salida: " + dataOutput.toString());
		LOG.info("[obtenerRangoMes] Fin");
		return dataOutput;
	}

}