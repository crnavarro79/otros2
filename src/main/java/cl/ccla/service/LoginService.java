package cl.ccla.service;

import cl.ccla.exception.AutenticationServiceException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.rest.request.LoginRequest;
import cl.ccla.rest.response.UsuarioResponse;

public interface LoginService {
	UsuarioResponse login(LoginRequest loginRequest) throws AutenticationServiceException, PropertiesException;
}