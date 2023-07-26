package utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Validador extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limites;

	public Validador(int limites) {
		// gerar o contrutor
		super();
		this.limites = limites;
	}

	public void insertString(int ofs, String str, AttributeSet a) throws BadLocationException {
		if ((getLength() + str.length()) <= limites) {
			super.insertString(ofs, str, a); // atraves do contrutor("super") vai extenter o recurso nas caixas de texto

		}
	}

}
