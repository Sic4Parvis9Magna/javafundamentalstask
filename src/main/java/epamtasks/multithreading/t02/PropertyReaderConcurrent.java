package epamtasks.multithreading.t02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PropertyReaderConcurrent {


    private static final Logger log = LogManager.getLogger(PropertyReaderConcurrent.class);
    private Map<String,String> content;
    private volatile String value;
    private final Lock lock;
    private static final String ERROR_FORMAT  ;
    private static final String WRONG_KEY  ;

    static {
        ERROR_FORMAT = "error:{}, appears from:{} " ;
        WRONG_KEY = "NOT_FOUND";

    }

    public PropertyReaderConcurrent(){
        content = new ConcurrentHashMap<>();
        value=WRONG_KEY;
        lock = new ReentrantLock();
    }

    public  boolean initialiseResource(String filename,Locale locale)  {
        lock.lock();
        log.info("{} lock PropertyReader for initialise {}",filename);
        try {

            clearContent();
            try {
                ResourceBundle resource = ResourceBundle.getBundle(filename, locale);
                for (String key : resource.keySet()) {
                    content.put(key, resource.getString(key));
                }
            } catch (MissingResourceException e) {
                log.error(ERROR_FORMAT, e.getMessage(), e.getStackTrace());
                return false;
            }
            return true;
        }finally {
            log.info("{} unlock PropertyReader for initialise {}",filename);
            lock.unlock();
        }

    }
    public  boolean initialiseResource(String filename)  {
        return initialiseResource(filename,Locale.getDefault());
    }

    private PropertyReaderConcurrent clearContent() {
        content.clear();
        return this;
    }

    public boolean isContainsKey(String key){

            return content.containsKey(key);

    }

    public boolean releaseKey(String key){
        lock.lock();
        log.info("{} lock PropertyReader for realise{}",key);
        try {


            if (!isContainsKey(key)) {
                value = WRONG_KEY;
                return false;
            }
            value = content.get(key);
            return true;
        }finally {
            log.info("{} unlock PropertyReader for realise{}",key);
            lock.unlock();
        }
    }
    public String getKeyContent(){
      lock.lock();
      try {
          return value;
      }finally {
          lock.unlock();
      }


    }
    public Set<String> getKeySet(){
        return content.keySet();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Uploaded properties:\n");
        lock.lock();
        log.info("{} lock PropertyReader for get Content as string");
        try {
            Set<Map.Entry<String, String>> entrySet = content.entrySet();
            for (Map.Entry<String, String> ent : entrySet) {
                sb.append(ent.getKey()).append(" = ").append(ent.getValue() + "\n");

            }
        }finally {
            log.info("{} lock PropertyReader for get Content as string");
            lock.unlock();
        }
        return sb.toString();
    }
}
