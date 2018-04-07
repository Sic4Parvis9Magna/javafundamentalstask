package epamtasks.multithreading.t01p2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static epamtasks.multithreading.t01p2.BankAccountConcurrent.buildAccount;

public class BankConcurrent {

    private static final String ERROR_FORMAT ;
    private static final Logger log = LogManager.getLogger(BankConcurrent.class);

    static {
        ERROR_FORMAT ="error:{},\n appears from:{} " ;
    }

    public static void main(String[] args) {
        Optional<BankAccountConcurrent> account = buildAccount("Jo Block",
                "Jo.txt");
        Optional<BankAccountConcurrent> account2 = buildAccount("Lim Aiyx",
                "Lim.txt");
        if(!account.isPresent() || !account2.isPresent()){
            log.error("Account is't  initialized");
            return;
        }
        account.get().deposit(500);
        account2.get().deposit(500);

        AccountManagerConcurrent manager0 = new AccountManagerConcurrent(account.get(),account2.get());
        AccountManagerConcurrent manager1 = new AccountManagerConcurrent(account2.get(),account.get());


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
