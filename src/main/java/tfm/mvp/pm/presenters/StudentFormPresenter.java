package tfm.mvp.pm.presenters;

import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.pm.models.Student;
import tfm.mvp.pm.models.StudentDto;
import tfm.mvp.pm.models.Subject;
import tfm.mvp.pm.models.SubjectDto;

public class StudentFormPresenter {

	private StudentDto studentDto;
	private SubjectDto subjectDto;
	private Student student;
	private List<Subject> subjectsCollection;
	private static final char ID_SUBJECT_SEPARATOR = '#';

	public StudentFormPresenter() {
		studentDto = new StudentDto();
		subjectDto = new SubjectDto();
	}

	public void insertNewStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Student studentAux = new Student(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			studentAux.getSubjectCollection().add(new Subject(aux));
		}
		studentDto.insert(studentAux);

	}

	public void updateStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Student studentAux = new Student(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			studentAux.getSubjectCollection().add(new Subject(aux));
		}

		studentDto.update(studentAux);

	}

	public void loadStudent(int id) {
		student = studentDto.get(id);
	}

	public String getStudentName() {
		return student.getName();
	}

	public String getStudentSurName() {
		return student.getSurname();
	}

	public int getStudentId() {
		return student.getId();
	}

	public int getStudentNumSubject() {
		return student.getSubjectCollection().size();
	}

	public String getStudentSubject(int id) {
		return student.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDto.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}
}
