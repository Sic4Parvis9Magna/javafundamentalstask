package epamtasks.multithreading.t01;

import org.junit.jupiter.api.Test;

import static epamtasks.multithreading.t01.BankAccount.*;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanseTest() {
        String holdersName = "Andrew Smith";
        String fileName = "testAcc.txt";
        BankAccount bankAccount = buildAccount(holdersName, fileName );
        int amount =  (int)bankAccount.getBalanse();
        assertEquals(0,amount);
        System.out.printf("Account of %s have %f $ been deposted%n",bankAccount.getHolder(),
                bankAccount.getBalanse());
    }

    @Test
    void deposit() {
        String holdersName = "Andrew Smith";
        String fileName = "testAcc.txt";
        BankAccount bankAccount = buildAccount(holdersName, fileName );
        int amount =  (int)bankAccount.getBalanse();
        assertEquals(0,amount);
       assertTrue( bankAccount.deposit(34.5));
        System.out.printf("balanse is %f %n",bankAccount.getBalanse());
       assertEquals(34.5,bankAccount.getBalanse());
    }

    @Test
    void windrawAndDepositTest(){
        String holdersName = "Andrew Smith";
        String fileName = "testAcc.txt";
        BankAccount bankAccount = buildAccount(holdersName, fileName );
        int amount =  (int)bankAccount.getBalanse();
        assertEquals(0,amount);
        assertTrue(bankAccount.deposit(400));
        assertTrue(bankAccount.withdraw(250));
        assertFalse(bankAccount.withdraw(250));
        assertTrue(bankAccount.deposit(290));
        System.out.println(bankAccount.getBalanse());
    }

    @Test
    void transActionTest(){
        String holdersName = "Andrew Smith";
        String fileName = "testAcc.txt";
        BankAccount bankAccount = buildAccount(holdersName, fileName );
        String holdersName1 = "Lisa Smith";
        String fileName1 = "testAcc2.txt";
        BankAccount bankAccount1 = buildAccount(holdersName1, fileName1 );
        assertTrue(bankAccount.deposit(400));
        assertTrue(bankAccount1.deposit(400));
        assertTrue(bankAccount.transAction(bankAccount1,200));
        assertFalse(bankAccount.transAction(bankAccount,200));
        assertEquals(bankAccount1.getBalanse(),600.0);
    }
}