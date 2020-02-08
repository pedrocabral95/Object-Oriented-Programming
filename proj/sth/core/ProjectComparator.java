package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Project;

/**  
*  This class creates a comparator that compares projects by their name's alphabetic order
*/

public class ProjectComparator implements Comparator<Project> ,java.io.Serializable{
	@Override
	public int compare(Project a, Project b){
		if (a.getName().compareTo(b.getName()) <= -1) {
			return -1;
		} if (a.getName().compareTo(b.getName()) >= 1) {
				return 1;
		} else { //equal
				return a.getName().compareTo(b.getName());
		}
	//0 sao iguais
	//-1 quer dizer que tem de vir antes na lista
	//1 quer dizer que tem de vir depois na lista
	}
}