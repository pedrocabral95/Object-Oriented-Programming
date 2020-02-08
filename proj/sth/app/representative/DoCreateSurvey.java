package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.DuplicateSurveyException;

/**
*   Import sth.core exceptions
*/
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateSurveyIdException;

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoCreateSurvey(SchoolManager receiver) {
		super(Label.CREATE_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws NoSuchDisciplineException , NoSuchProjectException, DuplicateSurveyException {
		try {
			_receiver.createSurvey(_discipline.value(),_project.value());
		} catch (DuplicateSurveyIdException npe) {
			throw new DuplicateSurveyException(_discipline.value(),_project.value());
		} catch (NoSuchDisciplineIdException nsde) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch ( NoSuchProjectIdException nse) {
			throw new NoSuchProjectException(_discipline.value(),_project.value());
		}
	}
}
