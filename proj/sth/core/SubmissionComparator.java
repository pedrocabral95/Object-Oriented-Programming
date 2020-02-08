package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Submission;

/**  
*  This class creates a comparator that compares submissions by their student's id
*/

public class SubmissionComparator implements Comparator<Submission> , java.io.Serializable{
	@Override
	public int compare(Submission a, Submission b){
		return a.getStudentId() - b.getStudentId();
	//0 sao iguais
	//-1 quer dizer que tem de vir antes na lista
	//1 quer dizer que tem de vir depois na lista
	}
}