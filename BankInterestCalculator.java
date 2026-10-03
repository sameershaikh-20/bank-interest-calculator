import java.util.InputMismatchException;
import java.util.Scanner;

public class BankInterestCalculator {

    // Module 3: Interest Calculation -> Simple Interest = (P * R * T) / 100
    public static double calculateInterest(double principal, double rate, double years) {
        double interest = (principal * rate * years) / 100;
        return interest;
    }

    // Module 4: Maturity Calculation -> Maturity = Principal + Interest
    public static double calculateMaturityBalance(double principal, double interest) {
        double maturity = principal + interest;
        return maturity;
    }

    // Account-specific rules using if-else (ASSUMPTIONS for this project)
    // Returns the bonus rate to be added to the entered interest rate
    public static double getBonusRate(String accountType, double balance, double years) {
        double bonus = 0;
        if (accountType.equals("Savings Account")) {
            if (balance >= 50000) {
                bonus = 0.5;
            } else {
                bonus = 0;
            }
        } else if (accountType.equals("Fixed Deposit Account")) {
            if (years >= 5) {
                bonus = 1.0;
            } else if (years >= 2) {
                bonus = 0.5;
            } else {
                bonus = 0;
            }
        } else if (accountType.equals("Recurring Deposit Account")) {
            if (years >= 3) {
                bonus = 0.25;
            } else {
                bonus = 0;
            }
        }
        return bonus;
    }

    // Module 5: Account Summary
    public static void displayAccountSummary(String name, String accNo, String accountType,
            double principal, double baseRate, double bonusRate, double finalRate,
            double years, double interest, double maturity) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("          ACCOUNT SUMMARY");
        System.out.println("========================================");
        System.out.println("Account Holder     : " + name);
        System.out.println("Account Number     : " + accNo);
        System.out.println("Account Type       : " + accountType);
        System.out.printf("Principal Amount   : Rs. %.2f%n", principal);
        System.out.printf("Base Interest Rate : %.2f %%%n", baseRate);
        System.out.printf("Bonus Rate         : %.2f %%%n", bonusRate);
        System.out.printf("Final Interest Rate: %.2f %%%n", finalRate);
        System.out.printf("Duration           : %.2f year(s)%n", years);
        System.out.printf("Interest Earned    : Rs. %.2f%n", interest);
        System.out.printf("Maturity Balance   : Rs. %.2f%n", maturity);
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("     BANK ACCOUNT INTEREST CALCULATOR");
        System.out.println("========================================");

        // Module 1: Account Registration (with input validation)
        String name = "";
        while (name.trim().isEmpty() || !name.matches("[A-Za-z ]+")) {
            System.out.print("Enter Account Holder Name: ");
            name = sc.nextLine();
            if (name.trim().isEmpty() || !name.matches("[A-Za-z ]+")) {
                System.out.println("Error: Please enter a valid name using letters and spaces only.");
            }
        }

        String accNo = "";
        while (!accNo.matches("[0-9]+")) {
            System.out.print("Enter Account Number: ");
            accNo = sc.nextLine();
            if (!accNo.matches("[0-9]+")) {
                System.out.println("Error: Please enter a valid account number using digits only.");
            }
        }

        double balance = 0;
        while (true) {
            System.out.print("Enter Account Balance (Rs.): ");
            try {
                balance = sc.nextDouble();
                if (balance < 0) {
                    System.out.println("Error: Balance cannot be negative.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number for account balance.");
                sc.next(); // throw away the wrong input
            }
        }

        double rate = 0;
        while (true) {
            System.out.print("Enter Interest Rate (% per year): ");
            try {
                rate = sc.nextDouble();
                if (rate < 0) {
                    System.out.println("Error: Interest rate cannot be negative.");
                } else if (rate > 100) {
                    System.out.println("Error: Interest rate cannot be greater than 100%.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number for interest rate.");
                sc.next(); // throw away the wrong input
            }
        }

        double years = 0;
        while (true) {
            System.out.print("Enter Duration (in years): ");
            try {
                years = sc.nextDouble();
                if (years <= 0) {
                    System.out.println("Error: Duration must be greater than zero.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number for duration.");
                sc.next(); // throw away the wrong input
            }
        }

        // Module 2: Account Type Selection using switch
        System.out.println();
        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Fixed Deposit Account");
        System.out.println("3. Recurring Deposit Account");

        String accountType = "";
        while (accountType.equals("")) {
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        accountType = "Savings Account";
                        break;
                    case 2:
                        accountType = "Fixed Deposit Account";
                        break;
                    case 3:
                        accountType = "Recurring Deposit Account";
                        break;
                    default:
                        System.out.println("Error: Invalid account type selected.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid account type number.");
                sc.next(); // throw away the wrong input
            }
        }

        // Apply account-specific rules (if-else inside getBonusRate)
        double bonus = getBonusRate(accountType, balance, years);
        double finalRate = rate + bonus;
        if (bonus > 0) {
            System.out.println("Bonus rate of " + bonus + "% applied.");
        } else {
            System.out.println("No bonus rate applicable.");
        }

        // Calculations
        double interest = calculateInterest(balance, finalRate, years);
        double maturity = calculateMaturityBalance(balance, interest);

        // Display summary
        displayAccountSummary(name, accNo, accountType, balance, rate, bonus, finalRate, years, interest, maturity);

        sc.close();
    }
}