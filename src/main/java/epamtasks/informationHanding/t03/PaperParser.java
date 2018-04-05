package epamtasks.informationHanding.t03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PaperParser {

    private static final Logger log = LogManager.getLogger(PaperParser.class);
    private StringBuilder htmlContent;
    private String fileName;
    private static final String ERROR_FORMAT ;

    static {
        ERROR_FORMAT ="error:{}, appears from:{} " ;
    }

    PaperParser(){
        htmlContent = new StringBuilder(" ");
        fileName = "Java.SE.03.Information handling_task_attachment.html";
    }

    public String getFileName() {
        return fileName;
    }

    public PaperParser setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public boolean initialiseContent(){
        String str;
        try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName),
                                                    "Cp1251"))) {

                while ((str = reader.readLine()) != null) {
                    htmlContent.append(str);

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

    public String getContent(){
        return htmlContent.toString();
    }

    public boolean isSeqRefPaper(){

        String regexp ="\\(Рис\\.([и\\s+\\d+\\,+]*)\\)" ;
        Pattern p = Pattern.compile(regexp);
        Matcher m= p.matcher(htmlContent);
        Set<Integer> integerSet = new HashSet<>();
        int maxReff = 0;
        int oldSetSize= 0;
        boolean newmax= false;
        List<Integer> numbers;
        while (m.find()) {
            log.info("Parsing result: {}",m.group(1));
            numbers = parseNumber(m.group(1));
            for (Integer num:numbers){
               if(num>maxReff){
                   maxReff=num;
                   newmax = true;
                   log.info("new max reference is {}",maxReff);
               }
               oldSetSize = integerSet.size();
               integerSet.add(num);

               if(!newmax && oldSetSize != integerSet.size()) return false;
               newmax = false;

            }
        }
        return true;
    }

    public static List<Integer> parseNumber(String text){
        List<Integer> nums = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while (m.find()){
            nums.add(Integer.parseInt(m.group()));
        }
        return  nums;
    }

    public  List<String> parseSentense(String text){
        String refRegexp = "\\(Рис\\.([и\\s+\\d+\\,+]*)\\)" ;
        Matcher m = Pattern.compile(refRegexp).matcher(text);
        List<String> sentences = new ArrayList<>();
        while (m.find()){
            int end = findEndChar(m.end(),text);
            int start = findStartChar(m.start(),text);
            sentences.add(text.substring(start,end));
        }
        return sentences;
    }

    public static int findStartChar(int start, String text) {
        int begin = start;
        int end = start;
        String statring = "[^\\(]([А-Я])[а-я]*[^\\.\\d\\<]";
        Matcher m = Pattern.compile(statring).matcher(text);
        boolean task =false;
       do {
            begin--;
            m.region(begin,end);
            if(m.find()){

                if(m.group().endsWith(")")) {
                    end = m.start() - 2;
                    begin = end-1;
                }else {
                    task = true;
                }
            }
        } while(!task);

        return m.start(1);
    }

    public static int findEndChar(int end, String text) {

        String ending = "[^А-Я]([\\.?!])[^\\dа-я\\.\\)]{2}";
        Matcher m = Pattern.compile(ending).matcher(text);
       if (!m.find(end-1) ){
           return text.length()-1;
       }

       return m.start(1)+1;
    }

}
