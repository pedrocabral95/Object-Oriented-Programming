package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.core exceptions
*/
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

/**
*   Import sth.app exceptions
*/
import sth.app.exception.NoSuchPersonException;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> implements Serializable {


	private Input<String> _fileName;

	 /** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		_fileName = _form.addStringInput(Message.openFile());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.loadFile(_fileName.value());
			_display.display();
		} catch (FileNotFoundException d) {
			_display.popup(Message.fileNotFound());
		} catch (IOException|ClassNotFoundException e){
			e.printStackTrace();
		} catch (NoSuchPersonIdException id) {
			throw new NoSuchPersonException(id.getId());
		}
	}
}


