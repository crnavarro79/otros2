package cl.ccla.services;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;

import cl.ccla.CashinRequest;
import cl.ccla.CashinResponse;
import cl.ccla.exception.CustomException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.to.CashinTPPResponseTO;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;
import cl.ccla.to.ReintentoTPPRequest;

public interface RecargaRoutinesService {

	Integer obtenerReintentos() throws NamingException, PropertiesException, SQLException;

	String crearReintento(ReintentoTPPRequest request, Integer nroReintento, Integer idRegistroRecargaTpp)
			throws NamingException, PropertiesException, SQLException;

	CashinTPPResponseTO llamarServicioRecargaAP(CashinRequest request, Properties prop)
			throws NamingException, PropertiesException, SQLException;

	RecargaTPPResponse inserRegistro(RecargaTPPRequest entrada)
			throws CustomException, NamingException, PropertiesException, SQLException;

	void updateRegistro(RecargaTPPRequest entrada, Integer idRegistroRecargaTpp, Integer estadoAccion)
			throws CustomException, NumberFormatException, NamingException, PropertiesException, SQLException;

	void actualizaEstado(Integer idRegistroRecargaTpp, String code)
			throws CustomException, NamingException, PropertiesException, SQLException;

	void actualizaRegistroFinal(CashinResponse response, String idRegistroRecargaTpp, RecargaTPPRequest entrada,
			Integer codigoRespuestaTpp) throws CustomException, NamingException, PropertiesException, SQLException;

}
