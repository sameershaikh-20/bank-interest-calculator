import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Swing GUI frontend for the Bank Account Interest Calculator.
// This class does NOT use Scanner and does NOT call BankInterestCalculator.main().
// It only reuses the calculation methods of BankInterestCalculator.
public class BankInterestGUI extends JFrame {

    // Colours used in the GUI (one green accent + neutral colours)
    private Color green = new Color(41, 98, 74);
    private Color lightGreen = new Color(232, 244, 238);
    private Color pageColor = new Color(243, 247, 245);
    private Color grayText = new Color(90, 100, 95);
    private Color cardBorderColor = new Color(215, 225, 220);

    // Input components
    private JTextField nameField;
    private JTextField accNoField;
    private JTextField balanceField;
    private JTextField rateField;
    private JTextField durationField;
    private JComboBox<String> accountTypeBox;

    // Result labels (Account Summary)
    private JLabel holderValue = new JLabel();
    private JLabel accNoValue = new JLabel();
    private JLabel typeValue = new JLabel();
    private JLabel principalValue = new JLabel();
    private JLabel baseRateValue = new JLabel();
    private JLabel bonusValue = new JLabel();
    private JLabel finalRateValue = new JLabel();
    private JLabel durationValue = new JLabel();
    private JLabel interestValue = new JLabel();
    private JLabel maturityValue = new JLabel();

    public BankInterestGUI() {
        setTitle("Bank Account Interest Calculator");
        setSize(920, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(pageColor);
        setLayout(new BorderLayout());

        // ---------- Header ----------
        JLabel titleLabel = new JLabel("Bank Account Interest Calculator");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(green);
        JLabel subtitleLabel = new JLabel("Calculate Interest and Maturity Balance");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitleLabel.setForeground(grayText);
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(pageColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 28, 5, 28));
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // ---------- LEFT CARD: Account Details ----------
        JLabel detailsTitle = new JLabel("Account Details");
        detailsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        detailsTitle.setForeground(green);

        nameField = new JTextField();
        accNoField = new JTextField();
        balanceField = new JTextField();
        rateField = new JTextField();
        durationField = new JTextField();
        styleField(nameField);
        styleField(accNoField);
        styleField(balanceField);
        styleField(rateField);
        styleField(durationField);

        String[] accountTypes = { "Savings Account", "Fixed Deposit Account", "Recurring Deposit Account" };
        accountTypeBox = new JComboBox<String>(accountTypes);
        accountTypeBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
        accountTypeBox.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 14));
        formPanel.setBackground(Color.WHITE);
        formPanel.add(makeLabel("Account Holder Name"));
        formPanel.add(nameField);
        formPanel.add(makeLabel("Account Number"));
        formPanel.add(accNoField);
        formPanel.add(makeLabel("Account Balance (Rs.)"));
        formPanel.add(balanceField);
        formPanel.add(makeLabel("Interest Rate (%)"));
        formPanel.add(rateField);
        formPanel.add(makeLabel("Duration (Years)"));
        formPanel.add(durationField);
        formPanel.add(makeLabel("Account Type"));
        formPanel.add(accountTypeBox);

        JButton calculateButton = new JButton("Calculate Interest");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        calculateButton.setPreferredSize(new Dimension(150, 44));
        calculateButton.setBackground(green);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setOpaque(true);
        calculateButton.setBorderPainted(false);

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(green);
        clearButton.setOpaque(true);
        clearButton.setBorder(BorderFactory.createLineBorder(green, 2));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        JPanel leftTop = new JPanel(new BorderLayout(0, 18));
        leftTop.setBackground(Color.WHITE);
        leftTop.add(detailsTitle, BorderLayout.NORTH);
        leftTop.add(formPanel, BorderLayout.CENTER);
        leftTop.add(buttonPanel, BorderLayout.SOUTH);

        JPanel leftCard = new JPanel(new BorderLayout());
        leftCard.setBackground(Color.WHITE);
        leftCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cardBorderColor),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        leftCard.add(leftTop, BorderLayout.NORTH);

        // ---------- RIGHT CARD: Account Summary ----------
        JLabel summaryTitle = new JLabel("Account Summary");
        summaryTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        summaryTitle.setForeground(green);

        // Maturity Balance is shown big because it is the final result
        JLabel maturityCaption = new JLabel("Maturity Balance");
        maturityCaption.setFont(new Font("SansSerif", Font.PLAIN, 14));
        maturityCaption.setForeground(grayText);
        maturityValue.setFont(new Font("SansSerif", Font.BOLD, 34));
        maturityValue.setForeground(green);
        JPanel maturityBox = new JPanel(new BorderLayout());
        maturityBox.setBackground(lightGreen);
        maturityBox.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        maturityBox.add(maturityCaption, BorderLayout.NORTH);
        maturityBox.add(maturityValue, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        detailsPanel.setBackground(Color.WHITE);
        addSummaryRow(detailsPanel, "Account Holder", holderValue);
        addSummaryRow(detailsPanel, "Account Number", accNoValue);
        addSummaryRow(detailsPanel, "Account Type", typeValue);
        addSummaryRow(detailsPanel, "Principal Amount", principalValue);
        addSummaryRow(detailsPanel, "Base Interest Rate", baseRateValue);
        addSummaryRow(detailsPanel, "Bonus Rate", bonusValue);
        addSummaryRow(detailsPanel, "Final Interest Rate", finalRateValue);
        addSummaryRow(detailsPanel, "Duration", durationValue);
        addSummaryRow(detailsPanel, "Interest Earned", interestValue);
        interestValue.setForeground(green);

        JPanel rightTop = new JPanel(new BorderLayout(0, 15));
        rightTop.setBackground(Color.WHITE);
        rightTop.add(summaryTitle, BorderLayout.NORTH);
        rightTop.add(maturityBox, BorderLayout.CENTER);
        rightTop.add(detailsPanel, BorderLayout.SOUTH);

        JPanel rightCard = new JPanel(new BorderLayout());
        rightCard.setBackground(Color.WHITE);
        rightCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cardBorderColor),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        rightCard.add(rightTop, BorderLayout.NORTH);

        resetSummary();

        // ---------- Two sections side by side ----------
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBackground(pageColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 28, 28, 28));
        centerPanel.add(leftCard);
        centerPanel.add(rightCard);
        add(centerPanel, BorderLayout.CENTER);

        // Event handling with ActionListener
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateAndShow();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
    }

    // Gives a text field a readable font and a thin border with padding
    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cardBorderColor),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
    }

    // Creates a label for the form
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(grayText);
        return label;
    }

    // Adds one "name ... value" row to the summary
    private void addSummaryRow(JPanel panel, String labelText, JLabel valueLabel) {
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        valueLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(makeLabel(labelText));
        panel.add(valueLabel);
    }

    // Converts text to a number. Throws NumberFormatException if it is not a valid number.
    private double readNumber(String text) {
        double value = Double.parseDouble(text.trim());
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new NumberFormatException("Not a normal number");
        }
        return value;
    }

    private void showError(String message, JTextField fieldToFocus) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
        fieldToFocus.requestFocus();
    }

    // Runs when "Calculate Interest" is clicked
    private void calculateAndShow() {
        // Read values from the GUI components
        String name = nameField.getText();
        String accNo = accNoField.getText();
        String accountType = (String) accountTypeBox.getSelectedItem();

        // Validate all inputs (same rules as the console version)
        if (name.trim().isEmpty() || !name.matches("[A-Za-z ]+")) {
            showError("Error: Please enter a valid name using letters and spaces only.", nameField);
            return;
        }

        if (!accNo.matches("[0-9]+")) {
            showError("Error: Please enter a valid account number using digits only.", accNoField);
            return;
        }

        double balance = 0;
        try {
            balance = readNumber(balanceField.getText());
        } catch (NumberFormatException ex) {
            showError("Error: Please enter a valid number for account balance.", balanceField);
            return;
        }
        if (balance < 0) {
            showError("Error: Balance cannot be negative.", balanceField);
            return;
        }

        double rate = 0;
        try {
            rate = readNumber(rateField.getText());
        } catch (NumberFormatException ex) {
            showError("Error: Please enter a valid number for interest rate.", rateField);
            return;
        }
        if (rate < 0) {
            showError("Error: Interest rate cannot be negative.", rateField);
            return;
        } else if (rate > 100) {
            showError("Error: Interest rate cannot be greater than 100%.", rateField);
            return;
        }

        double years = 0;
        try {
            years = readNumber(durationField.getText());
        } catch (NumberFormatException ex) {
            showError("Error: Please enter a valid number for duration.", durationField);
            return;
        }
        if (years <= 0) {
            showError("Error: Duration must be greater than zero.", durationField);
            return;
        }

        // Reuse the SAME core methods from BankInterestCalculator
        double bonus = BankInterestCalculator.getBonusRate(accountType, balance, years);
        double finalRate = rate + bonus;
        double interest = BankInterestCalculator.calculateInterest(balance, finalRate, years);
        double maturity = BankInterestCalculator.calculateMaturityBalance(balance, interest);

        // Display the Account Summary (2 decimal places)
        holderValue.setText(name);
        accNoValue.setText(accNo);
        typeValue.setText(accountType);
        principalValue.setText(String.format("Rs. %.2f", balance));
        baseRateValue.setText(String.format("%.2f %%", rate));
        bonusValue.setText(String.format("%.2f %%", bonus));
        finalRateValue.setText(String.format("%.2f %%", finalRate));
        durationValue.setText(String.format("%.2f year(s)", years));
        interestValue.setText(String.format("Rs. %.2f", interest));
        maturityValue.setText(String.format("Rs. %.2f", maturity));
    }

    // Runs when "Clear" is clicked (does not close the application)
    private void clearAll() {
        nameField.setText("");
        accNoField.setText("");
        balanceField.setText("");
        rateField.setText("");
        durationField.setText("");
        accountTypeBox.setSelectedIndex(0);
        resetSummary();
    }

    // Default values shown in the Account Summary
    private void resetSummary() {
        holderValue.setText("-");
        accNoValue.setText("-");
        typeValue.setText("-");
        principalValue.setText("Rs. 0.00");
        baseRateValue.setText("0.00 %");
        bonusValue.setText("0.00 %");
        finalRateValue.setText("0.00 %");
        durationValue.setText("0.00 year(s)");
        interestValue.setText("Rs. 0.00");
        maturityValue.setText("Rs. 0.00");
    }

    // Separate entry point for the GUI (does NOT start the console program)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankInterestGUI gui = new BankInterestGUI();
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }
        });
    }
}
