package epamtasks.oop.t06;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
public class TestBay {
    private static final Logger log = LogManager.getLogger(TestBay.class);

    public static void main(String[] args) {
        AtomicSubmarine mySubmarine1 = new AtomicSubmarine();
        AtomicSubmarine mySubmarine2 = new AtomicSubmarine();
        AtomicSubmarine mySubmarine3 = new AtomicSubmarine();
        AtomicSubmarine mySubmarine4 = new AtomicSubmarine();

       ArrayList <AtomicSubmarine>subArr = new ArrayList<>();
        subArr.add(mySubmarine1.setName("Victory"));
        subArr.add(mySubmarine2.setName("MarkII"));
        subArr.add(mySubmarine3);
        subArr.add(mySubmarine4.setName("Red_October"));
        for (AtomicSubmarine sub: subArr) {

            sub.startMoving()
                    .prepareEngine()
                    .stopMoving()
                    .startMoving()
                    .emergencyStop();
            log.info("------Next sub ----------");
        }
    }
}
