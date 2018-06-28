package tfm.mvp.pm.presenters;

import tfm.mvp.pm.models.StudentDao;
import tfm.mvp.pm.models.SubjectDao;
import tfm.mvp.pm.models.TeacherDao;

public class MainPresenter {

	private StudentDao studentDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;
	
	public MainPresenter() {
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();
		teacherDao = new TeacherDao();				
	}
	
	public int getNumStudents() {
		return studentDao.getAll().size();
	}
	public int getNumTeachers() {
		return teacherDao.getAll().size();
	}
	
	public int getNumSubjects() {
		return subjectDao.getAll().size();
	}
}
