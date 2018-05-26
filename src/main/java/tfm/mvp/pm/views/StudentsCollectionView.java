package tfm.mvp.pm.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pm.presenters.StudentsCollectionPresenter;

public class StudentsCollectionView extends JPanel {

	private TableModel studentsTableModel;

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JButton newStudentButton;
	private JTable studentsTable;
	private JScrollPane jScrollPane1;
	private StudentFormView studentFormView;
	private StudentsCollectionPresenter studentCollectionPresenter;

	public StudentsCollectionView(StudentFormView studentFormView) {
		this.studentFormView = studentFormView;
		studentCollectionPresenter = new StudentsCollectionPresenter();
		initComponents();
	}

	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		studentsTable = new JTable();
		deleteStudentButton = new JButton();
		editStudentButton = new JButton();
		newStudentButton = new JButton();

		updateStudentTableData();

		jScrollPane1.setViewportView(studentsTable);

		deleteStudentButton.setText("Borrar");
		deleteStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteStudentButtonActionPerformed();
			}
		});

		editStudentButton.setText("Modo edici�n");
		editStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editStudentButtonActionPerformed();
			}
		});

		newStudentButton.setText("Nuevo alumno");
		newStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newStudentButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel2Layout = new GroupLayout(this);
		this.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(deleteStudentButton)
										.addGap(18, 18, 18).addComponent(editStudentButton).addGap(18, 18, 18)
										.addComponent(newStudentButton)))
						.addContainerGap(17, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteStudentButton).addComponent(editStudentButton)
								.addComponent(newStudentButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	public void updateStudentTableData() {
		studentCollectionPresenter.loadTableData();
		String[] columns = new String[studentCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[studentCollectionPresenter.getNumRows()][studentCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i]=studentCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < studentCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < studentCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = studentCollectionPresenter.getStudentAtribute(j, i);
			}
		}
		studentsTableModel = new DefaultTableModel(tableData, columns);
		studentsTable.setModel(studentsTableModel);
	}

	private void deleteStudentButtonActionPerformed() {
		int selectedRow = studentsTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				studentCollectionPresenter.removeStudent((Integer.parseInt(studentsTableModel.getValueAt(selectedRow, 0).toString())));
				updateStudentTableData();
			}
		}
	}

	private void editStudentButtonActionPerformed() {

		int selectedRow = studentsTable.getSelectedRow();
		if (selectedRow != -1) {
			studentFormView.editTeacherMode(Integer.parseInt(studentsTableModel.getValueAt(selectedRow, 0).toString()));

		}
	}

	private void newStudentButtonActionPerformed() {
		studentFormView.newTeacherMode();
	}

}
