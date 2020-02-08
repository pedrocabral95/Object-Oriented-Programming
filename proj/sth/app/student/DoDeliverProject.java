package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSuchDisciplineException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.5.1. Deliver project.
 */
public class DoDeliverProject extends sth.app.common.ProjectCommand {

  private Input<String> _description;

  /**
   * @param receiver
   */
  public DoDeliverProject(SchoolManager receiver) {
    super(Label.DELIVER_PROJECT, receiver);
    _description = _form.addStringInput(Message.requestDeliveryMessage());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchProjectException, NoSuchDisciplineException {
    try {
      _receiver.submitProject(_discipline.value(),_project.value(),_description.value());
    } catch (NoSuchDisciplineIdException de) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSuchProjectIdException de) {
      throw new NoSuchProjectException(_discipline.value(),_project.value());
    }
  }
}