package cl.ccla.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import cl.ccla.constants.AppConstants;
import cl.ccla.dao.RecargaTPPDAO;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.GlosaCodTO;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;
import cl.ccla.to.ReintentoTPPRequest;
import cl.ccla.to.ReintentoTPPResponse;
import cl.ccla.to.UpdateTPPRequest;
import cl.ccla.to.UpdateTPPResponse;
import cl.ccla.utils.AppUtils;
import cl.ccla.utils.ContextoSingleton;

public class RecargaTPPDAOImpl implements RecargaTPPDAO {

	static final Logger LOG = Logger.getLogger(RecargaTPPDAOImpl.class);

	public RecargaTPPResponse insertRegistro(RecargaTPPRequest entrada)
			throws NamingException, PropertiesException, SQLException {
		RecargaTPPResponse respuesta = new RecargaTPPResponse();
		LOG.info("[insertRegistro] Inicio");
		LOG.info("[insertRegistro] entrada:" + entrada.toString());
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();

	
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE)).withProcedureName("PRC_INSERT_REGISTRO")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("P_NROCONTRATO", Types.NUMERIC),
						new SqlParameter("P_RUT", Types.VARCHAR), 
						new SqlParameter("P_MONTO", Types.NUMERIC),
						new SqlParameter("P_TIPO", Types.NUMERIC), 
						new SqlParameter("P_CANAL", Types.NUMERIC),
						new SqlParameter("P_SISTEMA", Types.NUMERIC), 
						new SqlParameter("P_FECHA_SP", Types.DATE),
						new SqlParameter("P_USUARIO", Types.VARCHAR), 
						new SqlParameter("P_IDTRANS_SP", Types.VARCHAR),
						new SqlParameter("P_EMAIL", Types.VARCHAR), 
						new SqlOutParameter(AppConstants.ID_TRANSACCION_TPP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_COD, Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_MSG, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("P_NROCONTRATO", entrada.getNumeroContrato());
		mapSqlParameterSource.addValue("P_RUT", entrada.getNumeroDocumento());
		mapSqlParameterSource.addValue("P_MONTO", entrada.getMonto());
		mapSqlParameterSource.addValue("P_TIPO", entrada.getTipo());
		mapSqlParameterSource.addValue("P_CANAL", entrada.getCanal());
		mapSqlParameterSource.addValue("P_SISTEMA", entrada.getSistema());
		mapSqlParameterSource.addValue("P_FECHA_SP", entrada.getFechaTransaccion());
		mapSqlParameterSource.addValue("P_USUARIO", entrada.getNombreUsuario());
		mapSqlParameterSource.addValue("P_IDTRANS_SP", entrada.getIdTransaccion());
		mapSqlParameterSource.addValue("P_EMAIL", entrada.getEmailDestino());

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);

		BigDecimal idTransaccion = (BigDecimal) out.get(AppConstants.PARAM_IDTRANSACCION);
		BigDecimal codigoError = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);

		String mensajeError = (String) out.get(AppConstants.PARAM_MSG_ERROR_SP);

		if (mensajeError == null) {
			mensajeError = "Transaccion realizada exitosamente";
		} else {
			mensajeError = (String) out.get(AppConstants.PARAM_MSG_ERROR_SP);
		}

		respuesta.setIdTransaccion(idTransaccion.toString());
		respuesta.setCodigoRespuesta(codigoError.toString());
		respuesta.setGlosaRespuesta(mensajeError);

		LOG.info("[insertRegistro] Salida: "+respuesta.toString());
		
		return respuesta;
	}

	@Override
	public UpdateTPPResponse updateRegistro(UpdateTPPRequest entrada)
			throws NamingException, PropertiesException, SQLException {
		UpdateTPPResponse respuesta = new UpdateTPPResponse();
		LOG.info("[updateRegistro] Inicio");
		LOG.info("[updateRegistro] Entrada:" + entrada.toString());
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE)).withProcedureName("PRC_UPDATE_REGISTRO")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("P_IDTRANS_AP", Types.NUMERIC),
						new SqlParameter("P_CODRESP_AP", Types.VARCHAR),
						new SqlParameter("P_GLSRESP_AP", Types.VARCHAR), 
						new SqlParameter("P_FECHA_AP", Types.DATE),
						new SqlParameter(AppConstants.ID_TRANSACCION_TPP, Types.NUMERIC),
						new SqlParameter(AppConstants.COD_RESP, Types.NUMERIC),
						new SqlOutParameter("P_ESTADOUPD", Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_COD, Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_MSG, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		
		mapSqlParameterSource.addValue("P_IDTRANS_AP", AppUtils.validarSiEsLong(entrada.getIdTransaccionAP()));
		mapSqlParameterSource.addValue("P_CODRESP_AP", entrada.getCodigoRespuestaAP());
		mapSqlParameterSource.addValue("P_GLSRESP_AP", entrada.getMensajeRespuestaAP());
		mapSqlParameterSource.addValue("P_FECHA_AP", entrada.getFechaAP());
		mapSqlParameterSource.addValue(AppConstants.ID_TRANSACCION_TPP, Integer.parseInt(entrada.getIdTransaccionTpp()));
		mapSqlParameterSource.addValue(AppConstants.COD_RESP, entrada.getCodigoRespuestaSP());
		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);

		BigDecimal estado = (BigDecimal) out.get("P_ESTADOUPD");
		BigDecimal codigoError = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);
		String mensajeError = (String) out.get(AppConstants.PARAM_MSG_ERROR_SP);

		if (estado != null && estado.intValue() == AppConstants.COD_OK_INT_SP) {
			respuesta.setEstado(estado.toString());
			mensajeError = "Update realizado exitosamente";
		} else {
			respuesta.setEstado(AppConstants.COD_NOK_SP);
			respuesta.setCodigoRespuesta(codigoError.toString());
		}
		respuesta.setGlosaRespuesta(mensajeError);
		LOG.info("[updateRegistro] Salida: "+respuesta);
		return respuesta;
	}

	@Override
	public UpdateTPPResponse updateEstado(int codigo, int idRegistroRecargaTpp)
			throws NamingException, PropertiesException, SQLException {
		UpdateTPPResponse respuesta = new UpdateTPPResponse();
		BigDecimal errorCode;
		LOG.info("[updateEstado] Inicio");
		LOG.info("[updateEstado] codigo:" + codigo);
		LOG.info("[updateEstado] idRegistroRecargaTpp:" + idRegistroRecargaTpp);
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();

		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE)).withProcedureName("PRC_UPDATE_ESTADO")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("P_CODESTADO", Types.NUMERIC),
						new SqlParameter(AppConstants.ID_TRANSACCION_TPP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_ERROR_SP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_MSG_ERROR_SP, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("P_CODESTADO", codigo);
		mapSqlParameterSource.addValue(AppConstants.ID_TRANSACCION_TPP, idRegistroRecargaTpp);

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);

		errorCode = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);

		if (errorCode != null) {
			respuesta.setCodigoRespuesta(errorCode.toString());
		} else {
			respuesta.setCodigoRespuesta(AppConstants.COD_OK_SP);
		}

		respuesta.setGlosaRespuesta((String) out.get(AppConstants.PARAM_MSG_ERROR_SP));
		LOG.info("[updateEstado] Fin");
		return respuesta;
	}

	
	@Override
	public UpdateTPPResponse flagEstado(String servicio) throws NamingException, PropertiesException, SQLException {
		UpdateTPPResponse respuesta = new UpdateTPPResponse();
		BigDecimal flag;

		LOG.info("[flagEstado] Inicio");
		LOG.info("[flagEstado] Servicio:" + servicio);
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE))
				.withProcedureName("PRC_SELECT_FLAG_PRODUCTO").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("P_DATO", Types.VARCHAR),
						new SqlOutParameter("P_FLAG", Types.NUMERIC), new SqlOutParameter(AppConstants.ERROR_COD, Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_MSG, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("P_DATO", servicio);

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);

		flag = (BigDecimal) out.get("P_FLAG");

		if (flag != null)
			respuesta.setEstado(flag.toString());

		if (out.get(AppConstants.PARAM_ERROR_SP) == null) {
			respuesta.setCodigoRespuesta(AppConstants.COD_OK_SP);
		} else {
			respuesta.setCodigoRespuesta(out.get(AppConstants.PARAM_ERROR_SP).toString());
		}

		respuesta.setGlosaRespuesta((String) out.get(AppConstants.PARAM_MSG_ERROR_SP));

		LOG.info("[flagEstado] Respuesta: "+respuesta.toString());

		return respuesta;
	}

	@Override
	public ReintentoTPPResponse selectReintento() throws NamingException, PropertiesException, SQLException {
		ReintentoTPPResponse respuesta = new ReintentoTPPResponse();
		BigDecimal errorCode;
		BigDecimal cantidad;
		LOG.info("[selectReintento] Inicio");
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE))
				.withProcedureName("PRC_SELECT_CANT_REINTENTO").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("P_CANT_REINTENTO", Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_ERROR_SP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_MSG_ERROR_SP, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);
		errorCode = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);
		cantidad = (BigDecimal) out.get("P_CANT_REINTENTO");

		if (errorCode != null) {
			respuesta.setCodigo(Integer.parseInt(errorCode.toString()));
		} else {
			respuesta.setCodigo(AppConstants.COD_OK_INT_SP);
		}

		respuesta.setDato(cantidad.toString());
		respuesta.setGlosa((String) out.get(AppConstants.PARAM_MSG_ERROR_SP));

		LOG.info("[selectReintento] Respuesta:" + respuesta.toString());
		return respuesta;
	}

	@Override
	public ReintentoTPPResponse updateReintento(int nroReintento, int id)
			throws NamingException, PropertiesException, SQLException {
		ReintentoTPPResponse respuesta = new ReintentoTPPResponse();
		BigDecimal errorCode;
		LOG.info("[updateReintento] Inicio");
		LOG.info("[updateReintento] nroReintento: "+nroReintento);
		LOG.info("[updateReintento] id: "+id);
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE))
				.withProcedureName("PRC_UPDATE_REINTENTO").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter(AppConstants.NRO_REINTENTO, Types.NUMERIC),
						new SqlParameter(AppConstants.ID_RECARGA, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_ERROR_SP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_MSG_ERROR_SP, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue(AppConstants.NRO_REINTENTO, nroReintento);
		mapSqlParameterSource.addValue(AppConstants.ID_RECARGA, id);

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);
		errorCode = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);

		if (errorCode != null) {
			respuesta.setCodigo(Integer.parseInt(errorCode.toString()));
		} else {
			respuesta.setCodigo(AppConstants.COD_OK_INT_SP);
		}
		respuesta.setGlosa((String) out.get(AppConstants.PARAM_MSG_ERROR_SP));
		LOG.info("[updateReintento] Respuesta: "+respuesta.toString());
		return respuesta;
	}

	@Override
	public ReintentoTPPResponse insertReintento(ReintentoTPPRequest request)
			throws NamingException, PropertiesException, SQLException {
		ReintentoTPPResponse respuesta = new ReintentoTPPResponse();
		BigDecimal errorCode;
		LOG.info("[insertReintento] Inicio");
		LOG.info("[insertReintento] Entrada: "+request.toString());
		Properties properties = AppUtils.cargarPropertiesExterno();
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE))
				.withProcedureName("PRC_INSERT_REINTENTO").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter(AppConstants.ID_RECARGA, Types.NUMERIC),
						new SqlParameter("P_ID_SOL", Types.VARCHAR), 
						new SqlParameter("P_COD_CANAL", Types.NUMERIC),
						new SqlParameter("P_COD_SISTEMA", Types.NUMERIC),
						new SqlParameter("P_COD_PRODUCTO", Types.NUMERIC),
						new SqlParameter("P_RUT_AFILIADO", Types.VARCHAR),
						new SqlParameter("P_NRO_CONTRATO", Types.NUMERIC),
						new SqlParameter("P_MONTO_RECARGA", Types.NUMERIC),
						new SqlParameter("P_FCH_RECARGA_SOL", Types.DATE),
						new SqlParameter("P_USER_SOL", Types.VARCHAR), 
						new SqlParameter("P_EMAIL_SOL", Types.VARCHAR),
						new SqlParameter(AppConstants.COD_RESP, Types.NUMERIC), 
						new SqlParameter("P_ID_AP", Types.NUMERIC),
						new SqlParameter("P_COD_RESP_AP", Types.VARCHAR),
						new SqlParameter("P_GLS_RESP_AP", Types.VARCHAR), 
						new SqlParameter("P_FCH_RESP_AP", Types.DATE),
						new SqlParameter(AppConstants.NRO_REINTENTO, Types.NUMERIC),
						new SqlParameter("P_FCH_REINTENTO", Types.DATE),
						new SqlParameter("P_COD_ESTRECARGA", Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_ERROR_SP, Types.NUMERIC),
						new SqlOutParameter(AppConstants.PARAM_MSG_ERROR_SP, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue(AppConstants.ID_RECARGA, request.getIdRecarga());
		mapSqlParameterSource.addValue("P_ID_SOL", request.getIdSolicitud());
		mapSqlParameterSource.addValue("P_COD_CANAL", request.getCanal());
		mapSqlParameterSource.addValue("P_COD_SISTEMA", request.getSistema());
		mapSqlParameterSource.addValue("P_COD_PRODUCTO", request.getProducto());
		mapSqlParameterSource.addValue("P_RUT_AFILIADO", request.getRut());
		mapSqlParameterSource.addValue("P_NRO_CONTRATO", request.getNroContrato());
		mapSqlParameterSource.addValue("P_MONTO_RECARGA", request.getMonto());
		mapSqlParameterSource.addValue("P_FCH_RECARGA_SOL", request.getFechaSolicitud());
		mapSqlParameterSource.addValue("P_USER_SOL", request.getUsuario());
		mapSqlParameterSource.addValue("P_EMAIL_SOL", request.getMail());
		mapSqlParameterSource.addValue(AppConstants.COD_RESP, request.getCodRespuestaSolicitud());
		mapSqlParameterSource.addValue("P_ID_AP", request.getIdAp());
		mapSqlParameterSource.addValue("P_COD_RESP_AP", request.getCodRespuestaAp());
		mapSqlParameterSource.addValue("P_GLS_RESP_AP", request.getGlsRespuestaAp());
		mapSqlParameterSource.addValue("P_FCH_RESP_AP", request.getFechaRespuestaAp());
		mapSqlParameterSource.addValue(AppConstants.NRO_REINTENTO, request.getNroReintento());
		mapSqlParameterSource.addValue("P_FCH_REINTENTO", request.getFechaReintento());
		mapSqlParameterSource.addValue("P_COD_ESTRECARGA", request.getCodEstrecarga());

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);
		errorCode = (BigDecimal) out.get(AppConstants.PARAM_ERROR_SP);

		if (errorCode.intValue() != 0) {
			respuesta.setCodigo(Integer.parseInt(errorCode.toString()));
		} else {
			respuesta.setCodigo(AppConstants.COD_OK_INT_SP);
		}

		respuesta.setGlosa((String) out.get(AppConstants.PARAM_MSG_ERROR_SP));
		LOG.info("[insertReintento] Respuesta: "+respuesta.toString());
		return respuesta;
	}

	private void showParameters(Properties properties) {
		LOG.info("Nombre del Package: " + properties.getProperty(AppConstants.PACKAGE));
		LOG.info("Nombre del Esquema: " + properties.getProperty(AppConstants.SCHEMA));
	}

	private SimpleJdbcCall loadResources() throws NamingException, PropertiesException {
		DataSource dataSource;
		dataSource = (DataSource) ContextoSingleton.getContext()
				.lookup(AppUtils.cargarPropertiesExterno().getProperty(AppConstants.TAP_DATASOURCE_JNDI));

		SimpleJdbcCall procedureParametersCall = new SimpleJdbcCall(dataSource);
		return procedureParametersCall;
	}
	
	@Override
	public GlosaCodTO getGlosaCodRecarga(String codigo) throws NamingException, PropertiesException, SQLException {
		String glosa = null;
		BigDecimal codTpp = null;
		
		GlosaCodTO glosaCodTO = null;

		LOG.info("[getGlosaRecarga] Inicio");
		LOG.info("[getGlosaRecarga] Servicio:" + codigo);
		Properties properties = AppUtils.cargarPropertiesExterno();
		LOG.info("[getGlosaRecarga] Servicio:" 
		+ properties.getProperty(AppConstants.SCHEMA)+"."+properties.getProperty(AppConstants.PACKAGE)+".PRC_SELECT_GLOSA_RECARGA");
		LOG.info("[getGlosaRecarga] P_CODE:"+codigo); 
		
		this.showParameters(properties);
		SimpleJdbcCall procedureParametersCall = loadResources();
		procedureParametersCall.withSchemaName(properties.getProperty(AppConstants.SCHEMA))
				.withCatalogName(properties.getProperty(AppConstants.PACKAGE))
				.withProcedureName("PRC_SELECT_GLOSASOL_BY_COD_UP").withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("P_CODE", Types.VARCHAR),
						new SqlOutParameter("P_GLOSA", Types.VARCHAR),
						new SqlOutParameter("P_GLOSA_COD", Types.NUMERIC), 
						new SqlOutParameter(AppConstants.ERROR_COD, Types.NUMERIC),
						new SqlOutParameter(AppConstants.ERROR_MSG, Types.VARCHAR));

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("P_CODE", codigo);

		Map<String, Object> out = procedureParametersCall.execute(mapSqlParameterSource);

		glosa = (String) out.get("P_GLOSA");
		codTpp = (BigDecimal) out.get("P_GLOSA_COD");
		
		if(glosa!=null && codTpp!= null) {
			glosaCodTO = new GlosaCodTO();
			glosaCodTO.setGlosa(glosa);
			glosaCodTO.setCodigoTpp(codTpp.intValue());
		}
		
		
		

		return glosaCodTO;
	}
}
