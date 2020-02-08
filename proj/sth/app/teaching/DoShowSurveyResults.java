package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/ 
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSurveyException;

/**
*   Import sth.core exceptions
*/ 
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.4.5. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoShowSurveyResults(SchoolManager receiver) {
		super(Label.SHOW_SURVEY_RESULTS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchDisciplineException, NoSuchProjectException, NoSurveyException {
	 try {
			for (String s : _receiver.showSurveyResults(_discipline.value(),_project.value()))
				_display.addLine(s);
			_display.display();
		} catch (NoSuchProjectIdException nsp) {
			throw new NoSuchProjectException(_discipline.value(), _project.value());
		} catch (NoSuchDisciplineIdException nsd) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch(NoSurveyIdException sda) {
			throw new NoSurveyException(_discipline.value(),_project.value());
		}
	}
}
