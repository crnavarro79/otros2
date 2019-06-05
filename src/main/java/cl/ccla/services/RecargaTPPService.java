package cl.ccla.services;

import cl.ccla.to.RecargaTPPRequest;
import cl.ccla.to.RecargaTPPResponse;

public interface RecargaTPPService {
	RecargaTPPResponse recarga(RecargaTPPRequest entrada);
}
