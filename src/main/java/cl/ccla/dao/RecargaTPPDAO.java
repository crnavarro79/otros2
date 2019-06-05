package cl.ccla.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import cl.ccla.exception.PropertiesException;
import cl.ccla.to.GlosaCodTO;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;
import cl.ccla.to.ReintentoTPPRequest;
import cl.ccla.to.ReintentoTPPResponse;
import cl.ccla.to.UpdateTPPRequest;
import cl.ccla.to.UpdateTPPResponse;

public interface RecargaTPPDAO {
	
	RecargaTPPResponse insertRegistro(RecargaTPPRequest entrada) throws NamingException, PropertiesException, SQLException;

	UpdateTPPResponse updateRegistro(UpdateTPPRequest entrada) throws NamingException, PropertiesException, SQLException;

	UpdateTPPResponse updateEstado(int codigo, int idRegistroRecargaTpp) throws NamingException, PropertiesException, SQLException;

	UpdateTPPResponse flagEstado(String servicio) throws NamingException, PropertiesException, SQLException;

	ReintentoTPPResponse selectReintento() throws NamingException, PropertiesException, SQLException;

	ReintentoTPPResponse updateReintento(int nroReintento, int id) throws NamingException, PropertiesException, SQLException;

	ReintentoTPPResponse insertReintento(ReintentoTPPRequest request) throws NamingException, PropertiesException, SQLException;
	
	public GlosaCodTO getGlosaCodRecarga(String codigo) throws NamingException, PropertiesException, SQLException;
}
