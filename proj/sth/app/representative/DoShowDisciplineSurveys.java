package sth.app.representative;

import pt.tecnico.po.ui.Command;
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
import sth.core.exception.NoSurveyIdException;

/**
 * 4.6.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

	private Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineSurveys(SchoolManager receiver) {
		super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws NoSuchDisciplineException, NoSuchProjectException {
		try {
			_form.parse();
			for (String s : _receiver.showSurveyResultsRep(_discipline.value()))
				_display.addLine(s);
			_display.display();
		} catch (NoSuchDisciplineIdException nsde) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSurveyIdException nsed) {      
		}
	}
}
