package epamtasks.exceptions.t01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;



public class FileManager {
    private static final Logger log = LogManager.getLogger(FileManager.class);
    private File currentDir;
    private StringBuilder contentBuffer;
    private static final String ERROR_FORMAT ;

    static {
        ERROR_FORMAT ="error:{}, appears from:{} " ;
    }

    private FileManager(String path){
            currentDir = new File(path);
            contentBuffer = new StringBuilder();
    }


    public static FileManager initialiseWithPath(String path){
        File file = new File(path);
        if(file.exists()) {

            if (file.isFile()){
               return new FileManager(file.getParentFile().getAbsolutePath());

            }else{

                return new FileManager(path);
            }
        }
        return getDefaultManager();
    }

    public static FileManager getDefaultManager(){
               return new FileManager(System.getProperty("user.dir"));
    }
    public String getCurrentPath(){
        return currentDir.getAbsolutePath();
    }

    public StringBuilder getDispFiles(){
        StringBuilder sb =  new StringBuilder();
        for (String str:currentDir.list()) {
            sb.append(str).append("\n");
        }
        return sb;
    }

    public boolean goToDir(String dest) {
        String newPath;

        if(dest.startsWith(File.separator)) {
         newPath = getCurrentPath() + dest;
        }else {
            newPath = dest;
        }

        File file = new File(newPath);
       if( ( file).isDirectory()){
           currentDir = file;
           return true;
       }
       return false;
    }

    public boolean toPreviousDir() {
        File parent = currentDir.getParentFile();
        if(parent == null)return false;
        currentDir = new File(parent.getAbsolutePath());
        return true;
    }

    public boolean makeDir(String dirName) {
        File temp = currentDir;
        currentDir = new File(getCurrentPath()+File.separator+dirName);
        if(currentDir.mkdir()){
            currentDir = temp;
            return true;
        }
        currentDir = temp;

        return false;
    }


    public boolean createFile(String fileName) {
        File file = new File(getCurrentPath()+File.separator+fileName);
        boolean ans = false;
        if(file.exists() && file.isFile())return true;
        try {
            ans= file.createNewFile();

        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
        }
        return ans;
    }

    public boolean deleteFile(String fileName) {
        File file = new File(getCurrentPath()+File.separator+fileName);
        boolean ans = false;

        try {
            ans= Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
        }
        return ans;
    }

    public String getContent() {
        String cont = contentBuffer.toString();
        contentBuffer.delete(0,contentBuffer.length()).trimToSize();
       return cont;
    }

    public boolean writeToFile(String fileName, String myMessage) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(getCurrentPath()+File.separator+fileName))){
            writer.write(myMessage);
        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }
        return true;
    }

    public boolean readFromFile(String fileName) {
        String text = "";
        try(BufferedReader br = new BufferedReader(
                new FileReader(
                        new File(getCurrentPath()+File.separator+fileName)))) {

            while ((text = br.readLine())!= null) {
                contentBuffer.append(text).append("\n");
            }

        } catch (FileNotFoundException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }
        return true;
    }

    public boolean appendToFile(String fileName, String myMessage) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(getCurrentPath()+File.separator+fileName,true))){
            writer.write(myMessage);
        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }
        return true;
    }
}
