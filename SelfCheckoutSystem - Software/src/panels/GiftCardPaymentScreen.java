package panels;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class GiftCardPaymentScreen extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GiftCardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Giftcard Payment");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Import ImageIcon - from stack 
		Image image = null;
		try {
			image = ImageIO.read(new File("GUI Images/gift-card.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(image != null) {
			image = image.getScaledInstance(285, 100, Image.SCALE_DEFAULT);
			ImageIcon iconLogo = new ImageIcon(image);
			// In init() method write this code
			lblNewLabel.setIcon(iconLogo);
		}
		

		
		JLabel lblNewLabel_1 = new JLabel("Enter your card number to check the amount available:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tap to Redeem Gift Card!");
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_2 = new JLabel("Your remaining payment balance is: ");
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblNewLabel_3 = new JLabel("CAD $");
		
		JButton btnNewButton_1 = new JButton("Complete Payment");
		btnNewButton_1.setBackground(new Color(100, 149, 237));
		
		JLabel lblNewLabel_4 = new JLabel("Current amount on Co-op GIft Card: ");
		
		JTextPane textPane_1 = new JTextPane();
		
		JLabel lblNewLabel_3_1 = new JLabel("CAD $");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(417, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(182))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(500, Short.MAX_VALUE)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
											.addComponent(lblNewLabel_3)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(26)
											.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addComponent(lblNewLabel_1)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
								.addComponent(textPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
								.addComponent(textPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))))
					.addGap(139))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(205)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(313, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3_1)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addComponent(btnNewButton)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(205))
		);
		setLayout(groupLayout);

		// tap to redeem button shouldn't go to new screen, only update the payment balance and print in text box 
		//btnNewButton.addActionListener(new GotoAttendantScreen());
		btnNewButton_1.addActionListener(new GotoPaymentSelector());
		
		
		
	}
	
	private class GotoPaymentSelector implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CommandLineDriver.goToScreen("pay");
		}
	}
	
	
	
	
}
