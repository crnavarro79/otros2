package cl.ccla.validation.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import cl.ccla.constants.AppConstants;
import cl.ccla.to.RecargaTPPRequest;

public class EmailValidateRule implements RegistrationRule {

	private static final Logger LOG = Logger.getLogger(EmailValidateRule.class);

	private final Pattern pattern = Pattern.compile(AppConstants.EMAIL_PATTERN);

	public void validate(RecargaTPPRequest regData) {
		LOG.info("[EmailValidateRule] Validando Email (" + regData.getEmailDestino() + ")...");

		if (regData.getEmailDestino() != null && !regData.getEmailDestino().equals("")) {
			Matcher matcher = pattern.matcher(regData.getEmailDestino());

			if (!matcher.matches() || regData.getEmailDestino().length() > AppConstants.MAXIMO_LARGO_EMAIL) {

				throw new IllegalArgumentException(
						"El mail no es valido. Debe estar en minuscula y tener el formato nombre_de_usuario@dominio");
			}
		}
	}

}