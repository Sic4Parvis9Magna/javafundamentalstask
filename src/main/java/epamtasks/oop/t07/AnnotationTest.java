package epamtasks.oop.t07;

import epamtasks.oop.t06.AtomicSubmarine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;

public class AnnotationTest {
    private static final Logger log = LogManager.getLogger(AnnotationTest.class);

    public static void main(String[] args) {
        AtomicSubmarine littleSub = new AtomicSubmarine();
      java.lang.annotation.Annotation[] annotations = littleSub.getClass().getDeclaredAnnotations();
        for (Annotation ann:annotations){

            log.info("Canonical name {}",ann.annotationType().getCanonicalName());
        }

       Submarine littleAnn = littleSub.getClass().getAnnotation(Submarine.class);
        log.info("Number of engines {}, use Atomic power ?({})",littleAnn.nuberOfEngines(),littleAnn.useAmomicPower());

    }
}
