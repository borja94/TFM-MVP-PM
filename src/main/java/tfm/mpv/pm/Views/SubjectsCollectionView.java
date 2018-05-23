package tfm.mpv.pm.Views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mpv.pm.Presenters.SubjectsCollectionPresenter;

public class SubjectsCollectionView extends JPanel {

	private TableModel SubjectsTableModel;

	private JButton DeleteButton;
	private JButton EditButton;
	private JButton NewSubjectButton;
	private JTable SubjectTable;
	private JScrollPane jScrollPane1;

	private SubjectFormView subjectFormView;
	private SubjectsCollectionPresenter subjectsCollectionPresenter;

	public SubjectsCollectionView(SubjectFormView subjectFormView) {

		subjectsCollectionPresenter = new SubjectsCollectionPresenter();
		this.subjectFormView = subjectFormView;
		initComponents();
		UpdateSubjectsTableData();

	}

	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		SubjectTable = new JTable();
		DeleteButton = new JButton();
		EditButton = new JButton();
		NewSubjectButton = new JButton();

		jScrollPane1.setViewportView(SubjectTable);

		DeleteButton.setText("Borrar");
		DeleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteButtonActionPerformed(evt);
			}
		});

		EditButton.setText("Modo edici�n");
		EditButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditButtonActionPerformed(evt);
			}
		});

		NewSubjectButton.setText("Nueva asignatura");
		NewSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewSubjectButtonActionPerformed(evt);
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel2Layout = new GroupLayout(this);
		this.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel2Layout.createSequentialGroup().addComponent(DeleteButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(EditButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(NewSubjectButton)))
						.addGap(0, 19, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DeleteButton).addComponent(EditButton).addComponent(NewSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void UpdateSubjectsTableData() {
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
		SubjectsTableModel = new DefaultTableModel(tableData, columns);
		SubjectTable.setModel(SubjectsTableModel);
	}

	private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = SubjectTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				subjectsCollectionPresenter
						.RemoveSubject(Integer.parseInt(SubjectsTableModel.getValueAt(selectedRow, 0).toString()));
				UpdateSubjectsTableData();
			}
		}
	}

	private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int selectedRow = SubjectTable.getSelectedRow();
		if (selectedRow != -1)
			subjectFormView.EditSubjectMode(Integer.parseInt(SubjectsTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void NewSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		subjectFormView.NewSubjectMode();
	}

}
