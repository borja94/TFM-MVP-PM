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

import tfm.mvp.pm.presenters.SubjectsCollectionPresenter;

public class SubjectsCollectionView extends JPanel {

	private TableModel subjectsTableModel;

	private JButton deleteButton;
	private JButton editButton;
	private JButton newSubjectButton;
	private JTable subjectTable;
	private JScrollPane tableScrollPane;

	private ISubjectFormView iSubjectFormView;
	private SubjectsCollectionPresenter subjectsCollectionPresenter;

	public SubjectsCollectionView() {

		subjectsCollectionPresenter = new SubjectsCollectionPresenter();
		initComponents();
		updateSubjectsTableData();

	}

	public void setSubjectFormView(ISubjectFormView subjectFormView) {
		iSubjectFormView = subjectFormView;
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		subjectTable = new JTable();
		deleteButton = new JButton();
		editButton = new JButton();
		newSubjectButton = new JButton();

		tableScrollPane.setViewportView(subjectTable);

		deleteButton.setText("Borrar");
		deleteButton.addActionListener(e->deleteButtonActionPerformed());

		editButton.setText("Modo edición");
		editButton.addActionListener(e->editButtonActionPerformed());

		newSubjectButton.setText("Nueva asignatura");
		newSubjectButton.addActionListener(e->newSubjectButtonActionPerformed());

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(editButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newSubjectButton)))
				.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteButton).addComponent(editButton).addComponent(newSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void updateSubjectsTableData() {
		subjectsCollectionPresenter.loadTableData();
		String[] columns = new String[subjectsCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[subjectsCollectionPresenter.getNumRows()][subjectsCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = subjectsCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < subjectsCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < subjectsCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = subjectsCollectionPresenter.getSubjectAtribute(j, i);
			}
		}
		subjectsTableModel = new DefaultTableModel(tableData, columns);
		subjectTable.setModel(subjectsTableModel);
	}

	private void deleteButtonActionPerformed() {
		int selectedRow = subjectTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				subjectsCollectionPresenter
						.removeSubject(Integer.parseInt(subjectsTableModel.getValueAt(selectedRow, 0).toString()));
				updateSubjectsTableData();
			}
		}
	}

	private void editButtonActionPerformed() {

		int selectedRow = subjectTable.getSelectedRow();
		if (selectedRow != -1)
			iSubjectFormView
					.editSubjectMode(Integer.parseInt(subjectsTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void newSubjectButtonActionPerformed() {
		iSubjectFormView.newSubjectMode();
	}

}
