package tfm.mpv.pm.Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

import tfm.mpv.pm.Presenters.TeacherFormPresenter;

public class TeacherFormView extends JPanel {

	private TeacherFormPresenter teacherFormPresenter;

	private DefaultListModel<String> UnassignedSubjectModel;
	private DefaultListModel<String> AssignedSubjectModel;
	private static final String NewTeacherLabelText = "Nuevo profesor";
	private static final String EditTeacherLabelText = "Editar profesor";
	private int TeacherSelectedId;
	private TeacherCollectionView teacherCollectionView;
	private boolean EditMode;

	private javax.swing.JLabel TeacherFormLabel;
	private javax.swing.JButton AddSubjectButton;
	private javax.swing.JButton RemoveSubjectButton;
	private javax.swing.JList<String> AssignSubjectCollection;
	private javax.swing.JList<String> UnassignSubjectCollection;
	private javax.swing.JTextField NameInput;
	private javax.swing.JTextField SurnameInput;
	private javax.swing.JButton SaveFormButton;
	private javax.swing.JLabel NameInputLabel;
	private javax.swing.JLabel UserNameInputLabel;
	private javax.swing.JLabel UnassignSubjectsInputLabel;
	private javax.swing.JLabel AssignSubjectInputLabel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;

	public TeacherFormView() {
		teacherFormPresenter = new TeacherFormPresenter();
		initComponents();
		UpdateSubjectList(null);

	}

	private void initComponents() {

		NameInput = new javax.swing.JTextField();
		NameInputLabel = new javax.swing.JLabel();
		SurnameInput = new javax.swing.JTextField();
		UserNameInputLabel = new javax.swing.JLabel();
		UnassignSubjectCollection = new javax.swing.JList<>();
		AssignSubjectCollection = new javax.swing.JList<>();
		AddSubjectButton = new javax.swing.JButton();
		RemoveSubjectButton = new javax.swing.JButton();
		UnassignSubjectsInputLabel = new javax.swing.JLabel();
		AssignSubjectInputLabel = new javax.swing.JLabel();
		SaveFormButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		TeacherFormLabel = new javax.swing.JLabel();

		NameInputLabel.setText("Nombre");

		UserNameInputLabel.setText("Apellidos");
		TeacherFormLabel.setText("Nuevo profesor");

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

		UnassignSubjectsInputLabel.setText("Asignaturas");

		AssignSubjectInputLabel.setText("Asignaturas seleccionadas");

		SaveFormButton.setText("Guardar");
		SaveFormButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveFormButtonActionPerformed(evt);
			}
		});

		jScrollPane1.setViewportView(UnassignSubjectCollection);

		jScrollPane2.setViewportView(AssignSubjectCollection);

		initComponentsPosition();
	}

	public void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(23, 23, 23)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(TeacherFormLabel)
						.addComponent(NameInputLabel).addComponent(NameInput).addComponent(UnassignSubjectsInputLabel)
						.addComponent(jScrollPane1))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(100)
						.addComponent(AddSubjectButton).addComponent(RemoveSubjectButton).addComponent(SaveFormButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(UserNameInputLabel)
						.addComponent(SurnameInput).addComponent(AssignSubjectInputLabel).addComponent(jScrollPane2))
				.addGap(23, 23, 23));

		layout.setVerticalGroup(layout.createSequentialGroup().addGap(21, 21, 21).addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(TeacherFormLabel).addComponent(NameInputLabel)
						.addComponent(NameInput).addComponent(UnassignSubjectsInputLabel).addComponent(jScrollPane1))
				.addGroup(layout.createSequentialGroup().addGap(100).addComponent(AddSubjectButton)
						.addComponent(RemoveSubjectButton).addComponent(SaveFormButton))
				.addGroup(layout.createSequentialGroup().addComponent(UserNameInputLabel).addComponent(SurnameInput)
						.addComponent(AssignSubjectInputLabel).addComponent(jScrollPane2)))
				.addGap(21, 21, 21));
	}

	public void NewTeacherMode() {
		TeacherFormLabel.setText(NewTeacherLabelText);
		NameInput.setText("");
		SurnameInput.setText("");
		UpdateSubjectList(null);
		TeacherSelectedId = 0;
		EditMode = false;
	}

	public void EditTeacherMode(int id) {
		TeacherFormLabel.setText(EditTeacherLabelText);
		TeacherSelectedId = id;
		EditMode = true;
		teacherFormPresenter.loadTeacher(id);
		NameInput.setText(teacherFormPresenter.getTeacherName());
		SurnameInput.setText(teacherFormPresenter.getTeacherSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < teacherFormPresenter.getTeacherNumSubject(); i++) {
			subject.add(teacherFormPresenter.getTeacherSubject(i));
		}
		UpdateSubjectList(subject);
	}

	private void UpdateSubjectList(List<String> teacherSubjectCollection) {

		UnassignedSubjectModel = new DefaultListModel<>();
		AssignedSubjectModel = new DefaultListModel<>();

		for (int i = 0; i < teacherFormPresenter.loadSubjects(); i++) {
			String subject = teacherFormPresenter.getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject.toString())) {
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
				teacherFormPresenter.UpdateTeacher(name, surname, AssignedSubjectModel, TeacherSelectedId);
			else
				teacherFormPresenter.InsertNewTeacher(name, surname, AssignedSubjectModel);
			teacherCollectionView.UpdateTeacherTableData();
		}
	}

	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView) {
		this.teacherCollectionView = teacherCollectionView;
	}

}
