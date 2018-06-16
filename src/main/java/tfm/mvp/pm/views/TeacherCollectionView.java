package tfm.mvp.pm.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pm.presenters.TeachersCollectionPresenter;

public class TeacherCollectionView extends JPanel {

	private javax.swing.JButton deleteTeacherButton;
	private javax.swing.JButton editTeacherButton;
	private javax.swing.JButton newTeacherButton;
	private javax.swing.JTable teachersTable;
	private javax.swing.JScrollPane tableScrollPane;

	private TeachersCollectionPresenter teacherCollectionPresenter;
	private TableModel teachersTableModel;
	private ITeacherFormView iTeacherFormView;

	public TeacherCollectionView() {
		teacherCollectionPresenter = new TeachersCollectionPresenter();
		initComponents();
	}

	public void updateTeacherTableData() {

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
		teachersTableModel = new DefaultTableModel(tableData, columns);
		teachersTable.setModel(teachersTableModel);

	}

	public void setTeacherFormView(ITeacherFormView teacherFormView) {
		iTeacherFormView = teacherFormView;
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		teachersTable = new JTable();
		deleteTeacherButton = new JButton();
		editTeacherButton = new JButton();
		newTeacherButton = new JButton();

		updateTeacherTableData();

		tableScrollPane.setViewportView(teachersTable);

		deleteTeacherButton.setText("Borrar");
		deleteTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteTeacherButtonActionPerformed();
			}
		});

		editTeacherButton.setText("Modo edición");
		editTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editTeacherButtonActionPerformed();
			}
		});

		newTeacherButton.setText("Nuevo profesor");
		newTeacherButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newTeacherButtonActionPerformed();
			}
		});

		initComponentPostions();
	}

	private void initComponentPostions() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteTeacherButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(editTeacherButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newTeacherButton)))
				.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteTeacherButton).addComponent(editTeacherButton)
								.addComponent(newTeacherButton))
						.addGap(203, 203, 203)));
	}

	private void deleteTeacherButtonActionPerformed() {
		int selectedRow = teachersTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al profesor");
			if (dialogResult == JOptionPane.YES_OPTION) {
				teacherCollectionPresenter.removeTeacher(((int) teachersTableModel.getValueAt(selectedRow, 0)));
				updateTeacherTableData();
			}
		}
	}

	private void editTeacherButtonActionPerformed() {
		int selectedRow = teachersTable.getSelectedRow();
		if (selectedRow != -1)
			iTeacherFormView
					.editTeacherMode(Integer.parseInt(teachersTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void newTeacherButtonActionPerformed() {
		iTeacherFormView.newTeacherMode();
	}

}
