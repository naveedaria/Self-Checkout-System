package panels;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;

public class MainScreen extends JPanel {
	private JTextField txtEnterYourBarcode;
	public JPasswordField pwd;
	/**
	 * Create the panel.
	 */
	//Step 1: After you make a new panel, this code gets generated by going into the Design tab
	public MainScreen() {
		//Step 5 (optional) : if you have a UI element that you want to access the data of (i.e. text field) in an ActionListener, you need to make it a class field and make it public
		JButton nextButton = new JButton("Finish & Pay");
		
		JList itemList = new JList();
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		txtEnterYourBarcode = new JTextField();
		txtEnterYourBarcode.setText("Enter your barcode and press \"Scan Barcode\"...");
		txtEnterYourBarcode.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Scanner:");
		
		JButton scanButton = new JButton("Scan Barcode");
		
		JLabel lblNewLabel_2 = new JLabel("Scan & Bag");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		
		JLabel announcementsLabel = new JLabel("     ");
		
		JButton lookupButton = new JButton("Look Up Item");
		
		JButton bagItemButton = new JButton("Bag Item");
		
		JButton skipBaggingBUtton = new JButton("Skip Bagging");
		
		JButton pluCodeButton = new JButton("Enter PLU Code");
		
		JButton membershipCardButton = new JButton("Swipe Membership Card");
		
		JButton ownBagButton = new JButton("Use Own Bag");
		
		JButton attendantButton = new JButton("Call Attendant");
		
		JButton removeItemButton = new JButton("Remove Item");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEnterYourBarcode))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(textArea, Alignment.LEADING)
							.addComponent(itemList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(scanButton)
							.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
							.addComponent(bagItemButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(skipBaggingBUtton))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
								.addComponent(announcementsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(attendantButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
										.addComponent(lookupButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
										.addComponent(membershipCardButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(removeItemButton, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
										.addComponent(ownBagButton, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
										.addComponent(pluCodeButton, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))))))
					.addGap(24))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(303, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(273))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(itemList, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(announcementsLabel)
							.addGap(235)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(attendantButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addComponent(removeItemButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(membershipCardButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(ownBagButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pluCodeButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(lookupButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtEnterYourBarcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scanButton)
						.addComponent(skipBaggingBUtton)
						.addComponent(bagItemButton))
					.addGap(20))
		);
		setLayout(groupLayout);
		//Step 3: Then add the listener to the button LIKE THIS
		nextButton.addActionListener(new GotoNextScreen());
		attendantButton.addActionListener(new GotoAttendantScreen());
		lookupButton.addActionListener(new GoToLookupScreen());
		txtEnterYourBarcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barcodeFieldMouseClicked(evt);
            }
        });
	}
	//Step 2: if you have a button that you want to do something, you need to make an action listener LIKE THIS
	private class GotoNextScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String bag = showBagsScreen();
			if(!bag.trim().isEmpty()) {
				int bagNum = Integer.parseInt(bag);
			}
			
			
			CommandLineDriver.goToScreen("pay");
			
		}
		
	}
	private class GoToLookupScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("lookup");
			
		}
		
	}
	private class GotoAttendantScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String pass = showLoginScreen();
			if(pass.trim().equals("seng2021")) {
				CommandLineDriver.goToScreen("attendant");
			} else {
				showIncorrectMessage();
			}
			
			
		}
		
	}
	
	private String showLoginScreen() {
		
		pwd = new JPasswordField(10);
		
		JOptionPane.showConfirmDialog(
                this,
                pwd,"Please enter Attendat password: ",
                JOptionPane.OK_CANCEL_OPTION
                );
		String s = new String(pwd.getPassword());
		if(s == null) {
			s = "";
		}
		return s;
	}
	
	private void showIncorrectMessage() {
		JOptionPane.showMessageDialog(this,
			    "The passcode entered is incorrect. Please try again.",
			    "Incorrect Passcode",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	private String showBagsScreen() {
		String b = JOptionPane.showInputDialog(
                this,
                "How many bags would you like to purchase: ",
                "Enter Bags",
                JOptionPane.QUESTION_MESSAGE
                );
		if(b == null || b.trim() == "") {
			b = "0";
		}
		return b;
	}
	
	private void barcodeFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        if (this.txtEnterYourBarcode.getText().equals("Enter your barcode and press \"Scan Barcode\"...")) {
            txtEnterYourBarcode.setText("");
        }
    } 
}



