package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class AttendantMenuScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public AttendantMenuScreen() {
		
		JLabel lblNewLabel = new JLabel("Attendant Menu");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(163)
					.addComponent(lblNewLabel)
					.addContainerGap(187, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(284, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
