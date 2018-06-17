package tfm.mvp.pm.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pm.presenters.TeacherFormPresenter;

public class TeacherFormView extends JPanel implements ITeacherFormView{

	private TeacherFormPresenter teacherFormPresenter;

	private DefaultListModel<String> unassignedSubjectModel;
	private DefaultListModel<String> assignedSubjectModel;
	private static final String NEW_TEACHER_LABEL_TEXT = "Nuevo profesor";
	private static final String EDIT_TEACHER_LABEL_TEXT = "Editar profesor";
	private int teacherSelectedId;
	private TeacherCollectionView teacherCollectionView;
	private boolean editMode;

	private JLabel teacherFormLabel;
	private JButton addSubjectButton;
	private JButton removeSubjectButton;
	private JList<String> assignSubjectCollection;
	private JList<String> unassignSubjectCollection;
	private JTextField nameInput;
	private JTextField surnameInput;
	private JButton saveFormButton;
	private JLabel nameInputLabel;
	private JLabel surnameInputLabel;
	private JLabel unassignSubjectsInputLabel;
	private JLabel assignSubjectInputLabel;
	private JScrollPane unassignSubjectsScrollPane;
	private JScrollPane assignSubjectPane;

	public TeacherFormView() {
		teacherFormPresenter = new TeacherFormPresenter();
		initComponents();
		updateSubjectList(null);

	}

	private void initComponents() {

		nameInput = new JTextField();
		nameInputLabel = new JLabel();
		surnameInput = new JTextField();
		surnameInputLabel = new JLabel();
		unassignSubjectCollection = new JList<>();
		assignSubjectCollection = new JList<>();
		addSubjectButton = new JButton();
		removeSubjectButton = new JButton();
		unassignSubjectsInputLabel = new JLabel();
		assignSubjectInputLabel = new JLabel();
		saveFormButton = new JButton();
		unassignSubjectsScrollPane = new JScrollPane();
		assignSubjectPane = new JScrollPane();
		teacherFormLabel = new JLabel();

		nameInputLabel.setText("Nombre");

		surnameInputLabel.setText("Apellidos");
		teacherFormLabel.setText(NEW_TEACHER_LABEL_TEXT);

		addSubjectButton.setText("-->");
		addSubjectButton.addActionListener(e->addSubjectButtonActionPerformed());

		removeSubjectButton.setText("<--");
		removeSubjectButton.addActionListener(e->removeSubjectButtonActionPerformed());

		unassignSubjectsInputLabel.setText("Asignaturas");

		assignSubjectInputLabel.setText("Asignaturas seleccionadas");

		saveFormButton.setText("Guardar");
		saveFormButton.addActionListener(e->saveFormButtonActionPerformed());

		unassignSubjectsScrollPane.setViewportView(unassignSubjectCollection);

		assignSubjectPane.setViewportView(assignSubjectCollection);

		initComponentsPosition();
	}

	public void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(teacherFormLabel)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(nameInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(surnameInputLabel).addComponent(surnameInput,
														GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(unassignSubjectsInputLabel))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(addSubjectButton).addComponent(removeSubjectButton))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(assignSubjectInputLabel)))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117)
								.addComponent(saveFormButton)))
						.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(teacherFormLabel)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nameInputLabel).addComponent(surnameInputLabel))
						.addGap(4, 4, 4)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(surnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(56, 56, 56)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(unassignSubjectsInputLabel).addComponent(assignSubjectInputLabel))
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGap(95, 95, 95)
										.addComponent(addSubjectButton).addGap(33, 33, 33)
										.addComponent(removeSubjectButton)))
						.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));
	}

	public void newTeacherMode() {
		teacherFormLabel.setText(NEW_TEACHER_LABEL_TEXT);
		nameInput.setText("");
		surnameInput.setText("");
		updateSubjectList(null);
		teacherSelectedId = 0;
		editMode = false;
	}

	public void editTeacherMode(int id) {
		teacherFormLabel.setText(EDIT_TEACHER_LABEL_TEXT);
		teacherSelectedId = id;
		editMode = true;
		teacherFormPresenter.loadTeacher(id);
		nameInput.setText(teacherFormPresenter.getTeacherName());
		surnameInput.setText(teacherFormPresenter.getTeacherSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < teacherFormPresenter.getTeacherNumSubject(); i++) {
			subject.add(teacherFormPresenter.getTeacherSubject(i));
		}
		updateSubjectList(subject);
	}

	private void updateSubjectList(List<String> teacherSubjectCollection) {

		unassignedSubjectModel = new DefaultListModel<>();
		assignedSubjectModel = new DefaultListModel<>();

		for (int i = 0; i < teacherFormPresenter.loadSubjects(); i++) {
			String subject = teacherFormPresenter.getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject)) {
				assignedSubjectModel.addElement(subject);
			} else {
				unassignedSubjectModel.addElement(subject);
			}
		}
		unassignSubjectCollection.setModel(unassignedSubjectModel);
		assignSubjectCollection.setModel(assignedSubjectModel);

	}

	private void addSubjectButtonActionPerformed() {
		int[] selectedIndex = unassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = unassignedSubjectModel.getElementAt(index);
			assignedSubjectModel.addElement(item);
			unassignedSubjectModel.remove(index);
		}
	}

	private void removeSubjectButtonActionPerformed() {
		int[] selectedIndex = assignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = assignedSubjectModel.getElementAt(index);
			unassignedSubjectModel.addElement(item);
			assignedSubjectModel.remove(index);
		}
	}

	private void saveFormButtonActionPerformed() {

		String name = nameInput.getText();
		String surname = surnameInput.getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (editMode)
				teacherFormPresenter.updateTeacher(name, surname, assignedSubjectModel, teacherSelectedId);
			else
				teacherFormPresenter.insertNewTeacher(name, surname, assignedSubjectModel);
			teacherCollectionView.updateTeacherTableData();
		}
	}

	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView) {
		this.teacherCollectionView = teacherCollectionView;
	}

}
