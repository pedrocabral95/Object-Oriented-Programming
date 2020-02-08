package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.ClosingSurveyException;
import sth.app.exception.NoSurveyException;

/**
*   Import sth.core exceptions
*/
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
	/**
	 * @param receiver
	 */
	public DoCloseSurvey(SchoolManager receiver) {
		super(Label.CLOSE_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchDisciplineException , NoSuchProjectException, NoSurveyException, ClosingSurveyException {
		try {
			_receiver.closeSurvey(_discipline.value(),_project.value());
		} catch (NoSuchProjectIdException npe) {
			throw new NoSuchProjectException(_discipline.value(),_project.value());
		} catch (NoSuchDisciplineIdException nsde) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSurveyIdException nse) {
			throw new NoSurveyException(_discipline.value(),_project.value());
		} catch (ClosingSurveyIdException nepe) {
			throw new ClosingSurveyException(_discipline.value(),_project.value());
		}
	}

}
