package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.app.exception.NoSuchPersonException;
import java.io.IOException;
import java.util.List;

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {
	private Input<Integer> _number;
	/**
	 * @param receiver
	 */
	public DoChangePhoneNumber(SchoolManager receiver) {
		super(Label.CHANGE_PHONE_NUMBER, receiver);
		_number = _form.addIntegerInput(Message.requestPhoneNumber());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
    _form.parse();
   	_receiver.changeNumber(_number.value());
    int k;
    for (String s : _receiver.showPerson())
      _display.addLine(s);
    _display.display();
  }
}
