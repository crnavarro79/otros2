package cl.ccla.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextoSingleton {

	private static Context initContext;
	
	private ContextoSingleton() {
		
	}

	public static Context getContext() throws NamingException {

		if (initContext == null) {

			Context initContext = new InitialContext();
			return initContext;
		}else {
			return initContext;
		}
		
	}
}
