package cl.ccla.validation.data;

import cl.ccla.to.RecargaTPPRequest;

public interface RegistrationRule {

	void validate(RecargaTPPRequest entrada);
}