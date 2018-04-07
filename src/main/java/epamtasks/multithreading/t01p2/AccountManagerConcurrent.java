package epamtasks.multithreading.t01p2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
public class AccountManagerConcurrent extends Thread {
    private static final Logger log = LogManager.getLogger(AccountManagerConcurrent.class);
    private BankAccountConcurrent bankAccount;
    private BankAccountConcurrent bankAccount2;
    private final int managerId;

    private static int managerCounter ;


    public AccountManagerConcurrent(BankAccountConcurrent bankAccount,
                                    BankAccountConcurrent bankAccount2){
        super();
        this.bankAccount = bankAccount;
        this.bankAccount2 = bankAccount2;
        managerId = managerCounter++;
        setName("Manager#"+managerId);
    }

    public int getManagerId() {
        return managerId;
    }

    @Override
    public void run(){

        for (int i = 0; i < 2; i++) {

            getTransAction();
            getWithdraw();
            getDeposit();

        }


    }

    private AccountManagerConcurrent getTransAction(){
        Random random = new Random();
        boolean reult=false;
        double amount = random.nextDouble()*500+10;
        if((Math.random()*10+1)>5){
            log.info("{} trying make transaction at {}$ from account {} to {} .",
                    Thread.currentThread().getName(),
                    (int)amount,
                    bankAccount.getHolder(),
                    bankAccount2.getHolder());

            reult= bankAccount.transAction(bankAccount2, amount);

            log.info("{} {} transaction at {}$ from account {} to {} .",
                    Thread.currentThread().getName(),
                    reult,
                    (int)amount,
                    bankAccount.getHolder(),
                    bankAccount2.getHolder());
        }else {
            log.info("{} trying to make transaction at {}$ from account {} to {} .",
                    Thread.currentThread().getName(),
                    (int)amount,
                    bankAccount2.getHolder(),
                    bankAccount.getHolder());

            reult= bankAccount2.transAction(bankAccount, amount);

            log.info("{} {} made transaction at {}$ from account {} to {} .",
                    Thread.currentThread().getName(),
                    reult,
                    (int)amount,
                    bankAccount2.getHolder(),
                    bankAccount.getHolder());
        }
        return this;
    }

    private AccountManagerConcurrent getWithdraw(){
        Random random = new Random();

        if(random.nextInt(100)%2 !=0){
            bankAccount.withdraw(random.nextDouble()*200);
        }else{
            bankAccount2.withdraw(random.nextDouble()*200);
        }
        return this;
    }

    private AccountManagerConcurrent getDeposit(){
        Random random = new Random();

        if(random.nextInt(100)%2 !=0){
            bankAccount.deposit(random.nextDouble()*200);
        }else{
            bankAccount2.deposit(random.nextDouble()*200);
        }
        return this;
    }
}
