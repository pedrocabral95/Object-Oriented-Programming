package sth.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import pt.tecnico.po.ui.ValidityPredicate;
import sth.core.SchoolManager;
import sth.app.teaching.TeachingMenu;
import sth.core.exception.NoSuchPersonIdException;
/**
 * 4.1.2. Open teaching menu.
 */
public class DoOpenTeachingMenu extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoOpenTeachingMenu(SchoolManager receiver) throws NoSuchPersonIdException {
    super(Label.OPEN_TEACHING_MENU, receiver, new ValidityPredicate<SchoolManager>(receiver) {
      @Override
      public boolean isValid() {
        try { 
         return _receiver.isLoggedUserProfessor();  
        }
        catch (NoSuchPersonIdException ie) {
          return false;
        } 
      }
    }
    );
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    Menu menu = new TeachingMenu(_receiver);
    menu.open();
  }

}
