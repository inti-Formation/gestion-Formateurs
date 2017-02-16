package com.adaming.myapp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@FacesValidator(value="noBlankSpaceValidator")
public class NoBlankSpaceValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		if(value.toString().trim().isEmpty() || value == null){
            FacesMessage msg = 
                new FacesMessage("la valeur du champs est incorrect", 
                        "la valeur du champs doit être des caractères significatifs");
            msg.setSeverity(FacesMessage.SEVERITY_FATAL);
            throw new ValidatorException(msg);

        }
	}

}
