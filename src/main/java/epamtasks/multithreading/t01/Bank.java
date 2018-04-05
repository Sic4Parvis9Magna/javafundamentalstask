package epamtasks.multithreading.t01;

public class Bank {
    public static void main(String[] args) {
        BankAccount account = BankAccount.buildAccount("Samuel Fox",
                "SamAcc.txt");
        BankAccount account2 = BankAccount.buildAccount("Samanta Fox",
                "Samanta.txt");

        account.deposit(1000);
        account2.deposit(500);

        new AccountManager(account,account2).start();
        new AccountManager(account2,account).start();

    }
}
