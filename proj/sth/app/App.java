package sth.app;

import static pt.tecnico.po.ui.Dialog.IO;
import sth.core.Parser;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Menu;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;

import sth.app.main.MainMenu;
import sth.app.person.DoLogin;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
/**
 * Main driver for the travel management application.
 */
public class App {

	public static void main(String[] args) {
		SchoolManager manager = new SchoolManager();

		String datafile = System.getProperty("import"); //$NON-NLS-1$

		if (datafile != null) {
			try {
				manager.importFile(datafile);
			} catch (ImportFileException a) {
				System.err.println("Error in parsing: " + a.getMessage());
				a.printStackTrace();
			}
		} 
		try {
			DoLogin loginCmd = new DoLogin(manager);
			loginCmd.execute();
			Menu menu = new MainMenu(manager);
			menu.open();
			} catch (NoSuchPersonIdException ide) {
				ide.printStackTrace();
			} catch (DialogException ded ) {
			} finally {
				IO.close();
			}
	}
}
