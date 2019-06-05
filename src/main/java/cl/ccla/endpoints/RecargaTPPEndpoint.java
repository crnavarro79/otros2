package cl.ccla.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.sun.xml.ws.developer.SchemaValidation;

import cl.ccla.context.AppContext;
import cl.ccla.services.RecargaTPPService;
import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;

//@SchemaValidation TODO validarlo en una proxima etapa de control de cambio
@WebService(serviceName = "RecargaTPP")
public class RecargaTPPEndpoint {

	final static Logger LOG = Logger.getLogger(RecargaTPPEndpoint.class);

	@WebMethod(operationName = "recargaTapp")
	public RecargaTPPResponse recarga_tpp(@WebParam(name = "entrada") RecargaTPPRequest entrada) {

		RecargaTPPResponse respuesta = null;
		LOG.info("[Operacion Recarga] Inicio");

		RecargaTPPService recargaService = (RecargaTPPService) AppContext.getAppContext().getBean("recargaService");
		respuesta = recargaService.recarga(entrada);
		LOG.info("[Operacion Recarga] Respuesta:\n" + respuesta.toString());

		LOG.info("[Operacion Recarga] Fin");

		return respuesta;
	}
}
