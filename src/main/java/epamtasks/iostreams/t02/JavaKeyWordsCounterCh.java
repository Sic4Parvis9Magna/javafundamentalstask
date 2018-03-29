package epamtasks.iostreams.t02;

import epamtasks.iostreams.t01.JavaKeyWords;
import epamtasks.iostreams.t01.JavaKeyWordsCounter;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.logging.log4j.LogManager.getLogger;

public class JavaKeyWordsCounterCh {
    private static final Logger log = getLogger(JavaKeyWordsCounter.class);
    private Map<String,Integer> wordMap;
    private StringBuilder content;
    private static final String ERROR_FORMAT ;

    static {
        ERROR_FORMAT ="error:{}, appears from:{} " ;
    }

    public JavaKeyWordsCounterCh(){
        content = new StringBuilder();
        wordMap = new HashMap<>();
    }

    public boolean readContent(String fileName){

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){

            String currentLine;
            while ((currentLine = br.readLine()) != null){
                content.append(currentLine).append("\n");
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
    public JavaKeyWordsCounterCh initialiseWordMap(String fileWithWords){
        String text = "";
        JavaKeyWordsCh jk = new JavaKeyWordsCh();
        jk.initialiseWords(fileWithWords);
        Set<String> wordSet = jk.getWordSet();
        try (Scanner scanner = new Scanner(content.toString())) {
            while(scanner.hasNext()) {
                text = scanner.next();
                Pattern p = Pattern.compile("[a-zA-Z]+");
                Matcher m = p.matcher(text);
                if(m.find()) {
                    text = m.group();
                }
                if (wordSet.contains(text)) {
                    if (!wordMap.containsKey(text)) {
                        wordMap.put(text, 1);
                    } else {
                        wordMap.computeIfPresent(text, (k, v) -> v + 1);
                    }


                }
            }
        }

        return this;
    }
    private StringBuilder convertMapToContentForm(){
        StringBuilder mapContent = new StringBuilder();
        for (Map.Entry<String,Integer> ent: wordMap.entrySet()){

            mapContent.append( String.format("%s  %d %n",ent.getKey(),ent.getValue()));
        }
        return mapContent;
    }
    public boolean uploadMapToFile(String fileName){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){

            bw.write(convertMapToContentForm().toString());

        }catch (IOException e){

            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }
        return true;
    }

    public JavaKeyWordsCounterCh clearContent(){
        content.delete(0,content.length());
        content.trimToSize();
        return this;
    }
    public JavaKeyWordsCounterCh clearWordMap(){
        wordMap.clear();
        return this;
    }

    public String getWordsMapAsString(){
        return wordMap.keySet().toString();
    }
    public Map<String, Integer> getWordMap() {
        return wordMap;
    }
    public StringBuilder getContent() {
        return content;
    }


}
