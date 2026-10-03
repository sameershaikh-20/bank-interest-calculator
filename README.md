# Bank Account Interest Calculator

A beginner-friendly Java console and Swing GUI application that calculates simple interest and maturity balance for different bank account types.

---

## Overview

This project demonstrates a bank interest calculator implemented in two versions:

- **Console application** (`BankInterestCalculator.java`) — runs in the terminal using `Scanner` for input.
- **Swing GUI application** (`BankInterestGUI.java`) — provides a graphical interface with input fields, dropdown, buttons, and formatted summary.

Both versions **share the exact same calculation methods** (`calculateInterest`, `calculateMaturityBalance`, `getBonusRate`, `displayAccountSummary`) defined in `BankInterestCalculator.java`. The GUI reuses these static methods directly — it does not launch the console `main()`.

---

## Features

| Category | Features |
|----------|----------|
| **Validation** | Account holder name (letters & spaces only), Account number (digits only), Balance (non-negative), Interest rate (0–100%), Duration (> 0) |
| **Account Types** | Savings Account, Fixed Deposit Account, Recurring Deposit Account |
| **Bonus Rules** | Account-type-specific bonus rates based on balance or duration |
| **Calculations** | Simple interest, Final rate (base + bonus), Maturity balance |
| **Output** | Formatted account summary with 2-decimal precision |
| **Console** | Input loops with `InputMismatchException` handling |
| **GUI** | `Calculate Interest` button, `Clear` button, `JOptionPane` error dialogs, `SwingUtilities.invokeLater` |

---

## How It Works

1. **Enter account details** — Name, account number, balance, interest rate, duration.
2. **Validate input** — Reject empty/invalid values with clear error messages.
3. **Select account type** — Savings, Fixed Deposit, or Recurring Deposit.
4. **Determine bonus rate** — Based on account type, balance, and duration.
5. **Calculate final interest rate** — `Final Rate = Base Rate + Bonus Rate`.
6. **Calculate simple interest** — `Interest = (Principal × Rate × Time) / 100`.
7. **Calculate maturity balance** — `Maturity = Principal + Interest`.
8. **Display account summary** — Formatted table with all details.

---

## Interest Calculation

The project uses **simple interest only** (not compound interest).

```text
Final Rate = Entered Rate + Bonus Rate

Simple Interest = (Principal × Rate × Time) / 100

Maturity Balance = Principal + Interest
```

- `Principal` = Account balance
- `Rate` = Final interest rate (base + bonus) in percent per year
- `Time` = Duration in years

---

## Bonus Rate Rules

| Account Type | Condition | Bonus Rate |
|--------------|-----------|------------|
| Savings Account | Balance ≥ ₹50,000 | 0.5% |
| Fixed Deposit Account | Duration ≥ 5 years | 1.0% |
| Fixed Deposit Account | Duration ≥ 2 years | 0.5% |
| Fixed Deposit Account | Duration < 2 years | 0% |
| Recurring Deposit Account | Duration ≥ 3 years | 0.25% |
| Recurring Deposit Account | Duration < 3 years | 0% |

> **Note:** These bonus rates are **project assumptions for learning purposes** and do not reflect real bank policies.

---

## Input Validation

| Field | Rule | Console Handling | GUI Handling |
|-------|------|------------------|--------------|
| Name | Letters and spaces only (`[A-Za-z ]+`) | Loop with `matches()` | `matches()` + `JOptionPane` |
| Account Number | Digits only (`[0-9]+`) | Loop with `matches()` | `matches()` + `JOptionPane` |
| Balance | ≥ 0 | `InputMismatchException` loop | `NumberFormatException` + dialog |
| Interest Rate | 0–100 | `InputMismatchException` loop | `NumberFormatException` + dialog |
| Duration | > 0 | `InputMismatchException` loop | `NumberFormatException` + dialog |

- Console: Uses `Scanner` + `try-catch` with `InputMismatchException`, discards invalid token via `sc.next()`.
- GUI: Uses `Double.parseDouble` + `try-catch` with `NumberFormatException`, shows `JOptionPane.ERROR_MESSAGE`, focuses the offending field.

---

## Project Structure

```text
Bank-Account-Interest-Calculator/
├── BankInterestCalculator.java   # Core logic: calculations, bonus rules, summary, console main()
├── BankInterestGUI.java          # Swing GUI: extends JFrame, reuses BankInterestCalculator methods
└── README.md                     # This file
```

- **BankInterestCalculator.java** — Contains all static calculation methods, bonus logic, validation loops, console I/O, and `main()` for the terminal version.
- **BankInterestGUI.java** — Swing frontend (`extends JFrame`). Creates input form, summary panel, buttons, and wires `ActionListener` events. Calls `BankInterestCalculator.getBonusRate()`, `calculateInterest()`, and `calculateMaturityBalance()` directly.

---

## Technologies Used

- **Language:** Java (JDK 8+)
- **Standard Library Classes:**
  - `java.util.Scanner`
  - `java.util.InputMismatchException`
  - `javax.swing.JFrame`
  - `javax.swing.JPanel`
  - `javax.swing.JLabel`
  - `javax.swing.JTextField`
  - `javax.swing.JComboBox`
  - `javax.swing.JButton`
  - `javax.swing.JOptionPane`
  - `javax.swing.BorderFactory`
  - `java.awt.BorderLayout`
  - `java.awt.GridLayout`
  - `java.awt.Font`
  - `java.awt.Color`
  - `java.awt.Dimension`
  - `java.awt.event.ActionEvent`
  - `java.awt.event.ActionListener`
  - `javax.swing.SwingUtilities`

> No external libraries, build tools (Maven/Gradle), database, or network access.

---

## Java Concepts Demonstrated

- **Static methods** — `calculateInterest`, `getBonusRate`, `displayAccountSummary`
- **Parameters & return values** — All calculation methods
- **Control flow** — `if-else` (bonus rules), `switch` (account type menu), `while` loops (input validation)
- **Regular expressions** — `matches("[A-Za-z ]+")`, `matches("[0-9]+")`
- **Exception handling** — `try-catch` for `InputMismatchException` (console) and `NumberFormatException` (GUI)
- **Arrays & generics** — `String[] accountTypes`, `JComboBox<String>`
- **Inheritance** — `public class BankInterestGUI extends JFrame`
- **Encapsulation** — Private fields (`nameField`, `balanceField`, etc.) with internal helper methods
- **Event handling** — `ActionListener` on `Calculate Interest` and `Clear` buttons
- **Swing layouts** — `BorderLayout`, `GridLayout`, nested panels
- **Thread-safe GUI launch** — `SwingUtilities.invokeLater(Runnable)`

---

## Requirements

- **JDK 8 or higher** installed
- Any operating system with a Java desktop environment (Windows, macOS, Linux)

---

## How to Run

### Compile both files

```bash
javac BankInterestCalculator.java BankInterestGUI.java
```

### Run console version

```bash
java BankInterestCalculator
```

### Run GUI version

```bash
java BankInterestGUI
```

---

## Example Calculation

**Scenario:** Savings Account with high balance qualifying for bonus.

| Input | Value |
|-------|-------|
| Account Type | Savings Account |
| Balance (Principal) | ₹100,000 |
| Base Interest Rate | 4% |
| Duration | 2 years |
| Bonus (balance ≥ 50,000) | 0.5% |
| **Final Rate** | **4.5%** |

**Calculations:**

```
Simple Interest = (100000 × 4.5 × 2) / 100 = ₹9,000
Maturity Balance = 100000 + 9000 = ₹109,000
```

---

## Testing

The project has been **manually tested** with the following scenarios:

- ✅ Normal values (e.g., ₹100000, 4%, 2 years)
- ✅ Boundary values (balance = 0, rate = 0, rate = 100, duration = 1)
- ✅ Decimal values (₹12345.67, rate = 4.5%, duration = 2.5)
- ✅ Zero balance (valid for Savings & Recurring Deposit)
- ✅ Invalid inputs (negative numbers, non-numeric text, empty fields, invalid characters in name/account number)

> **No automated JUnit tests** are included in this project.

---

## Limitations

- Simple interest only — no compound interest support.
- Recurring Deposit is treated as a **single lump sum**; monthly instalments are not implemented.
- Bonus rates are **hard-coded** in `getBonusRate()` — not configurable.
- Validation logic is **duplicated** between console and GUI versions.
- Names containing hyphens, apostrophes, or non-ASCII letters are rejected.
- Results are **not persisted** — no file or database storage.
- Only **one account** is processed per run.
- No automated unit tests (JUnit).

---

## Future Scope

- Add **compound interest** calculation option.
- Implement **monthly instalment logic** for Recurring Deposit.
- Replace string-based account types with an **`enum`**.
- Extract shared validation into a **common utility class**.
- Add **file or database storage** for account records.
- Support **multiple accounts** using `ArrayList<Account>`.
- Use **`BigDecimal`** for precise monetary calculations.
- Add **JUnit 5** test suite for automated regression testing.

---

## Author

**Sameer Shaikh**  
B.Tech Computer Science & Engineering  
ITM Skills University  
Java Programming Project