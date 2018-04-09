package epamtasks.multithreading.t02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyRunner {
    private static final Logger log = LogManager.getLogger(PropertyRunner.class);

    public static void main(String[] args) {
        PropertyReaderConcurrent propertyReader = new PropertyReaderConcurrent();
        String propName = "QuestionsBundle";
        if(!propertyReader.initialiseResource(propName)) return;

        PropertyConsumer consumer1 = new PropertyConsumer("Consumer1",propertyReader);
        PropertyConsumer consumer2 = new PropertyConsumer("Consumer2",propertyReader);
        PropertyConsumer consumer3 = new PropertyConsumer("Consumer3",propertyReader);
        consumer1.start();
        consumer2.start();
        consumer3.start();


        try {
            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            log.error("Error  {} appears from {}",e.getMessage(),e.getStackTrace());
            Thread.currentThread().interrupt();
        }


        log.info("End of main thread!");
    }
}
