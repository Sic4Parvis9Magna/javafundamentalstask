package epamtasks.iostreams.t01;


import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.apache.logging.log4j.LogManager.*;

public class JavaKeyWords {
    private static final Logger log = getLogger(JavaKeyWords.class);
    private Set<String> wordSet;
    private static final String ERROR_FORMAT  ;

    static {
        ERROR_FORMAT = "error:{}, appears from:{} " ;
    }

    public JavaKeyWords(){
        wordSet = new HashSet<>();
    }

    public boolean initialiseWords(String fileName){

       try(FileInputStream inputStream = new FileInputStream(fileName);
           Scanner scanner = new Scanner(inputStream)){
           while (scanner.hasNext()){
               wordSet.add(scanner.next());
           }

        }catch (FileNotFoundException e1){

           log.error(ERROR_FORMAT,e1.getMessage(),e1.getStackTrace());
           return false;
       }catch (IOException e2){

           log.error(ERROR_FORMAT,e2.getMessage(),e2.getStackTrace());
           return false;
       }
        return  true;
    }
    public Set<String> getWordSet(){return wordSet;}
    public JavaKeyWords printWords(){
        for (String word: wordSet){
            log.info(word);
        }
        log.info("Total number of words: {}",wordSet.size());
        return this;
    }

   }
