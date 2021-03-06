package tfm.mvp.pm.presenters;

import java.util.List;

import tfm.mvp.pm.models.Student;
import tfm.mvp.pm.models.StudentDao;
import tfm.mvp.pm.models.Subject;

public class StudentsCollectionPresenter {

	private StudentDao studentDao;
	private List<Student> studentCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Nombre", "Apellidos", "Asignaturas" };

	public StudentsCollectionPresenter() {
		studentDao = new StudentDao();
	}

	public void loadTableData() {
		studentCollection = studentDao.getAll();
	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return studentCollection.size();
	}

	public String getStudentAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) studentCollection.get(row).getId()).toString();
		case 1:
			return studentCollection.get(row).getName();
		case 2:
			return studentCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : studentCollection.get(row).getSubjectCollection()) {
				if (result.toString() == "")
					result.append( subject.getTitle());
				else
					result.append("," + subject.getTitle());
			}
			return result.toString();
		default:
			return null;
		}
	}

	public void removeStudent(int id) {
			studentDao.remove(id);
	}

}
