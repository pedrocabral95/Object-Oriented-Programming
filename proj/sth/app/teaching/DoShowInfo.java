package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.io.Serializable;
import java.util.List;

/**
 * 4.4.5. Show survey results.
 */

public class DoShowInfo extends Command<SchoolManager> implements Serializable {

	/**
	 * @param receiver
	 */
	public DoShowInfo(SchoolManager receiver) {
		super(Label.DO_SHOW_INFO, receiver);

	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	public final void execute() {
		_form.parse();
		List<String> list = _receiver.showInfo();
		for (String s : list)
      _display.addLine(s);
    _display.display();
	}
}
