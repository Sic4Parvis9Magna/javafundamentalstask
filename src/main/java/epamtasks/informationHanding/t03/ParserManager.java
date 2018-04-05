package epamtasks.informationHanding.t03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserManager {
    private static final Logger log = LogManager.getLogger(ParserManager.class);

    public static void main(String[] args) {
        PaperParser parser = new PaperParser();
        parser.setFileName("Java.SE.03.Information handling_task_attachment.html");
        log.info("Lets try work with {} file.",parser.getFileName());
        if(!parser.initialiseContent()){
            log.error("Wrong fileName");
        }else {
            log.info("Does file have right sequence of references ? Answer : {}",parser.isSeqRefPaper());
            log.info("Let's see these sentences!");
            int counter = 1;
            for (String sent: parser.parseSentense(
                    parser.getContent())) {
                log.info("sentence #{}:\n{}",counter++,sent);

            }
        }
    }
}
