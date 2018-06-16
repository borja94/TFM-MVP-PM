package tfm.mvp.pm.presenters;


import tfm.mvp.pm.models.Subject;
import tfm.mvp.pm.models.SubjectDao;

public class SubjectFormPresenter {

	private SubjectDao subjectDao;
	private Subject subject;

	public SubjectFormPresenter() {
		subjectDao = new SubjectDao();
	}

	public void insertNewStudent(String title, int course) {
		
			Subject subjectAux = new Subject(0, title, course);
			subjectDao.insert(subjectAux);
		
	}

	public void updateStudent(String title, int course, int id) {
		
			Subject subjectAux = new Subject(id, title, course);
			subjectDao.update(subjectAux);

		
	}

	public void loadSubject(int id) {
		
			subject = subjectDao.get(id);
		
	}
	public String getSubjectTitle() {
		return subject.getTitle();
	}
	public String getSubjectCourse() {
		return subject.getCourse().toString();
	}
}
