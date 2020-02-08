package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Student;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSurveyException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSurveyIdException;
/**
 * 4.5.3. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchDisciplineException, NoSuchProjectException, NoSurveyException{
    try {
      for (String s : _receiver.showSurveyResults(_discipline.value(),_project.value()))
        _display.addLine(s);
      _display.display();
    } catch (NoSuchDisciplineIdException nse){
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSurveyIdException nsp) {
      throw new NoSurveyException(_discipline.value(),_project.value());
    } catch (NoSuchProjectIdException ssad) {
      throw new NoSuchProjectException(_discipline.value(),_project.value());
    }
  }
}
