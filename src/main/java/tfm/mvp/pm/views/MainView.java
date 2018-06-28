package tfm.mvp.pm.views;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import tfm.mvp.pm.presenters.MainPresenter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainView extends JPanel {

	private JButton teacherFrameButton;
	private JButton studentFrameButton;
	private JButton subjectFrameButton;
	private JLabel menuLabel;
	private MainPresenter mainPresenter;

	public MainView() {
		mainPresenter = new MainPresenter();
		initComponents();
	}

	private void initComponents() {

		menuLabel = new JLabel();
		teacherFrameButton = new JButton();
		studentFrameButton = new JButton();
		subjectFrameButton = new JButton();

		menuLabel.setText("Menú");
		teacherFrameButton.setText("Profesores --> Nº Profesores:"+ mainPresenter.getNumTeachers() );
		studentFrameButton.setText("Alumnos --> Nº Alumnos:"+mainPresenter.getNumStudents());
		subjectFrameButton.setText("Asignaturas --> Nº Asignaturas:"+mainPresenter.getNumSubjects());
		initComponentsPosition();

	}

	public void initComponentsPosition() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(23, 23, 23)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(menuLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(teacherFrameButton, GroupLayout.Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(studentFrameButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(subjectFrameButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(23, 23, 23));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(menuLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(teacherFrameButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(studentFrameButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(subjectFrameButton)
						.addGap(21, 21, 21)));

	}

	public JButton getTeacherFrameButton() {
		return teacherFrameButton;
	}

	public JButton getStudentFrameButton() {
		return studentFrameButton;
	}

	public JButton getSubjectFrameButton() {
		return subjectFrameButton;
	}
}
