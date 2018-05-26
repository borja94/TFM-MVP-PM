package tfm.mvp.pm.presenters;

import java.util.List;

import tfm.mvp.pm.models.Subject;
import tfm.mvp.pm.models.Teacher;
import tfm.mvp.pm.models.TeacherDto;

public class TeachersCollectionPresenter {

	private TeacherDto teacherDto;
	private List<Teacher> teacherCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Nombre", "Apellidos", "Asignaturas" };

	public TeachersCollectionPresenter() {
		teacherDto = new TeacherDto();
	}

	public void loadTableData() {
		teacherCollection = teacherDto.getAll();
	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return teacherCollection.size();
	}

	public String getTeacherAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) teacherCollection.get(row).getId()).toString();
		case 1:
			return teacherCollection.get(row).getName();
		case 2:
			return teacherCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : teacherCollection.get(row).getSubjectCollection()) {
				if (result.toString() == "")
					result.append(subject.getTitle());
				else
					result.append("," + subject.getTitle());
			}
			return result.toString();
		default:
			return null;
		}
	}

	public void removeTeacher(int id) {
		teacherDto.remove(id);
	}

}
