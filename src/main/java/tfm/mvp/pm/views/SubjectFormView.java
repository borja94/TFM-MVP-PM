package tfm.mvp.pm.views;



import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pm.presenters.SubjectFormPresenter;

public class SubjectFormView extends JPanel implements ISubjectFormView {

	private static final String NEW_SUBJECT_LABEL_TEXT = "Nueva asignatura";
	private static final String EDIT_SUBJECT_LABEL_TEXT = "Editar asignatura";
	private boolean editMode;
	private int subjectSelectedId;

	private JTextField courseInput;
	private JButton saveButton;
	private JLabel subjectFormLabel;
	private JTextField titleInput;
	private JLabel titleInputLabel;
	private JLabel courseInpitLabel;

	private SubjectFormPresenter subjectFormPresenter;
	private SubjectsCollectionView subjectCollectionView;

	public SubjectFormView() {
		subjectFormPresenter = new SubjectFormPresenter();
		initComponents();
	}

	private void initComponents() {

		subjectFormLabel = new JLabel();
		titleInput = new JTextField();
		courseInput = new JTextField();
		titleInputLabel = new JLabel();
		courseInpitLabel = new JLabel();
		saveButton = new JButton();

		subjectFormLabel.setText("NewSubjectLabelText");

		titleInputLabel.setText("Título");

		courseInpitLabel.setText("Curso");

		saveButton.setText("Guardar");
		saveButton.addActionListener(e->saveButtonActionPerformed());

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel1Layout = new GroupLayout(this);
		this.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(subjectFormLabel)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(titleInput, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(titleInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(courseInpitLabel).addComponent(courseInput,
														GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
								.addComponent(saveButton)).addContainerGap(225, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(33, 33, 33).addComponent(subjectFormLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleInputLabel).addComponent(courseInpitLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(courseInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(30, 30, 30).addComponent(saveButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	private void saveButtonActionPerformed() {

		String name = titleInput.getText();
		String course = courseInput.getText();

		if (!name.isEmpty() && !course.isEmpty()) {
			if (editMode) {
				subjectFormPresenter.updateStudent(name, Integer.parseInt(course), subjectSelectedId);
			} else {
				subjectFormPresenter.insertNewStudent(name, Integer.parseInt(course));
			}
			
			 subjectCollectionView.updateSubjectsTableData();
			 
		}
	}
	
	public void newSubjectMode() {
		subjectFormLabel.setText(NEW_SUBJECT_LABEL_TEXT);
		titleInput.setText("");
		courseInput.setText("");
		subjectSelectedId = 0;
		editMode = false;
	}
	public void editSubjectMode(int id) {
		subjectFormLabel.setText(EDIT_SUBJECT_LABEL_TEXT);
		subjectSelectedId = id;
		editMode = true;
		subjectFormPresenter.loadSubject(id);
		titleInput.setText(subjectFormPresenter.getSubjectTitle());
		courseInput.setText(subjectFormPresenter.getSubjectCourse());
	}

	public void setSubjectCollectionView(SubjectsCollectionView subjectCollectionView) {
		this.subjectCollectionView = subjectCollectionView;
	}
	
	
}
