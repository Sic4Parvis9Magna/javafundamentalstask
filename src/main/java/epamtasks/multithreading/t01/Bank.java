package epamtasks.multithreading.t01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static epamtasks.multithreading.t01.BankAccount.*;

public class Bank {
    private static final Logger log = LogManager.getLogger(Bank.class);
    private static final String ERROR_FORMAT ;

    static {
        ERROR_FORMAT ="error:{},\n appears from:{} " ;
    }

    public static void main(String[] args) {
        Optional<BankAccount> account = buildAccount("Samuel Fox",
                "SamAcc.txt");
        Optional<BankAccount> account2 = buildAccount("Samanta Fox",
                "Samanta.txt");
        if(!account.isPresent() || !account2.isPresent()){
            log.error("Account is't  initialized");
            return;
        }
        account.get().deposit(500);
        account2.get().deposit(500);

       AccountManager manager0 = new AccountManager(account.get(),account2.get());
        AccountManager manager1 = new AccountManager(account2.get(),account.get());


        manager0.start();
        manager1.start();
        try {
            manager0.join();
            manager1.join();
        }catch (InterruptedException e){
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            Thread.currentThread().interrupt();
        }


        log.info("End of main thread.");

    }
}
