package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/ 
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.SurveyFinishedException;
import sth.app.exception.NoSurveyException;

/**
*   Import sth.core exceptions
*/
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {
	
	/**
	 * @param receiver
	 */
	public DoCancelSurvey(SchoolManager receiver) {
		super(Label.CANCEL_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchDisciplineException , NoSuchProjectException , NonEmptySurveyException , SurveyFinishedException, NoSurveyException {
		try {
			_receiver.cancelSurvey(_discipline.value(),_project.value());
		} catch (NonEmptySurveyIdException nepe) {
			throw new NonEmptySurveyException(_discipline.value(),_project.value());
		} catch (NoSurveyIdException nse) {
			throw new NoSurveyException(_discipline.value(),_project.value());
		} catch (NoSuchProjectIdException npe) {
			throw new NoSuchProjectException(_discipline.value(),_project.value());
		} catch (SurveyFinishedIdException sfe) {
			throw new SurveyFinishedException(_discipline.value(),_project.value());
		} catch (NoSuchDisciplineIdException nds) {
			throw new NoSuchDisciplineException(_discipline.value());
		}
	}
}
