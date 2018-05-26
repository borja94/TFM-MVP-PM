package tfm.mvp.pm.presenters;

import java.util.List;

import tfm.mvp.pm.models.Subject;
import tfm.mvp.pm.models.Teacher;
import tfm.mvp.pm.models.TeacherDto;

public class TeachersCollectionPresenter {

	private TeacherDto teacherDto;
	private List<Teacher> teacherCollection;
	private final String columnNames[] = { "ID", "Nombre", "Apellidos", "Asignaturas" };

	public TeachersCollectionPresenter() {
		teacherDto = new TeacherDto();
	}

	public void loadTableData() {
		teacherCollection = teacherDto.GetAll();
	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
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
			String result = "";
			for (Subject subject : teacherCollection.get(row).getSubjectCollection()) {
				if (result == "")
					result = subject.getTitle();
				else
					result += "," + subject.getTitle();
			}
			return result;
		default:
			return null;
		}
	}

	public void RemoveTeacher(int id) {
		teacherDto.Remove(id);
	}

}
