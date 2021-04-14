package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;

import driver.CommandLineDriver;






public class CashPaymentScreen extends JPanel {
	BigDecimal cashPayed = new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP);
	BigDecimal balance = CommandLineDriver.controlSoftware.shoppingCart.getTotalPayment();
	//Keep track of what cash has been inserted
	int bills5 = 0;
	int bills10 = 0;
	int bills20 = 0;
	int bills50 = 0;
	int bills100 = 0;
	
	int nickels = 0;
	int dimes = 0;
	int quarters = 0;
	int loonies = 0;
	int toonies = 0;
	
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_8;
	
	JButton btnNewButton = new JButton("$5 bill");
	JButton btnNewButton_1 = new JButton("Nickel");
	JButton btnNewButton_2 = new JButton("Loonie");
	JButton btnNewButton_3 = new JButton("Quarter");
	JButton btnNewButton_4 = new JButton("$10 bill");
	JButton btnNewButton_5 = new JButton("$50 bill");
	JButton btnNewButton_6 = new JButton("Dime");
	JButton btnNewButton_7 = new JButton("$20 bill");
	JButton btnNewButton_8 = new JButton("$100 bill");
	JButton btnNewButton_9 = new JButton("Toonie");
	JButton btnNewButton_10 = new JButton("Continue");
	
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public boolean checkBalanceZero() {
		if (balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) != 1) {
			return true;
		}
		return false;
	}
	
	
	//Returns a hashmap that will allow control software to see how many of each type of cash was payed
	public HashMap<String,Integer> getCashPayed(){
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("bills5", bills5);
		map.put("bills10", bills10);
		map.put("bills20", bills20);
		map.put("bills50", bills50);
		map.put("bills100", bills100);
		map.put("nickels", nickels);
		map.put("dimes", dimes);
		map.put("quarters", quarters);
		map.put("loonies", loonies);
		map.put("toonies", toonies);
		return map;
	}
	
	/**
	 * Create the panel.
	 */
	public CashPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Cash Payment");

		JLabel lblNewLabel_1 = new JLabel(cashPayed.toString());
		
		lblNewLabel_2 = new JLabel(balance.toString());
		BigDecimal combined = cashPayed.add(balance);
		lblNewLabel_3 = new JLabel(CommandLineDriver.controlSoftware.shoppingCart.getTotalPayment().toString());
	
		lblNewLabel_8 = new JLabel("0.0");
		
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CommandLineDriver.controlSoftware.shoppingCart.getTotalPayment().compareTo(new BigDecimal(0)) == 1) {
					int numOfNotes = bills5 + bills10 + bills20 + bills50 + bills100;
					Banknote[] banknoteArray = new Banknote[numOfNotes];
					int i;
					for(i = 0; i<bills5; i++) {
						banknoteArray[i] = new Banknote(5, CommandLineDriver.controlSoftware.currency);
					}
					for(i = i; i < bills10; i++) {
						banknoteArray[i] = new Banknote(10, CommandLineDriver.controlSoftware.currency);
					}
					for(i = i; i < bills20; i++) {
						banknoteArray[i] = new Banknote(20, CommandLineDriver.controlSoftware.currency);
					}
					for(i = i; i < bills50; i++) {
						banknoteArray[i] = new Banknote(50, CommandLineDriver.controlSoftware.currency);
					}
					for(i = i; i < bills100; i++) {
						banknoteArray[i] = new Banknote(100, CommandLineDriver.controlSoftware.currency);
					}
					
					int numOfCoins = nickels + dimes + quarters + loonies + toonies;
					Coin[] coinArray = new Coin[numOfCoins];
					int j;
					for(j = 0; j<nickels; j++) {
						coinArray[j] = new Coin(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency);
					}
					for(j = j; j<dimes; j++) {
						coinArray[j] = new Coin(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency);
					}
					for(j = j; j<quarters; j++) {
						coinArray[j] = new Coin(new BigDecimal(0.25).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency);
					}
					for(j = j; j<loonies; j++) {
						coinArray[j] = new Coin(new BigDecimal(1.0).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency);
					}
					for(j = j; j<toonies; j++) {
						coinArray[j] = new Coin(new BigDecimal(2.0).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency);
					}
					try {
					CommandLineDriver.controlSoftware.cashToPay(coinArray, banknoteArray);
					}
					catch(Exception we) {
						we.printStackTrace();
					}
				}
				CommandLineDriver.goToScreen("thank");
			}
		});
		
		JButton btnNewButton_11 = new JButton("Back");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandLineDriver.goToScreen("pay");
			}
		});
		
		btnNewButton_10.setEnabled(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills5 += 1;
					cashPayed = cashPayed.add(new BigDecimal(5.0));
					balance = balance.subtract(new BigDecimal(5.0));
					CommandLineDriver.controlSoftware.calculateBillPayment(5);
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
					CommandLineDriver.controlSoftware.selfCheckout.banknoteInput.accept(new Banknote(5, CommandLineDriver.controlSoftware.currency));
					}
					catch(Exception exc) {
						exc.printStackTrace();
					}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					nickels += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
					balance = balance.subtract(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.coinSlot.accept(new Coin(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					loonies += 1;
					cashPayed = cashPayed.add(new BigDecimal(1.0));
					balance = balance.subtract(new BigDecimal(1.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.coinSlot.accept(new Coin(new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
					if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					quarters += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.25));
					balance = balance.subtract(new BigDecimal(0.25));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.coinSlot.accept(new Coin(new BigDecimal(0.25).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
					if(checkBalanceZero()) {
						lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
						btnNewButton.setEnabled(false);
						btnNewButton_1.setEnabled(false);
						btnNewButton_2.setEnabled(false);
						btnNewButton_3.setEnabled(false);
						btnNewButton_4.setEnabled(false);
						btnNewButton_5.setEnabled(false);
						btnNewButton_6.setEnabled(false);
						btnNewButton_7.setEnabled(false);
						btnNewButton_8.setEnabled(false);
						btnNewButton_9.setEnabled(false);
						btnNewButton_10.setEnabled(true);
					}
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills10 += 1;
					cashPayed = cashPayed.add(new BigDecimal(10.0));
					balance = balance.subtract(new BigDecimal(10.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.banknoteInput.accept(new Banknote(10, CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills50 += 1;
					cashPayed = cashPayed.add(new BigDecimal(50.0));
					balance = balance.subtract(new BigDecimal(50.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.banknoteInput.accept(new Banknote(50, CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					dimes += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
					balance = balance.subtract(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.coinSlot.accept(new Coin(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills20 += 1;
					cashPayed = cashPayed.add(new BigDecimal(20.0));
					balance = balance.subtract(new BigDecimal(20.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.banknoteInput.accept(new Banknote(20, CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills100 += 1;
					cashPayed = cashPayed.add(new BigDecimal(100.0));
					balance = balance.subtract(new BigDecimal(100.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.banknoteInput.accept(new Banknote(100, CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setEnabled(false);
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					toonies += 1;
					cashPayed = cashPayed.add(new BigDecimal(2.0));
					balance = balance.subtract(new BigDecimal(2.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
					try {
						CommandLineDriver.controlSoftware.selfCheckout.coinSlot.accept(new Coin(new BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP), CommandLineDriver.controlSoftware.currency));
						}
						catch(Exception exc) {
							exc.printStackTrace();
						}
				}
				if(checkBalanceZero()) {
					lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(balance).setScale(2, RoundingMode.HALF_UP).toString());
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_10.setEnabled(true);
				}
			}
		});
		
		
		String[] columnNames = {"Cash Payed",
                "Balance"};
		Object[][] data = {
			    {cashPayed, balance,},
			};
		
		JLabel lblNewLabel_4 = new JLabel("Cash Payed");
		
		JLabel lblNewLabel_5 = new JLabel("Balance");
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		
		JLabel lblNewLabel_7 = new JLabel("Change");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(62)
									.addComponent(lblNewLabel_1)
									.addGap(91))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
											.addComponent(lblNewLabel))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnNewButton_11)
														.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
											.addGap(50)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_4)
												.addComponent(lblNewLabel_7)
												.addComponent(lblNewLabel_8))))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_5))
							.addGap(46))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGap(166)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_10))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_3))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)))
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_4)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_6)
							.addComponent(lblNewLabel_4)
							.addComponent(lblNewLabel_5)
							.addComponent(lblNewLabel_6)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_7)
								.addComponent(btnNewButton_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_5)
								.addComponent(btnNewButton_2))
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_8)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_8)
						.addComponent(btnNewButton_9)
						.addComponent(btnNewButton_10)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(btnNewButton_11)
					.addGap(20))
		);
		setLayout(groupLayout);

	}
	
	public void enableContinue() {
		
	}
}
