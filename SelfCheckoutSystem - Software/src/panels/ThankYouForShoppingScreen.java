package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ThankYouForShoppingScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public ThankYouForShoppingScreen() {
		
		JLabel lblNewLabel = new JLabel("Thank You for Shopping");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 42));
		
		JLabel lblNewLabel_1 = new JLabel("Calgary Co-op!\n");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 42));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(325, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)
							.addGap(16))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(173))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addContainerGap(171, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
