package epamtasks.multithreading.t02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyConsumer extends Thread {
    private static final Logger log = LogManager.getLogger(PropertyConsumer.class);
    private PropertyReaderConcurrent properties;

    PropertyConsumer(String name,PropertyReaderConcurrent properties){
        super(name);
        this.properties = properties;
    }

    @Override
    public void run(){
        for(int i =0; i<5;i++){
            String key1 = "q"+i;
            if(properties.releaseKey(key1)){
                log.info("{} get key q{} and relise value {}."
                        ,Thread.currentThread().getName(),i,properties.getKeyContent());
            }
            String key2 = "a"+i;
            if(properties.releaseKey(key2)){
                log.info("{} get key a{} and relise value {}."
                        ,Thread.currentThread().getName(),i,properties.getKeyContent());
            }
        }

    }
}
