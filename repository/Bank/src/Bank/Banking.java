package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankingApp extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private double totalAmount = 0.0;
    private JLabel totalLabel;
    private JTextField amountField;

    public BankingApp() {
        setTitle("Banking Application");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        totalLabel = new JLabel("Enter Amount: " );
        amountField = new JTextField(10);

        // Panel for amount input
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel(""));
        inputPanel.add(amountField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        // Add components to the main frame
        add(totalLabel);
        add(inputPanel);
        add(buttonPanel);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                totalAmount += amount;
                amountField.setText("");
                showTotalPage();
            } else {
                JOptionPane.showMessageDialog(this, "Enter a positive amount to deposit.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a number.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= totalAmount) {
                totalAmount -= amount;
                amountField.setText("");
                showTotalPage();
            } else {
                JOptionPane.showMessageDialog(this, "Enter a valid amount to withdraw.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a number.");
        }
    }

    private void showTotalPage() {
        JFrame totalFrame = new JFrame("Total Amount");
        totalFrame.setSize(200, 150);
        totalFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        totalFrame.setLayout(new FlowLayout());

        JLabel totalAmountLabel = new JLabel("Total Amount: " + totalAmount);
        totalFrame.add(totalAmountLabel);
        totalFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingApp app = new BankingApp();
            app.setVisible(true);
        });
    }
}

