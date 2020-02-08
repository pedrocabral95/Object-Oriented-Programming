package sth.app.main;

import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.io.FileNotFoundException;
import pt.tecnico.po.ui.DialogException;

import java.io.Serializable;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> implements Serializable {
	
	private Input<String>  _fileName;
	
	 /** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	/**
	 * @param receiver
	 */
	public DoSave(SchoolManager receiver) 
	{
		super(Label.SAVE, receiver);
		_fileName = _form.addStringInput(Message.newSaveAs());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try { 
			if (_receiver.getFile() == null) {
				_form.parse();
				_receiver.setFileName(_fileName.value());
				_receiver.saveFile();
			}
			else {
				_receiver.saveFile();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}    
	}
}
