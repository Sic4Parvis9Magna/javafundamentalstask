package epamtasks.multithreading.t01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


public class BankAccount {
    private static final Logger log = LogManager.getLogger(BankAccount.class);
    private String holder;
    private String filename;
    private Object lock = new Object();
    private static final String ERROR_FORMAT ;


    static {
        ERROR_FORMAT ="error:{},\n appears from:{} " ;
    }


    private BankAccount(String holder, String filename) {
        this.holder = holder;
        this.filename = filename;

    }

    public static BankAccount buildAccount(String holder, String fileName)  {
      try(BufferedWriter writer = new BufferedWriter( new FileWriter(fileName))) {
          writer.write("0.0");
      } catch (IOException e) {
          log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
      }
        return new BankAccount(holder,fileName);
    }



    public String getHolder() {
        return holder;
    }
    public String getFilename() {
        return filename;
    }

    public double getBalanse(){
        double am = 0;
        synchronized (lock){
            try(BufferedReader br = new BufferedReader(
                    new FileReader(filename))) {

               String content = br.readLine();
                log.info("{}'s Current balance: {}",getHolder(),content);
               if(content == null){
                   return 0;
               }else {
                  am += parseDouble(content);
               }
            } catch (FileNotFoundException e) {
                log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            } catch (IOException e1) {
                log.error(ERROR_FORMAT,e1.getMessage(),e1.getStackTrace());
            }
        }

        return am;
    }

    private static double parseDouble(String string){
        double result =0;
        try {
            result = Double.parseDouble(string);
        }catch (NumberFormatException nf){
            log.error(ERROR_FORMAT,nf.getMessage(),nf.getStackTrace());
        }
        return result;
    }

    public boolean deposit(double amount){
        if(amount <=0)return false;
        synchronized (lock){
            Double am = amount + getBalanse();
            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filename))) {

                writer.write(am.toString());

            } catch (FileNotFoundException e) {
                log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
                return false;
            } catch (IOException e2) {
                log.error(ERROR_FORMAT,e2.getMessage(),e2.getStackTrace());
                return false;
            }
        }

        return true;
    }

    public boolean withdraw(double amount){
        synchronized (lock){
            Double am =  getBalanse();
          if(amount > am)return false;
              am -= amount;


            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filename))) {

                writer.write(am.toString());

            } catch (FileNotFoundException e) {
                log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
                return false;
            } catch (IOException e2) {
                log.error(ERROR_FORMAT,e2.getMessage(),e2.getStackTrace());
                return false;
            }
        }

        return true;

    }

    public boolean transAction(BankAccount destination, double amount){
        if(this == destination) return false;
        synchronized (this) {

                if (!this.withdraw(amount)) {
                    return false;
                } else if (!destination.deposit(amount)) {
                    this.deposit(amount);
                    return false;
                }
                return true;

        }

    }

}
