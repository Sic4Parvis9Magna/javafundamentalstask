package epamtasks.multithreading.t01;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class AccountManager extends Thread{
    private BankAccount bankAccount;
    private BankAccount bankAccount2;
    private final int managerId;

    private static int managerCounter = 1;


    public AccountManager(BankAccount bankAccount,BankAccount bankAccount2){
        this.bankAccount = bankAccount;
        this.bankAccount2 = bankAccount2;
        managerId = managerCounter++;
    }

    public int getManagerId() {
        return managerId;
    }

    @Override
    public void run(){
       // Random random = new Random();
       // double randomVal=0;

            for (int i = 0; i < 3; i++) {

                    getTransAction();

            }

    }

    public AccountManager getTransAction(){
        Random random = new Random();
        boolean reult=false;
        double amount = random.nextDouble()*100+10;
        if((Math.random()*10+1)>5){
            log.info("Manager#{} trying make transaction at {}$ from account {} to {} .",
                    managerId,
                    amount,
                    bankAccount.getHolder(),
                    bankAccount2.getHolder());

          reult= bankAccount.transAction(bankAccount2, amount);

            log.info("Manager#{} {} transaction at {}$ from account {} to {} .",
                    managerId,
                    reult,
                    amount,
                    bankAccount.getHolder(),
                    bankAccount2.getHolder());
        }else {
            log.info("Manager#{} trying to make transaction at {}$ from account {} to {} .",
                    managerId,
                    amount,
                    bankAccount2.getHolder(),
                    bankAccount.getHolder());

           reult= bankAccount2.transAction(bankAccount, amount);

            log.info("Manager#{} {} made transaction at {}$ from account {} to {} .",
                    managerId,
                    reult,
                    amount,
                    bankAccount2.getHolder(),
                    bankAccount.getHolder());
        }
        return this;
    }
}
