package cl.ccla.service.impl;

import java.util.Properties;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.exception.AutenticationServiceException;
import cl.ccla.exception.PropertiesException;
import cl.ccla.rest.request.LoginRequest;
import cl.ccla.rest.response.UsuarioResponse;
import cl.ccla.service.LoginService;
import cl.ccla.utils.AppUtils;
import cl.imagicair.security.ws.ImagicairSecurityMenuBuilder;
import cl.imagicair.security.ws.MenuObject;

public class LoginServiceImpl implements LoginService {

	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class);

	public UsuarioResponse login(LoginRequest loginRequest) throws AutenticationServiceException, PropertiesException {
		LOG.info("[login] Entrada" + loginRequest.toString());
		UsuarioResponse user = null;

		Properties prop = AppUtils.cargarPropertiesExterno();

		String servicioActivado = prop.getProperty("tap.seguridad.activo");

		user = new UsuarioResponse();

		if (!servicioActivado.equals(AppConstants.MODELO_SEGURIDAD_ACTIVADO)) {
			LOG.info("[login] Modelo de seguridad Desactivado");
			LOG.info("[login] Se procede a setear usuario de Test...");

			String[] usuarioBase = prop.getProperty("tap.usuario.test").split(";");

			user.setNombre(usuarioBase[0]);
			user.setApellido(usuarioBase[1]);
			user.setUserName(usuarioBase[2]);
			user.setRol(usuarioBase[3]);

		} else {
			LOG.info("[login] Modelo de seguridad Activado!");
			LOG.info("[login] WSDL Modelo Seguridad: " + prop.getProperty("tap.login.wsdl"));
			LOG.info("[login] Nombre App: " + AppConstants.NOMBRE_APP);

			ImagicairSecurityMenuBuilder img = new ImagicairSecurityMenuBuilder(AppConstants.NOMBRE_APP,
					prop.getProperty("tap.login.wsdl"));

			MenuObject obj = img.getMenuObject(loginRequest.getUsuario(), loginRequest.getPassword());

			if (obj != null && obj.getUser() != null) {
			
				LOG.info("[login] Se valida que el rol ["+obj.getUser().getRole()+"] tengas permisos de acceso...");
				
				if (!AppUtils.validarRol(obj.getUser().getRole())) {
					LOG.error("[login] Rol del usuario sin permiso para acceder");
					throw new AutenticationServiceException("Autenticacion fallida, Rol del usuario ["+obj.getUser().getRole()+"] no posee permisos");
				}
				
				LOG.info("[login] Se obtienen datos del usuario...");

				if (obj.getMenuList() != null) {
					user.setApellido(obj.getUser().getLastName());
					user.setRol(obj.getUser().getRole());
					user.setNombre(obj.getUser().getName());
					user.setUserName(obj.getUser().getUserName());
				} else {
					LOG.error("[login] Usuario sin permiso para acceder");
					throw new AutenticationServiceException("Autenticacion fallida, Usuario sin permiso de acceso");
				}
				
			} else {
				LOG.error("[login] Credenciales invalidas!!");
				throw new AutenticationServiceException("Autenticacion fallida, credenciales invalidas");
			}
		}

		user.setStatus(AppUtils.getStatusOK());
		
		LOG.info("[login] Salida: " + user.toString());

		return user;
	}

}