package epamtasks.informationHanding.t03;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaperParser {

    private StringBuilder htmlContent;
    private String fileName;

    PaperParser(){
        htmlContent = new StringBuilder();
        fileName = "Java.SE.03.Information handling_task_attachment.html";
    }

    public String getFileName() {
        return fileName;
    }

    public PaperParser setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    //TODO solve problem / change to boolean
    public PaperParser initialiseContent(){
        String str;
        if(new File(fileName).isFile()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                    "Cp1251"))) {

                while ((str = reader.readLine()) != null) {
                    htmlContent.append(str);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  this;
    }

    public String getContent(){
        return htmlContent.toString();
    }

    public boolean isSeqRefPaper(){

        String regexp = "\\(Рис\\.([и\\s+\\d+]*)\\)";
        Pattern p = Pattern.compile(regexp);
        Matcher m= p.matcher(htmlContent);
        Set<Integer> integerSet = new HashSet<>();
        int maxReff = 0;
        int oldSetSize= 0;
        boolean newmax= false;
        int[] numbers;
        while (m.find()) {

            numbers = parseNumber(m.group(1));
            for (Integer num:numbers){
               if(num>maxReff){
                   maxReff=num;
                   newmax = true;
               }
               oldSetSize = integerSet.size();
               integerSet.add(num);

               if(!newmax && oldSetSize != integerSet.size()) return false;
               newmax = false;

            }
        }
        return true;
    }

    private static int[] parseNumber(String string){
        string.trim();
        String[] str = string.split(" ");
       int[] arr = new int[str.length/2];
       int j=0;
        for (int i =0;i<str.length;i++){
            if((i+1)%2 == 0) {
               // System.out.println(i);
                arr[j] = Integer.parseInt(str[i]);
                j=j+1;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        PaperParser parser = new PaperParser()
                .initialiseContent();
        System.out.println(parser.getContent());
        System.out.println( parser.isSeqRefPaper());


    }
}
