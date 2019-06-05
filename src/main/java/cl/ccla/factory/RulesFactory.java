package cl.ccla.factory;

import java.util.ArrayList;
import java.util.List;

import cl.ccla.validation.business.ActivacionFirmaBRule;
import cl.ccla.validation.business.BusinessRule;
import cl.ccla.validation.business.CanalBRule;
import cl.ccla.validation.business.RelacionCanalSistemaBRule;
import cl.ccla.validation.business.ServicioActivadoBRule;
import cl.ccla.validation.business.SistemaBRule;
import cl.ccla.validation.data.EmailValidateRule;
import cl.ccla.validation.data.FechaValidateRule;
import cl.ccla.validation.data.IDTransaccionValidateRule;
import cl.ccla.validation.data.MontoValidateRule;
import cl.ccla.validation.data.NroContratoValidateRule;
import cl.ccla.validation.data.NroDocValidateRule;
import cl.ccla.validation.data.RegistrationRule;
import cl.ccla.validation.data.TipoValidateRule;
import cl.ccla.validation.data.UsuarioValidateRule;

public class RulesFactory {

	public List<BusinessRule> getBusinessRules() {
		List<BusinessRule> rules = new ArrayList<BusinessRule>();
		rules.add(new ActivacionFirmaBRule());
		rules.add(new CanalBRule());
		rules.add(new SistemaBRule());
		rules.add(new RelacionCanalSistemaBRule());
		rules.add(new ServicioActivadoBRule());
		return rules;
	}

	public List<RegistrationRule> getRegistrationRules() {
		List<RegistrationRule> rules = new ArrayList<RegistrationRule>();
		rules.add(new EmailValidateRule());
		rules.add(new FechaValidateRule());
		rules.add(new IDTransaccionValidateRule());
		rules.add(new MontoValidateRule());
		rules.add(new NroContratoValidateRule());
		rules.add(new NroDocValidateRule());
		rules.add(new TipoValidateRule());
		rules.add(new UsuarioValidateRule());

		return rules;
	}

	

}
