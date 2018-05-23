package tfm.mpv.pm.Views;

import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mpv.pm.Presenters.TeachersCollectionPresenter;

public class TeacherCollectionView extends JPanel {

	private javax.swing.JButton DeleteTeacherButton;
	private javax.swing.JButton EditTeacherButton;
	private javax.swing.JButton NewTeacherButton;
	private javax.swing.JTable TeachersTable;
	private javax.swing.JScrollPane jScrollPane3;

	private TeachersCollectionPresenter teacherCollectionPresenter;
	private TableModel TeachersTableModel;
	private TeacherFormView teacherFormView;

	public TeacherCollectionView(TeacherFormView teacherFormView) {
		teacherCollectionPresenter = new TeachersCollectionPresenter();
		this.teacherFormView = teacherFormView;
		initComponents();
	}

	private void initComponents() {

		jScrollPane3 = new javax.swing.JScrollPane();
		TeachersTable = new javax.swing.JTable();
		DeleteTeacherButton = new javax.swing.JButton();
		EditTeacherButton = new javax.swing.JButton();
		NewTeacherButton = new javax.swing.JButton();

		UpdateTeacherTableData();

		jScrollPane3.setViewportView(TeachersTable);

		DeleteTeacherButton.setText("Borrar");
		DeleteTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteTeacherButtonActionPerformed(evt);
			}
		});

		EditTeacherButton.setText("Modo edici�n");
		EditTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditTeacherButtonActionPerformed(evt);
			}
		});

		NewTeacherButton.setText("Nuevo profesor");
		NewTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewTeacherButtonActionPerformed(evt);
			}
		});

		initComponentPostions();
	}

	private void initComponentPostions() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup().addGap(23, 23, 23).addComponent(jScrollPane3)
				.addGroup(layout.createSequentialGroup().addComponent(DeleteTeacherButton)
						.addComponent(EditTeacherButton).addComponent(NewTeacherButton))
				.addGap(23, 23, 23));

		layout.setVerticalGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(jScrollPane3)
				.addGroup(layout.createParallelGroup().addComponent(DeleteTeacherButton).addComponent(EditTeacherButton)
						.addComponent(NewTeacherButton))
				.addGap(21, 21, 21));
	}

	public void UpdateTeacherTableData() {

		teacherCollectionPresenter.loadTableData();
		String[] columns = new String[teacherCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[teacherCollectionPresenter.getNumRows()][teacherCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = teacherCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < teacherCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < teacherCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = teacherCollectionPresenter.getTeacherAtribute(j, i);
			}
		}
		TeachersTableModel = new DefaultTableModel(tableData, columns);
		TeachersTable.setModel(TeachersTableModel);

	}

	private void DeleteTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = TeachersTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al profesor");
			if (dialogResult == JOptionPane.YES_OPTION) {
				teacherCollectionPresenter.RemoveTeacher(((int) TeachersTableModel.getValueAt(selectedRow, 0)));
				UpdateTeacherTableData();
			}
		}
	}

	private void EditTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = TeachersTable.getSelectedRow();
		if (selectedRow != -1)
			teacherFormView.EditTeacherMode(Integer.parseInt(TeachersTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void NewTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {
		teacherFormView.NewTeacherMode();
	}

}