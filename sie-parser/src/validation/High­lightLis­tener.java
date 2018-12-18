package validation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class High­lightLis­tener implements  DocumentListener{

    JTextComponent comp = null;
    Border de­fault­Bor­der = null;
    Border high­light­Bor­der = BorderFactory.createLineBorder(Color.ORANGE);

    public High­lightLis­tener(JTextComponent _comp) {
    	this.comp = _comp;
    	de­fault­Bor­der = comp.getBorder();
    	// Adding this lis­tener to a spec­i­fied com­po­nent:
    	comp.getDocument().addDocumentListener(this);
    	// High­light if empty:
    	this.may­be­High­light();
    }

    private void may­be­High­light() {
    	if (comp.getText().trim().length() != 0)
            // if a field is non-empty, switch it to de­fault look
    		comp.setBorder(de­fault­Bor­der);
        else
            // if a field is empty, high­light it
        	comp.setBorder(high­light­Bor­der);
        // ... more ac­tions
    }

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		may­be­High­light();
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		may­be­High­light();
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		may­be­High­light();		
	}
}