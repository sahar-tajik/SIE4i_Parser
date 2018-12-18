package validation;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class MandatoryFields {
	
	List<JTextField> 	mandatoryTextFields = null;
	List<JLabel> 		errorLables = null;
	
	
	public MandatoryFields(List<JTextField> mandatoryTextFields, List<JLabel> errorLables) {
		super();
		this.mandatoryTextFields = mandatoryTextFields;
		this.errorLables = errorLables;
		
		
	}


	public boolean checkingMandatoryFields() {
		boolean fieldIsNotEmpty = true;
		
		for (int i = 0; i< mandatoryTextFields.size(); i++) {
			JTextField jTextField = mandatoryTextFields.get(i);
			if(jTextField.getText().isEmpty()) {
				errorLables.get(i).setText("this field is mandatory!");
				fieldIsNotEmpty = false;
			}
			else {
				errorLables.get(i).setText("");
				fieldIsNotEmpty = true;				
			}
			
		}
		return fieldIsNotEmpty;		
	}
	
	

}
