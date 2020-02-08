package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchPersonIdException;

import sth.app.exception.NoSuchPersonException;
/**
 * 4.2.1. Show person.
 */
public class DoLogin extends Command<SchoolManager> {

	/** Login identifier. */
	private Input<Integer> _login;

	/**
	 * @param receiver
	 */
	public DoLogin(SchoolManager receiver) {
		super(Label.LOGIN, receiver);
		_login = _form.addIntegerInput(Message.requestLoginId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try {
			_form.parse();
			_receiver.login(_login.value());
			for (String s: _receiver.readMessage())
				_display.addLine(s);
			_display.display();
			_receiver.clearMessage();
		} catch (NoSuchPersonIdException e) {
			throw new NoSuchPersonException(_login.value());
		}
	}
}
