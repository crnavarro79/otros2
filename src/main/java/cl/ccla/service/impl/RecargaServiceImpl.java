package cl.ccla.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ccla.constants.ProductoEnum;
import cl.ccla.dao.RecargaDAO;
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
import cl.ccla.service.RecargaService;
import cl.ccla.to.ProductoTO;
import cl.ccla.utils.AppUtils;

@Service
public class RecargaServiceImpl implements RecargaService {

	private static final Logger LOG = Logger.getLogger(RecargaServiceImpl.class);
	
	@Autowired
	private RecargaDAO recargaDAO;


	public RecargaResponse obtenerRecargasReporte(FiltroDetalleRequest filtroDetalleRecargaRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, ParseException, DateException {
		LOG.info("[obternerRecargasReporte] Inicio");

		if (filtroDetalleRecargaRequest.getFechaInicio() == null || filtroDetalleRecargaRequest.getFechaFin() == null) {
			throw new IncompleteDataException("Los datos de entrada no pueden ser vacios");
		}
		
		if(!AppUtils.isValidDate(filtroDetalleRecargaRequest.getFechaInicio()) || !AppUtils.isValidDate(filtroDetalleRecargaRequest.getFechaFin())) {
			throw new DateException("El formato de fecha es incorrecto, debe ser YYYY-MM-DD");
		}

		RecargaResponse response = recargaDAO.obtenerRecargasReporte(filtroDetalleRecargaRequest);
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public TotalizadoRecargaResponse obtenerTotalizadoRecargas(FiltroRequest filtroRecargaRequest)
			throws NamingException, PropertiesException, IncompleteDataException, SpException, DateException,ParseException {
		LOG.info("[obtenerTotalizadoRecargas] Inicio");
		if (filtroRecargaRequest.getFechaInicio() == null || filtroRecargaRequest.getFechaFin() == null) {
			throw new IncompleteDataException("Los datos de entrada no pueden ser vacios");
		}
		
		if(!AppUtils.isValidDate(filtroRecargaRequest.getFechaInicio()) || !AppUtils.isValidDate(filtroRecargaRequest.getFechaFin())) {
			throw new DateException("El formato de fecha es incorrecto, debe ser YYYY-MM-DD");
		}
		TotalizadoRecargaResponse response = recargaDAO.obtenerTotalizadoRecargas(filtroRecargaRequest);
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public RangoMesResponse obtenerRangoMes() throws NamingException, PropertiesException, SpException {
		LOG.info("[obtenerRangoMes] Inicio");
		RangoMesResponse response = recargaDAO.obtenerRangoMes();
		response.setStatus(AppUtils.getStatusOK());
		return response;
	}

	public ProductoResponse obtenerSistemasProductos() {
		LOG.info("[obtenerSistemasProductos] Inicio");
		ProductoResponse productoResponse = new ProductoResponse();
		List<ProductoTO> dataOutput = new ArrayList<ProductoTO>();

		for (ProductoEnum values : ProductoEnum.values()) {
			ProductoTO productosResponse = new ProductoTO();
			productosResponse.setId(values.getCodigo());
			productosResponse.setNombre(values.getDescripcion());
			dataOutput.add(productosResponse);
		}
		productoResponse.setProductoTOs(dataOutput);
		
		LOG.info("[obtenerSistemasProductos] Total registros obtenidos: " + dataOutput.size());
		LOG.info("[obtenerSistemasProductos] Fin");
		productoResponse.setStatus(AppUtils.getStatusOK());
		return productoResponse;
	}

}
