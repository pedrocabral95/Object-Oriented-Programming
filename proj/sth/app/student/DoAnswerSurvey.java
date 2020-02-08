package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NoSuchSurveyIdException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.NoSuchSurveyException;


/**
 * 4.5.2. Answer survey.
 */
public class DoAnswerSurvey extends sth.app.common.ProjectCommand {

  private Input<Integer> _hours;
  private Input<String> _description;

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    _hours = _form.addIntegerInput(Message.requestProjectHours());
    _description = _form.addStringInput(Message.requestComment());
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectException, NoSuchDisciplineException, NoSurveyException, NoSuchSurveyException {
    try {
      _receiver.submitSurvey(_discipline.value(),_project.value(),_hours.value(), _description.value());
    } catch (NoSuchProjectIdException npe) {
      throw new NoSuchProjectException(_discipline.value(),_project.value());
    } catch (NoSuchDisciplineIdException nsde) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSurveyIdException nse) {
      throw new NoSurveyException(_discipline.value(),_project.value());
    } catch (NoSuchSurveyIdException nsed) {
      throw new NoSuchSurveyException(_discipline.value(),_project.value());
    }
  }
}
