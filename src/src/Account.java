package src;

public class Account {

    // FIELDS   /////////////////////////////////////////////////////////////
    private static int nextId =9000;
    private int accountId;
    private int customerId;
    private double balance;
    private AccountType accountType;


    public Account() {
    }

    public Account(int customerId, AccountType accountType) {
        this.accountId = ++nextId;
        this.customerId = customerId;
        this.accountType = accountType;
    }



    public int getAccountId() {
        return accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }



    public void depositToAmount(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " TL has been successfully transferred to your account.");
            System.out.println("Current Balance: " + balance + " TL");
        } else {
            System.out.println("The deposit amount must be greater than zero! Try again...");
        }
    }

    public void withdrawToAmount(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println(amount + " TL has been successfully withdrawn from your account.");
                System.out.println("Current Balance: " + balance);
            } else {
                System.out.println(amount + " Insufficient balance for TL amount!");
                System.out.println("Current Balance: " + balance);
            }
        } else {
            System.out.println("The amount to be withdrawn must be greater than zero! Try again...");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }

}
