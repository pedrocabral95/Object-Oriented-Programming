package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.app.exception.NoSuchDisciplineException;

import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

	private Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws NoSuchDisciplineException {
		try { 
			_form.parse();
			for (String s : _receiver.getStudentsOfDiscipline(_discipline.value()))
				_display.addLine(s);
			_display.display();
		} catch (NoSuchDisciplineIdException asd) {
			throw new NoSuchDisciplineException(_discipline.value());
		}
	}
}
