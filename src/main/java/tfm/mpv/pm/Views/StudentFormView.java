package tfm.mpv.pm.Views;

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

import tfm.mpv.pm.Presenters.StudentFormPresenter;

public class StudentFormView extends JPanel {

	private JButton AddSubjectButton;
	private JList<String> AssignSubjectCollection;
	private JTextField NameInput;
	private JButton RemoveSubjectButton;
	private JButton SaveFormButton;
	private JLabel StudentFormLabel;
	private JTextField SurnameInput;
	private JList<String> UnassignSubjectCollection;
	private JLabel NameInputLabel;
	private JLabel SurnameInputLabel;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private StudentsCollectionView studentCollectionView;

	private DefaultListModel<String> UnassignedSubjectModel;
	private DefaultListModel<String> AssignedSubjectModel;
	private boolean EditMode;
	private int StudentSelectedId;
	private StudentFormPresenter studentFormPresenter;
	private static final String NewStudentLabelText = "Nuevo alumno";
	private static final String EditStudentLabelText = "Editar alumno";

	public StudentFormView() {
		studentFormPresenter = new StudentFormPresenter();
		initComponents();
		UpdateSubjectList(null);
	}

	private void initComponents() {

		StudentFormLabel = new JLabel();
		NameInput = new JTextField();
		SurnameInput = new JTextField();
		NameInputLabel = new JLabel();
		SurnameInputLabel = new JLabel();
		jScrollPane2 = new JScrollPane();
		UnassignSubjectCollection = new JList<>();
		AssignSubjectCollection = new JList<>();
		AddSubjectButton = new JButton();
		RemoveSubjectButton = new JButton();
		SaveFormButton = new JButton();
		jScrollPane3 = new JScrollPane();

		StudentFormLabel.setText("A�adir alumno");

		NameInputLabel.setText("Nombre");

		SurnameInputLabel.setText("Apellidos");

		jScrollPane2.setViewportView(UnassignSubjectCollection);

		jScrollPane3.setViewportView(AssignSubjectCollection);

		AddSubjectButton.setText("-->");
		AddSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddSubjectButtonActionPerformed(evt);
			}
		});

		RemoveSubjectButton.setText("<--");
		RemoveSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RemoveSubjectButtonActionPerformed(evt);
			}
		});

		SaveFormButton.setText("Guardar");
		SaveFormButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveFormButtonActionPerformed(evt);
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel1Layout = new GroupLayout(this);
		this.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(StudentFormLabel)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(NameInput, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(NameInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(SurnameInputLabel).addComponent(SurnameInput,
														GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(AddSubjectButton).addComponent(RemoveSubjectButton))
										.addGap(18, 18, 18).addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(117, 117, 117)
								.addComponent(SaveFormButton)))
						.addContainerGap(114, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(26, 26, 26).addComponent(StudentFormLabel)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(NameInputLabel).addComponent(SurnameInputLabel))
						.addGap(4, 4, 4)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(NameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(SurnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(56, 56, 56)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(95, 95, 95)
										.addComponent(AddSubjectButton).addGap(33, 33, 33)
										.addComponent(RemoveSubjectButton)))
						.addGap(51, 51, 51).addComponent(SaveFormButton).addContainerGap(96, Short.MAX_VALUE)));

	}

	public void NewTeacherMode() {
		StudentFormLabel.setText(NewStudentLabelText);
		NameInput.setText("");
		SurnameInput.setText("");
		UpdateSubjectList(null);
		StudentSelectedId = 0;
		EditMode = false;
	}

	public void EditTeacherMode(int id) {
		StudentFormLabel.setText(EditStudentLabelText);
		StudentSelectedId = id;
		EditMode = true;
		studentFormPresenter.loadStudent(id);
		NameInput.setText(studentFormPresenter.getStudentName());
		SurnameInput.setText(studentFormPresenter.getStudentSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < studentFormPresenter.getStudentNumSubject(); i++) {
			subject.add(studentFormPresenter.getStudentSubject(i));
		}
		UpdateSubjectList(subject);
	}

	public void UpdateSubjectList(List<String> studentSubjectCollection) {
		UnassignedSubjectModel = new DefaultListModel<>();
		AssignedSubjectModel = new DefaultListModel<>();

		for (int i = 0; i < studentFormPresenter.loadSubjects(); i++) {
			String subject = studentFormPresenter.getSubjectByPosition(i);
			if (studentSubjectCollection != null && studentSubjectCollection.contains(subject.toString())) {
				AssignedSubjectModel.addElement(subject);
			} else {
				UnassignedSubjectModel.addElement(subject);
			}
		}
		UnassignSubjectCollection.setModel(UnassignedSubjectModel);
		AssignSubjectCollection.setModel(AssignedSubjectModel);
	}

	private void AddSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int[] selectedIndex = UnassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = UnassignedSubjectModel.getElementAt(index).toString();
			AssignedSubjectModel.addElement(item);
			UnassignedSubjectModel.remove(index);
		}
	}

	private void RemoveSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int[] selectedIndex = AssignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = AssignedSubjectModel.getElementAt(index).toString();
			UnassignedSubjectModel.addElement(item);
			AssignedSubjectModel.remove(index);
		}
	}

	private void SaveFormButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String name = NameInput.getText();
		String surname = SurnameInput.getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (EditMode)
				studentFormPresenter.UpdateStudent(name, surname, AssignedSubjectModel, StudentSelectedId);
			else
				studentFormPresenter.InsertNewStudent(name, surname, AssignedSubjectModel);
			studentCollectionView.UpdateStudentTableData();
		}
	}

	public void setStudentCollectionView(StudentsCollectionView studentCollectionView) {
		this.studentCollectionView = studentCollectionView;
	}

}
