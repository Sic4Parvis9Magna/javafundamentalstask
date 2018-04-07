package epamtasks.multithreading.t03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class IntegerSetterGetter extends Thread {
    private static final Logger log = LogManager.getLogger(IntegerSetterGetter.class);
    private SharedResource resource;
    private boolean run;
    private static final String ERROR_FORMAT  ;

    static {
        ERROR_FORMAT = "error:{}, appears from:{} " ;
    }

    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    @Override
    public void run() {
        int action;

        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }


        log.info("Поток {} завершил работу.",getName());
    } catch (InterruptedException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            Thread.currentThread().interrupt();

    }
}

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;
        int counter=0;

        synchronized (resource) {
            log.info("Поток {} хочет извлечь число.",getName());
            number = resource.getELement();
            while (number == null ) {
                log.info("Поток {} ждет пока очередь заполнится.",getName());
                resource.wait(100);
                log.info("Поток {} возобновил работу.",getName());
                number = resource.getELement();
                if( counter++ > 10)number = (number == null)?0:number;
            }


            log.info("Поток {} извлёк число {}.", getName(), number);
        }
    }

    private void setIntegersIntoResource()  {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            log.info("Поток {} записал число {}.", getName(), number);
            resource.notifyAll();
        }
    }
}

