package epamtasks.exceptions.t02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
public class PropertyReader {
    private static final Logger log = LogManager.getLogger(PropertyReader.class);

    private Map<String,String> content;
    private String value;
    private static final String ERROR_FORMAT  ;

    static {
        ERROR_FORMAT = "error:{}, appears from:{} " ;
    }

    public PropertyReader(){
        content = new HashMap<>();
    }

   public  boolean initialiseResource(String filename,Locale locale)  {
       try {
           ResourceBundle resource = ResourceBundle.getBundle(filename, locale);
           for (String key : resource.keySet()) {
               content.put(key, resource.getString(key));
           }
       }catch (MissingResourceException e){
           log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
           return false;
       }

       return true;
   }

   public String getFullContent(){
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String,String>> entrySet = content.entrySet();
       for (Map.Entry<String,String> ent: entrySet){
           sb.append(ent.getKey()).append(" = ").append(ent.getValue()+"\n");

       }
       return sb.toString();
   }

    public PropertyReader clearContent() {
        content.clear();
        return this;
    }

    public boolean isContainsKey(String key){
        return content.containsKey(key);
    }
    public boolean releaseKey(String key){
        if(!isContainsKey(key)){
            return false;
        }
        value = content.get(key);
        return true;
    }
    public String getKeyContent(){
        return value;
    }
}
