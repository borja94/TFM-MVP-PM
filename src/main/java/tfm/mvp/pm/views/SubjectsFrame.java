package tfm.mvp.pm.views;


import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;


public class SubjectsFrame extends JFrame {

	private MenuFrame menuFrame;
	private SubjectFormView subjectFormView;
	private SubjectsCollectionView subjectCollectionView;

	
	public SubjectsFrame(MenuFrame menuFrame) {

		subjectFormView = new SubjectFormView();
		subjectCollectionView = new SubjectsCollectionView();
		
		subjectFormView.setSubjectCollectionView(subjectCollectionView);
		subjectCollectionView.setSubjectFormView(subjectFormView);

		initComponents();
		this.menuFrame = menuFrame;
	}

	private void initComponents() {

		JButton returnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		returnMenuFrameButton.setText("Volver");
		returnMenuFrameButton.addActionListener(e->jButton1ActionPerformed());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(returnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(subjectCollectionView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectFormView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(subjectCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(returnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void jButton1ActionPerformed() {
		this.setVisible(false);
		menuFrame.setVisible(true);
		dispose();
	}

}
