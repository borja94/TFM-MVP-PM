package tfm.mpv.pm.Presenters;

import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mpv.pm.Models.Subject;
import tfm.mpv.pm.Models.SubjectDto;
import tfm.mpv.pm.Models.Teacher;
import tfm.mpv.pm.Models.TeacherDto;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;

	public TeacherFormPresenter() {
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void InsertNewTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Teacher teacher = new Teacher(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			teacher.getSubjectCollection().add(new Subject(aux));
		}
		teacherDto.Insert(teacher);

	}

	public void UpdateTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Teacher teacher = new Teacher(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			teacher.getSubjectCollection().add(new Subject(aux));
		}

		teacherDto.Update(teacher);

	}

	public void loadTeacher(int id) {
		teacher = teacherDto.Get(id);
	}

	public String getTeacherName() {
		return teacher.getName();
	}

	public String getTeacherSurName() {
		return teacher.getSurname();
	}

	public int getTeacherId() {
		return teacher.getId();
	}

	public int getTeacherNumSubject() {
		return teacher.getSubjectCollection().size();
	}

	public String getTeacherSubject(int id) {
		return teacher.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDto.GetAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

}
