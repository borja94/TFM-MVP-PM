package tfm.mvp.pm.presenters;

import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.pm.models.Subject;
import tfm.mvp.pm.models.SubjectDto;
import tfm.mvp.pm.models.Teacher;
import tfm.mvp.pm.models.TeacherDto;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private static final char ID_SUBJECT_SEPARATOR='#';

	public TeacherFormPresenter() {
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void insertNewTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Teacher teacherAux = new Teacher(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacherAux.getSubjectCollection().add(new Subject(aux));
		}
		teacherDto.insert(teacherAux);

	}

	public void updateTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Teacher teacherAux = new Teacher(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux =  assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacherAux.getSubjectCollection().add(new Subject(aux));
		}

		teacherDto.update(teacherAux);

	}

	public void loadTeacher(int id) {
		teacher = teacherDto.get(id);
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
		subjectsCollection = subjectDto.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

}
