package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchCourseIdException;

import sth.app.exception.NoSuchPersonException;
import sth.app.exception.NoSuchCourseException;
import java.io.Serializable;
/**
 * 4.2.1. Show person.
 */
public class DoMakeRepresentative extends Command<SchoolManager> implements Serializable {

	/** Login identifier. */
	private Input<String> _course;
	private Input<Integer> _login;

	/**
	 * @param receiver
	 */
	public DoMakeRepresentative(SchoolManager receiver) {
		super(Label.DO_MAKE_REPRESENTATIVE, receiver);
		_course = _form.addStringInput(Message.requestCourseName());
		_login = _form.addIntegerInput(Message.requestLoginId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try {
			_form.parse();
			_receiver.makeRepresentative(_course.value(),_login.value());
		} catch (NoSuchPersonIdException e) {
			throw new NoSuchPersonException(_login.value());
		} catch (NoSuchCourseIdException en) {
			//throw new NoSuchCourseException(_course.value(),_login.value());
		}
	}
}
