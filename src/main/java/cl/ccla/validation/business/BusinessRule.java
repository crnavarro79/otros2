package cl.ccla.validation.business;

import cl.ccla.exception.CustomException;
import cl.ccla.to.RecargaTPPRequest;

public interface BusinessRule {

	void validate(RecargaTPPRequest entrada) throws CustomException;
}