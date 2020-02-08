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
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {
	

	/**
	 * @param receiver
	 */
	public DoShowProjectSubmissions(SchoolManager receiver) {
		super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchDisciplineException, NoSuchProjectException {
		try {
			for (String s: _receiver.getProjectSubmissions(_discipline.value(),_project.value()))
				_display.addLine(s);
			_display.display();
		} catch (NoSuchProjectIdException nsp) {
			throw new NoSuchProjectException(_discipline.value(), _project.value());
		} catch (NoSuchDisciplineIdException nsd) {
			throw new NoSuchDisciplineException(_discipline.value());
		}
	}
}
