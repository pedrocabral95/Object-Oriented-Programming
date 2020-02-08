package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/ 
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;

/**
*   Import sth.core exceptions
*/ 
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.2. Close project.
 */ 
public class DoCloseProject extends sth.app.common.ProjectCommand {
	/**
	 * @param receiver
	 */
	public DoCloseProject(SchoolManager receiver) {
		super(Label.CLOSE_PROJECT, receiver);
		
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchProjectException, NoSuchDisciplineException, DialogException{
		try {
			_receiver.closeProject(_discipline.value(),_project.value()); 
		} catch (NoSuchDisciplineIdException nsd) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSuchProjectIdException nas) {
			throw new NoSuchProjectException(_discipline.value(),_project.value());
		}
	}
}
