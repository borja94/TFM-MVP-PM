package tfm.mvp.pm.Views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pm.Presenters.StudentsCollectionPresenter;

public class StudentsCollectionView extends JPanel {

	private TableModel StudentsTableModel;

	private JButton DeleteStudentButton;
	private JButton EditStudentButton;
	private JButton NewStudentButton;
	private JTable StudentsTable;
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
		StudentsTable = new JTable();
		DeleteStudentButton = new JButton();
		EditStudentButton = new JButton();
		NewStudentButton = new JButton();

		UpdateStudentTableData();

		jScrollPane1.setViewportView(StudentsTable);

		DeleteStudentButton.setText("Borrar");
		DeleteStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteStudentButtonActionPerformed(evt);
			}
		});

		EditStudentButton.setText("Modo edici�n");
		EditStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditStudentButtonActionPerformed(evt);
			}
		});

		NewStudentButton.setText("Nuevo alumno");
		NewStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewStudentButtonActionPerformed(evt);
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
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(DeleteStudentButton)
										.addGap(18, 18, 18).addComponent(EditStudentButton).addGap(18, 18, 18)
										.addComponent(NewStudentButton)))
						.addContainerGap(17, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DeleteStudentButton).addComponent(EditStudentButton)
								.addComponent(NewStudentButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	public void UpdateStudentTableData() {
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
		StudentsTableModel = new DefaultTableModel(tableData, columns);
		StudentsTable.setModel(StudentsTableModel);
	}

	private void DeleteStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = StudentsTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				studentCollectionPresenter.RemoveStudent((Integer.parseInt(StudentsTableModel.getValueAt(selectedRow, 0).toString())));
				UpdateStudentTableData();
			}
		}
	}

	private void EditStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int selectedRow = StudentsTable.getSelectedRow();
		if (selectedRow != -1) {
			studentFormView.EditTeacherMode(Integer.parseInt(StudentsTableModel.getValueAt(selectedRow, 0).toString()));

		}
	}

	private void NewStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		studentFormView.NewTeacherMode();
	}

}
