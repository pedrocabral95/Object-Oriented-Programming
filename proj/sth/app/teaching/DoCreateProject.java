package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/ 
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.DuplicateProjectException;

/**
*   Import sth.core exceptions
*/ 
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateProjectIdException;

/**
 * 4.4.1. Create project.
 */
public class DoCreateProject extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoCreateProject(SchoolManager receiver) {
		super(Label.CREATE_PROJECT, receiver);
	}
	/**
	*  @throws NoSuchProjectException
	*  @throws NoSuchDisciplineException
	*  @throws DuplicateProjectException
	*/
	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void myExecute() throws  NoSuchDisciplineException, DuplicateProjectException {
		try {
			_receiver.createProject(_discipline.value(),_project.value()); 
		} catch (NoSuchDisciplineIdException nsd) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (DuplicateProjectIdException dpe) {
			throw new DuplicateProjectException(_discipline.value(),_project.value());
		}
	}
}
