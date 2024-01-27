package src;

import java.util.*;

public class BankSimulation {

    private final Map<Integer, Customer> customers;
    private final List<Account> accounts;
    private final Scanner scanner;



    public BankSimulation() {
        customers = new HashMap<>();
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }


    public static void main(String[] args) {
        BankSimulation bankSimulation = new BankSimulation();
        bankSimulation.run();
    }


    public void run() {
        int choice;
        do {
            System.out.println("\n----- BANK TRANSACTIONS -----");
            System.out.println("1. List Customer");
            System.out.println("2. Add New Customer");
            System.out.println("3. Customer Transactions");
            System.out.println("0. EXİT");
            System.out.print("Make your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    customerOperationsMenu();
                    break;
                case 0:
                    System.out.println("Checking out...");
                    break;
                default:
                    System.out.println("Invalid selection! Try again.");
            }
        } while (choice != 0);
    }

    public void listCustomers() {
        System.out.println("\n----- CUSTOMERS -----");
        for (Customer customer : customers.values()) {
            System.out.println(customer.getCustomerId() +
                    " - " + customer.getFirstName() +
                    " " + customer.getLastName() +
                    " (" + customer.getCity() + ")");
            listCustomerAccounts(customer);
        }
    }



    public void listCustomerAccounts(Customer customer) {
        System.out.println("----- " + customer.getCustomerId() +
                " " + customer.getFirstName() +
                " " + customer.getLastName() +
                " ACCOUNTS -----");
        boolean hasAccounts = false;
        for (Account account : accounts) {
            if (account.getCustomerId() == customer.getCustomerId()) {
                System.out.println(account.getAccountId() +
                        " - " + account.getAccountType() +
                        " - " + account.getBalance() +
                        " TL");
                hasAccounts = true;
            }
        }
        System.out.println("===============================================");

        if (!hasAccounts) {
            System.out.println("The customer does not have an account opened.\n" +
                    "===============================================");
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////



    public void addCustomer() {
        System.out.println("\n----- ADD NEW CUSTOMER -----");
        System.out.print("Customer Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Customer Surname: ");
        String lastName = scanner.nextLine();
        System.out.print("City:");
        String city = scanner.nextLine();

        Customer newCustomer = new Customer(firstName, lastName, city);
        customers.put(newCustomer.getCustomerId(), newCustomer);

        System.out.println("The customer has been added successfully. Customer ID: " + newCustomer.getCustomerId());
    }

    public void customerOperationsMenu() {
        int customerId;
        do {
            System.out.println("\n----- CUSTOMER TRANSACTIONS -----");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter Customer ID (0 exits): ");
            customerId = scanner.nextInt();
            scanner.nextLine();

            if (customerId != 0) {
                Customer customer = customers.get(customerId);
                if (customer != null) {
                    customerOperations(customer);
                } else {
                    System.out.println("Invalid Customer ID. Try again.");
                }
            }
        } while (customerId != 0);
    }

    public void customerOperations(Customer customer) {
        int choice;
        do {
            System.out.println("\n----- " + customer.getFirstName() +
                    " " + customer.getLastName() +
                    " TRANSACTIONS -----");
                System.out.println("1. Open New Account");
                    System.out.println("2. List Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Inquire Balance");
            System.out.println("0. Return to Main Menu");
            System.out.print("Make your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    openNewAccount(customer);
                    break;
                case 2:
                    listCustomerAccounts(customer);
                    break;
                case 3:
                    depositToAccount(customer);
                    break;
                case 4:
                    withdrawToAccount(customer);
                    break;
                case 5:
                    checkBalance(customer);
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid selection! Try again.");
            }
        } while (choice != 0);
    }

    public void openNewAccount(Customer customer) {
        System.out.println("\n----- OPEN A NEW ACCOUNT-----");
        System.out.println("Select Account Type:");
        System.out.println("1. Current Account (Checking)");
        System.out.println("2. Savings Account");
        System.out.println("3. Credit Card Account (Credit)");
        System.out.print("Seçiminizi yapın: ");
        int accountTypeChoice = scanner.nextInt();
        scanner.nextLine();

        if (accountTypeChoice >= 1 && accountTypeChoice <= 3) {
            AccountType accountType = null;
            switch (accountTypeChoice) {
                case 1:
                    accountType = AccountType.CHECKING;
                    break;
                case 2:
                    accountType = AccountType.SAVINGS;
                    break;
                case 3:
                    accountType = AccountType.CREDIT;
                    break;
            }

            Account newAccount = new Account(customer.getCustomerId(), accountType);
            accounts.add(newAccount);

            System.out.println("The account has been opened successfully. Account number: " + newAccount.getAccountId());
        } else {
            System.out.println("Invalid selection! Opening a new account has been cancelled.");
        }
    }

    public void depositToAccount(Customer customer) {
        System.out.println("\n----- DEPOSIT -----");
        System.out.print("Enter Account Number: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Enter the Amount to Deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.depositToAmount(amount);
        } else {
            System.out.println("Invalid Account Number or the account is not yours! The transaction has been cancelled.");
        }
    }

    public void withdrawToAccount(Customer customer) {
        System.out.println("\n----- WITHDRAW MONEY -----");
        System.out.print("Enter Account Number: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Enter the Amount to Withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdrawToAmount(amount);
        } else {
            System.out.println("Invalid Account Number or the account is not yours! The transaction has been cancelled.");
        }
    }

    public void checkBalance(Customer customer) {
        System.out.println("\n----- INQUIRY BALANCE-----");
        System.out.print("Enter Account Number: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.println("Current balance: " + account.getBalance() + " TL");
        } else {
            System.out.println("Invalid Account Number or the account is not yours! The transaction has been cancelled.");
        }
    }

    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

}
