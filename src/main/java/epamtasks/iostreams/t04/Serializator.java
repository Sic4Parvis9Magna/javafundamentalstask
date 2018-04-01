package epamtasks.iostreams.t04;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;

public class Serializator {
    private static final Logger log = LogManager.getLogger(Serializator.class);
    private static final String ERROR_FORMAT ="error:{}, appears from:{} ";
    private Serializator(){}

    public static boolean serialiseObject(String file, Object ob){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file))){
            outputStream.writeObject(ob);

        }catch (IOException e){

            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }

        return true;
    }
    public static boolean deserialiseObject(String file,List<Object> dest){
        try(ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file))){
             dest.add(inputStream.readObject());

        }catch (FileNotFoundException e1){

            log.error(ERROR_FORMAT,e1.getMessage(),e1.getStackTrace());
            return false;
        }catch (ClassNotFoundException e2){

            log.error(ERROR_FORMAT,e2.getMessage(),e2.getStackTrace());
            return false;

        }catch (IOException e3){

            log.error(ERROR_FORMAT,e3.getMessage(),e3.getStackTrace());
            return false;
        }
        return true;
    }
}
