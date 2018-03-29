package epamtasks.iostreams.t03;

import org.apache.logging.log4j.Logger;

import java.io.*;

import static org.apache.logging.log4j.LogManager.*;

public class WizardEncoder {
    private static final Logger log = getLogger(WizardEncoder.class);
    private StringBuilder content;
    private String encoding;
    private static final String ERROR_FORMAT  ;

    static {
        ERROR_FORMAT = "error:{}, appears from:{} " ;
    }

    public WizardEncoder(){
       encoding = "UTF-8";
       content = new StringBuilder();
   }
    public boolean readFromFile(String fileName){

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                    encoding))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    content.append(str).append("\n");

                }

            } catch (IOException e) {
                log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
                return false;
            }
            return true;
    }
    public boolean writeToFile(String fileName){
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),
                             encoding))) {

            writer.write(content.toString());

        } catch (IOException e) {
            log.error(ERROR_FORMAT,e.getMessage(),e.getStackTrace());
            return false;
        }
        return true;
    }


    public WizardEncoder setContent(StringBuilder content) {
        this.content = content;
        return  this;
    }
    public WizardEncoder setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }
    public WizardEncoder clearContent(){
        content.delete(0,content.length())
        .trimToSize();
        return this;
    }

    public StringBuilder getContent() {
        return content;
    }
    public String getEncoding() {
        return encoding;
    }
}
