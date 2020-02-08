package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
*   Import sth.app exceptions
*/
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.OpeningSurveyException;

/**
*   Import sth.core exceptions
*/
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;

/**
 * 4.6.3. Open survey.
 */
public class DoOpenSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoOpenSurvey(SchoolManager receiver) {
		super(Label.OPEN_SURVEY, receiver);

	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws NoSuchDisciplineException , NoSuchProjectException , NoSurveyException, OpeningSurveyException {
		try {
			_receiver.openSurvey(_discipline.value(),_project.value());
		} catch (NoSuchProjectIdException npe) {
			throw new NoSuchProjectException(_discipline.value(),_project.value());
		} catch (NoSuchDisciplineIdException nsde) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSurveyIdException nse) {
			throw new NoSurveyException(_discipline.value(),_project.value());
		} catch (OpeningSurveyIdException fse) {
			throw new OpeningSurveyException(_discipline.value(),_project.value());
		}
	}
}
